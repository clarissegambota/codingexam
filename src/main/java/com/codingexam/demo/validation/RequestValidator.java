package com.codingexam.demo.validation;

import com.codingexam.demo.api.account.Account;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RequestValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return Account.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, org.springframework.validation.Errors errors) {
            Account account = (Account) target;
            if (!StringUtils.hasLength(account.getCustomerEmail())) {
                throw new RequiredFieldException("Customer email is required.");
            }

            if (null == account.getAccountType()) {
                throw new RequiredFieldException("Account Type is required.");
            }
        }
}
