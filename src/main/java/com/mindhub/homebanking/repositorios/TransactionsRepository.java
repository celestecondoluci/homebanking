package com.mindhub.homebanking.repositorios;

import com.mindhub.homebanking.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {

}
