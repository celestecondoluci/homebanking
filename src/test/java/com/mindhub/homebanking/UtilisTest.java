package com.mindhub.homebanking;

import com.mindhub.homebanking.utils.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class UtilisTest {
    @Test

    public void cardNumberIsCreated(){

        String cardNumber = Utility.getNumberCard();

        assertThat(cardNumber,is(not(emptyOrNullString())));

    }

    @Test

    public void cvvNumberIsCreated(){

        int cvv = Utility.getCvv();

        assertThat(cvv, notNullValue(Integer.class));
    }
    @Test

    public void AccountNumberIsCreated(){

        String AccountNumber = Utility.getNumberAccount();

        assertThat(AccountNumber,is(not(emptyOrNullString())));

    }
}
