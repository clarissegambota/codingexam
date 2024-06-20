INSERT INTO account(customerNumber, customerName, customerMobile, customerEmail, address1, address2)
VALUES (1, 'John Doe', '1234567890', 'test@mail.com', '123 Main St', 'Apt 1', 'S');

INSERT INTO savings(accountNumber, accountType, availableBalance, customerNumber)
VALUES (101, 'Savings', 1000.00, 1);

INSERT INTO checking(accountNumber, accountType, availableBalance, customerNumber)
VALUES (1019, 'Checking', 100.00, 1);