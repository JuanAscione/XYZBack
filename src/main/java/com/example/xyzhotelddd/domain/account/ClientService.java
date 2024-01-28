package com.example.xyzhotelddd.domain.account;

import com.example.xyzhotelddd.database.ClientRepository;
import com.example.xyzhotelddd.http.dto.ClientRequestDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDomainService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientDomainService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(ClientRequestDto newClientDto) throws Exception {
        Client newClient = new Client(newClientDto.getEmail(), newClientDto.getFirstName(), newClientDto.getLastName(), newClientDto.getPhoneNumber());
        newClient.createEURWallet();
        return clientRepository.save(newClient);
    }

    @Data
    public class ClientService {

        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
    }

}

