package com.mindhub.homebanking.repositorios;

import com.mindhub.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //se va a encargar de automatizar la creacion de un repositorio y publicarlo via REST y acceder a las operaciones/controladores
public interface ClientRepository extends JpaRepository<Client,Long>{
    Client findByMail(String mail);
}
