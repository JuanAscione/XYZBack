package com.example.xyzhotelddd.database;

import com.example.xyzhotelddd.domain.account.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    void delete(Client client);

}
