package com.mindhub.homebanking.servicies;

import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

import java.util.List;

public interface LoanService {
   List<LoanDTO> getLoansDTO();
    public LoanDTO getLoansDTOid(Long id);
    void saveLoan(Loan loan);
    Loan findById(Long id);

    void saveClientLoan(ClientLoan clientLoan);


}
