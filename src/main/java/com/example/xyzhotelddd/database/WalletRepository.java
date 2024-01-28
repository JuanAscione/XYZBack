package com.example.xyzhotelddd.database;

import com.example.xyzhotelddd.domain.account.Client;
import com.example.xyzhotelddd.domain.payment.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByClient(Client client);

}
