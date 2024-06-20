package com.codingexam.demo;

import com.codingexam.demo.api.account.Account;
import com.codingexam.demo.api.savings.Savings;
import com.codingexam.demo.model.AccountResponse;
import java.util.ArrayList;
import java.util.List;

public class ResponseMapper {

    public static AccountResponse mapPostResponse(Account account, int statusCode, String statusDescription) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setCustomerNumber(account.getCustomerNumber());

        mapCommonResponse(statusCode, statusDescription, accountResponse);
        return accountResponse;
    }

    public static void mapCommonResponse(int statusCode, String statusDescription, AccountResponse accountResponse) {
        accountResponse.setTransactionStatusCode(statusCode);
        accountResponse.setTransactionStatusDescription(statusDescription);
    }

    public static AccountResponse mapGetResponse(Account account, Savings savings, int statusCode, String statusDescription) {
        AccountResponse accountResponse = new AccountResponse();
        
        // can utilize mapstruct here        
        accountResponse.setCustomerNumber(account.getCustomerNumber());
        accountResponse.setCustomerName(account.getCustomerName());
        accountResponse.setCustomerMobile(account.getCustomerMobile());
        accountResponse.setCustomerEmail(account.getCustomerEmail());
        accountResponse.setAddress1(account.getAddress1());
        accountResponse.setAddress2(account.getAddress2());

        if (account.getAccountType() == AccountType.S) {
            List<Savings> savingsList = new ArrayList<>();
            Savings savings1 = new Savings();
            savings1.setAccountNumber(savings.getAccountNumber());
            savings1.setAccountType(AccountType.S.getDescription());
            savings1.setAvailableBalance(savings.getAvailableBalance());

            savingsList.add(savings1);

            accountResponse.setSavings(savingsList);
        }

        mapCommonResponse(statusCode, statusDescription, accountResponse);

        return accountResponse;
        
    }

}
