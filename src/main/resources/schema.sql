CREATE TABLE account (
  customerNumber BIGINT AUTO_INCREMENT PRIMARY KEY,
  customerName VARCHAR(50) NOT NULL,
  customerMobile VARCHAR(20) NOT NULL,
  customerEmail VARCHAR(50) NOT NULL,
  address1 VARCHAR(100) NOT NULL,
  address2 VARCHAR(100),
  accountType VARCHAR(1) NOT NULL
);

CREATE TABLE savings (
    accountNumber BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountType ENUM('S') NOT NULL,
    availableBalance BIGINT NOT NULL,
    customerNumber BIGINT,
    FOREIGN KEY (customerNumber) REFERENCES account(customerNumber)
);

CREATE TABLE checking (
    accountNumber BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountType ENUM('C') NOT NULL,
    availableBalance DECIMAL(10, 2) NOT NULL,
    customerNumber BIGINT,
    FOREIGN KEY (customerNumber) REFERENCES account(customerNumber)
);