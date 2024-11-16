    # TABLES for MySQL
## Customer Info
``` SQL
CREATE TABLE CustomerInfo(
    customerID          INT PRIMARY KEY AUTO_INCREMENT,
    customerName        VARCHAR(20),
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
);
```

### AccountOwners
``` SQL
CREATE TABLE AccountOwners(
    aID     INT,
    cID     INT,
    FOREIGN KEY (aID) REFERENCES Account(accountID) ON DELETE CASCADE,
    FOREIGN KEY (cID) REFERENCES CustomerInfo(customerID) ON DELETE CASCADE,
    PRIMARY KEY (aID, cID)
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
    FOREIGN KEY (accountNo) REFERENCES CustomerInfo(customerID),
    PRIMARY KEY (accountNo, transactionDate)
);
```

## Loans
``` SQL
CREATE TABLE Loans(
    loanID      INT PRIMARY KEY AUTO_INCREMENT,
    customerID  INT,
    amount      FLOAT, 
    rate        FLOAT,
    duration    DATE,
);
```