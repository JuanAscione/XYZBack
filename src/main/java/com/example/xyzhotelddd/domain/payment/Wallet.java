package com.example.xyzhotelddd.domain.payment;

import com.example.xyzhotelddd.domain.account.Client;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Embedded
    private Money money;

    public Wallet(BigDecimal balance, Currency currency, Client client) {
        Objects.requireNonNull(balance, "Balance must not be null");
        Objects.requireNonNull(currency, "Currency must not be null");
        Objects.requireNonNull(client, "Client must not be null");

        this.client = client;
        this.money = new Money(balance, currency);
    }

    public Wallet(Client client, String code) {
        this.client = client;
        this.money = new Money(BigDecimal.ZERO, Currency.getInstance(code));
    }


    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public void deposit(Money depositedMoney) {
        BigDecimal actualBalance = this.money.getAmount();
        this.money.setAmount(actualBalance.add(depositedMoney.getAmount()));
    }

    public void withdraw (Money depositedMoney) {
        BigDecimal actualBalance = this.money.getAmount();
        this.money.setAmount(actualBalance.subtract(depositedMoney.getAmount()));
    }
}
