    # TABLES for MySQL
## Customer Info
``` SQL
CREATE TABLE CustomerInfo(
    customerID          INT PRIMARY KEY AUTO_INCREMENT,
    customerMail        VARCHAR(25) UNIQUE,
    customerPass        VARCHAR(20),
    customerMobileNum   VARCHAR(10),
    customerAddress     VARCHAR(20),
);
```
## Account
``` SQL
CREATE TABLE Account(
    accountID           INT PRIMARY KEY AUTO_INCREMENT,
    accountBalance      INT,
    ownerID             INT,
    FOREIGN KEY (ownerID) REFERENCES CustomerInfo(CustomerID)
);
```

## Saved Payees
``` SQL
CREATE TABLE SavedPayees(
    nickname    VARCHAR(20),
    saver       INT,
    payee       INT,
    FOREIGN KEY (saver) REFERENCES CustomerInfo(customerID) ON DELETE CASCADE,
    FOREIGN KEY (payee) REFERENCES CustomerInfo(customerID) ON DELETE CASCADE,
    PRIMARY KEY (saver, payee)
);
```
## Transactions
``` SQL
CREATE TABLE Transactions(
    accountNo           INT NOT NULL,
    transactionType     VARCHAR(7) CHECK (type="deposit" OR type='withdraw'),
    amount              INT NOT NULL,
    transactionDate     DATE,
    FOREIGN KEY (accountN) REFERENCES CustomerInfo(customerID),
    PRIMARY KEY (accountNo, transactionDate)
);
```