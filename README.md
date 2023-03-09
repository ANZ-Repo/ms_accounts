# Account List and Transaction History Microservice
In this project, two APIs are exposed to retrieve the account list and transacation history details.

## Technologies

- Spring Boot
- Spring Data JPA
- H2 Database 
- Maven 
- Mockito
- Rest Assured

## Running the app locally

mvn spring-boot:run

#### Retrieve Account List - /user/{userId}/accounts?pageNo=?&pageSize=?

Sample Request and Response:

GET "http://localhost:8080/accounts/100?pageSize=1"

```JSON
   {
        "customerId": 100,
        "accountNumber": 12345,
        "accountType": "savings",
        "accountName": "accname",
        "balanceDate": "2021-12-31T12:59:59.000+00:00",
        "currency": "AUD",
        "balance": "100"
    }
```

#### Retrieve Transaction History - /accounts/{accountId}/transaction?pageNo=?&pageSize=?&sortBy=?

Sample Request and Response:

GET http://localhost:8080/accounts/12345/transactions

```JSON

    {
        "accountNumber": 12345,
        "accountName": "savings",
        "transDate": "2021-12-31T12:59:59.000+00:00",
        "currency": "AUD",
        "creditAmount": "20",
        "debitAmount": "0",
        "transType": "credit"
    }
```
