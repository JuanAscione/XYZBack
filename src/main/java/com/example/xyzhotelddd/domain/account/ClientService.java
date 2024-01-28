package com.example.xyzhotelddd.domain.account;

import com.example.xyzhotelddd.database.ClientRepository;
import com.example.xyzhotelddd.http.dto.ClientRequestDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(ClientRequestDTO newClientDto) throws Exception {
        Client client = clientRepository.findByEmail(newClientDto.getEmail());
        if(client != null){
            throw new Exception("Error email already used");
        }
        Client newClient = new Client(newClientDto.getEmail(), newClientDto.getFirstName(), newClientDto.getLastName(), newClientDto.getPhoneNumber());
        newClient.createEURWallet();
        newClient.newRandomPassword();
        return clientRepository.save(newClient);
    }

    public Client login(String email, String password) throws Exception {
        Client client = clientRepository.findByEmail(email);

        if (client == null || !client.getRandomPassword().equals(password)) {
            throw new Exception("Invalid credentials");
        }
        else{
            return client;
        }
    }

    @Data
    public static class ClientDTOService {

        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
    }

}

