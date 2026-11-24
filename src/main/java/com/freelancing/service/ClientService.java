
package com.freelancing.service;

import com.freelancing.model.Client;
import com.freelancing.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public void registerClient(Client client) {
        if (clientRepository.findByEmail(client.getEmail()) != null) {
            throw new IllegalArgumentException("Email already registered");
        }

        clientRepository.save(client);
    }
}
