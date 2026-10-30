# Api Calls
## /api/customerInfo
### POST
To create a new customer, use the following parameters:
- customerName (String)
- customerMail (String)
- customerPass (String)
- customerMobileNum (String)
- customerAddress (String)
### GET
To get customer's information, there are two methods. To get a customer's info from their mail and pass, use the following parameters:
- customerMail
- customerPass

To get a customer's info from their ID, use the following parameters:
- customerID

Both types of get requests gives the following fields:
- customerID
- customerMail
- customerPass
- customerMobileNum
- customerAddress
### PUT
To change a customer's info, use any of the following (optional) fields:
- newName
- newPassword
- newMobileNum
- newAddress
### DELETE
To delete a customer, provide the following parameter:
- customerID

## /api/account
### POST
To create an account for a customer, use the following parameters: 
- customerID
- accountName
### GET
To get an account from a customerID, use the following parameters:
- customerID

This request returns the following fields:
- accountID (UUID)
- accountName (String)
- accountBalance (float)
- locked (boolean)
### PUT
To change an account name, use the following parameters:
- accountID
- type = "changeName"
- newName

To lock/unlock an account, use the following parameters:
- accountID
- type = "toggleLock"

To do a transaction (only change an account's balance), use the following parameters:
- accountID
- type = "deposit" or "withdraw"
- amount
### DELETE
To delete an account, use the following parameter:
- accountID

## /api/accountOwners
### POST
To create a connection between a customer and an account, use the following parameters:
- accountID
- customerID
### GET
To get all accounts associated with a certain customer, use the following parameter:
- customerID

This returns a list of account IDs.

To get all customers associated with a certain account, use the following parameter:
- accountID

This returns a list of customer IDs.
### DELETE
To delete a connection, use the following parameters:
- accountID
- customerID

## /api/transaction
### POST
To create a new transaction, use the following parameters:
- accountID
- transactionType = "deposit" or "withdraw"
### GET
To get a list of all transactions associated with an account, use the following parameters:
- accountID