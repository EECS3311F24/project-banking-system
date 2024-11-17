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
