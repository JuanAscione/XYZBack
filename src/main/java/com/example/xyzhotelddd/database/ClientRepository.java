package com.example.xyzhotelddd.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    void delete(Client client);

}
