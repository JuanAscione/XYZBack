package com.example.xyzhotelddd.domain.payment;

import com.example.xyzhotelddd.domain.account.Client;
import com.example.xyzhotelddd.database.ClientRepository;
import com.example.xyzhotelddd.domain.payment.Money;
import com.example.xyzhotelddd.domain.payment.Wallet;
import com.example.xyzhotelddd.database.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Service
public class WalletService {

    private final ClientRepository clientRepository;
    private final WalletRepository walletRepository;
    private static final BigDecimal EUR_TO_USD_RATE = new BigDecimal("1.18");
    private static final BigDecimal EUR_TO_GBP_RATE = new BigDecimal("0.85");
    private static final BigDecimal EUR_TO_JPY_RATE = new BigDecimal("130.40");
    private static final BigDecimal EUR_TO_CHF_RATE = new BigDecimal("1.10");

    @Autowired
    public WalletService(ClientRepository clientRepository, WalletRepository walletRepository) {
        this.clientRepository = clientRepository;
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(Long idClient) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        Wallet wallet = new Wallet(client, "EUR");
        return walletRepository.save(wallet);
    }

    public Wallet deposit(Long idClient, BigDecimal amount, String currency) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        Wallet wallet = client.getWallet();
        if (wallet == null) {
            wallet = new Wallet(client, "EUR");
        }

        if (!Objects.equals(currency, "EUR")) {
            amount = this.convertToEuros(amount, currency);
        }

        Money depositedMoney = new Money(amount, null); // Always deposit in euros
        wallet.deposit(depositedMoney);

        return walletRepository.save(wallet);
    }

    public BigDecimal convertToEuros(BigDecimal amount, String sourceCurrency) {
        return switch (sourceCurrency) {
            case "USD" -> amount.divide(EUR_TO_USD_RATE, 2, BigDecimal.ROUND_HALF_UP);
            case "GBP" -> amount.divide(EUR_TO_GBP_RATE, 2, BigDecimal.ROUND_HALF_UP);
            case "JPY" -> amount.divide(EUR_TO_JPY_RATE, 2, BigDecimal.ROUND_HALF_UP);
            case "CHF" -> amount.divide(EUR_TO_CHF_RATE, 2, BigDecimal.ROUND_HALF_UP);
            default -> amount;
        };
    }

    public BigDecimal checkBalance(Long idClient) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        Wallet wallet = client.getWallet();
        if (wallet == null) {
            throw new IllegalStateException("Client does not have a wallet");
        }
        return wallet.getMoney().getAmount();
    }

    public void payForReservation(Client client, BigDecimal amount, String currency) throws Exception {

        Wallet wallet = client.getWallet();
        if (wallet == null) {
            throw new IllegalStateException("Client does not have a wallet");
        }

        Money paymentAmount = new Money(amount.divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP), Currency.getInstance("EUR"));

        // Check if the wallet balance is sufficient for the payment
        if (wallet.getMoney().getAmount().compareTo(paymentAmount.getAmount()) < 0) {
            throw new IllegalStateException("Insufficient funds");
        }

        wallet.withdraw(paymentAmount);

        walletRepository.save(wallet);
    }
}
