package com.codingexam.demo.api.account;

import com.codingexam.demo.AccountType;
import com.codingexam.demo.ResponseMapper;
import com.codingexam.demo.api.savings.Savings;
import com.codingexam.demo.api.savings.SavingsRepository;
import com.codingexam.demo.model.AccountResponse;
import com.codingexam.demo.validation.RequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private AccountRepository accountRepository;

    private SavingsRepository savingsRepository;

    private RequestValidator requestValidator;

    private ResponseMapper responseMapper;


    private static final String ACCOUNT_CREATED = "Customer account created";
    private static final String ACCOUNT_FOUND = "Customer account found";
    private static final String ACCOUNT_NOT_FOUND = "Customer not found";

    public AccountController(AccountRepository accountRepository, SavingsRepository savingsRepository)     {
        this.accountRepository = accountRepository;
        this.savingsRepository = savingsRepository;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody Account account, UriComponentsBuilder uriComponentsBuilder) {
        if (account.getCustomerEmail() == "" || null == account.getCustomerEmail()) {
            AccountResponse accountResponse = new AccountResponse();
            responseMapper.mapCommonResponse(HttpStatus.BAD_REQUEST.value(), "Customer email is required", accountResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accountResponse);
        }

        if (null == account.getAccountType()) {
            AccountResponse accountResponse = new AccountResponse();
            responseMapper.mapCommonResponse(HttpStatus.BAD_REQUEST.value(), "Account type is required", accountResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accountResponse);
        }

        Account savedAccount = accountRepository.save(account);

        if (AccountType.S.equals(savedAccount.getAccountType())) {
            Savings savingsDomain = new Savings();
            savingsDomain.setAccountType(AccountType.S.getDescription());
            savingsDomain.setAvailableBalance(0L);
            savingsDomain.setAccount(savedAccount);

            savingsRepository.save(savingsDomain);
        }

        AccountResponse response = responseMapper.mapPostResponse(savedAccount, HttpStatus.CREATED.value(), ACCOUNT_CREATED);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long customerNumber) {
        Account accountDomain = accountRepository.findByCustomerNumber(customerNumber);
        AccountResponse accountResponse = new AccountResponse();
        if (accountDomain == null) {
            responseMapper.mapCommonResponse(HttpStatus.NOT_FOUND.value(), ACCOUNT_NOT_FOUND, accountResponse);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountResponse);
        }
        Savings savings = savingsRepository.findByCustomerNumber(accountDomain.getCustomerNumber());

        accountResponse = responseMapper.mapGetResponse(accountDomain, savings, HttpStatus.FOUND.value(), ACCOUNT_FOUND);
        return ResponseEntity.status(HttpStatus.FOUND).body(accountResponse);
    }

}
