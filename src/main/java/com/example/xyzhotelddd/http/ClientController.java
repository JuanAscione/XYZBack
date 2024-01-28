package com.example.xyzhotelddd.http;

import com.example.xyzhotelddd.domain.account.Client;
import com.example.xyzhotelddd.domain.account.ClientService;
import com.example.xyzhotelddd.http.dto.ClientRequestDTO;
import com.example.xyzhotelddd.http.dto.ClientResponseDTO;
import com.example.xyzhotelddd.http.dto.LoginDTO;
import com.example.xyzhotelddd.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientRequestDTO newClient) {
        Validators validators = new Validators();
        try {
            if (!validators.validateName(newClient.getFirstName())) {
                throw new Exception("Error first name");
            } else if (!validators.validateName(newClient.getLastName())) {
                throw new Exception("Error last name");
            } else if (!validators.validateEmail(newClient.getEmail())) {
                throw new Exception("Error email");
            } else if (!validators.validatePhoneNumber(newClient.getPhoneNumber())) {
                throw new Exception("Error phone number");
            }
            else {
                Client createdClient = clientService.createClient(newClient);
                ClientResponseDTO responseDTO = new ClientResponseDTO(createdClient.getId(), createdClient.getEmail(), createdClient.getRandomPassword(), createdClient.getFirstName(), createdClient.getLastName(), createdClient.getPhoneNumber());
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating client: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ClientResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            Client client = clientService.login(loginDTO.getEmail(), loginDTO.getPassword());
            ClientResponseDTO clientResponse = new ClientResponseDTO(client.getId(), client.getEmail(), client.getRandomPassword(), client.getFirstName(), client.getLastName(), client.getPhoneNumber());
            return new ResponseEntity<>(clientResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
