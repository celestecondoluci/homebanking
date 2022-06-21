package com.mindhub.homebanking.servicies;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;

import java.util.List;

public interface AccountService {

    List<AccountDTO> getAccountsDto();
    public AccountDTO getAccountid(Long id);
    void saveAccount(Account account);

    Account findByNumber(String number);

    Account findById(Long id);


}
