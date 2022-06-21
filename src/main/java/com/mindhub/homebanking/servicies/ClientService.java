package com.mindhub.homebanking.servicies;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getClientsDTO();
    Client getClientCurrent(Authentication authentication);


    ClientDTO getClientDTOid(long id);

    void saveClient(Client client);

    Client findByMail(String mail);
}
