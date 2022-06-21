package com.mindhub.homebanking.servicies.implement;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositorios.ClientRepository;
import com.mindhub.homebanking.servicies.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    ClientRepository clientRepository;

     public List<ClientDTO> getClientsDTO(){
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @Override
    public Client getClientCurrent(Authentication authentication) {
        return clientRepository.findByMail(authentication.getName());
    }

    @Override
    public ClientDTO getClientDTOid(long id) {
        return clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
    }

    @Override
    public void saveClient(Client client) {
    clientRepository.save(client);
    }

    @Override
    public Client findByMail(String mail) {
        return clientRepository.findByMail(mail);
    }


}
