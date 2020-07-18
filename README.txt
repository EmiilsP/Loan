Done all requested parts related to task:

    *Application must expose REST API endpoints for the following functionality:
        -apply for loan (loan amount, term, name, surname and personal id must be provided)
        -list all approved loans
        -list all approved loans by user

    *Service must perform loan application validation according to the following rules and reject application if:
        -Application comes from blacklisted personal id
        -N application / second are received from a single country (essentially we want to limit number of loan applications coming from a country in a given timeframe)

    *Service must perform origin country resolution using the following web service and store country code together with the loan application. Because network is unreliable and services tend to fail, let's agree on default country code - "lv".


Potential improvements:
    *Security
    *Authorization
    *Documentation Comments
    *Better response handling in success/error cases
    *Additional endpoints (for example, createPerson)


Test data generated in homework\loan\src\main\resources\data.sql which can be used for endpoint testing.
In homework\loan\src\main\resources\application.properties can configure limits for loan applications
    *apiFilter.sameCountry.requestPeriodInSeconds=3
    *apiFilter.sameCountry.validRequestCount=2

Endpoints:
http://localhost:8080/listAll -list all approved loans
http://localhost:8080/listAll/1 -list all approved loans by user id
http://localhost:8080/apply -apply for loan
    request body example:
        {
            "loan": {
                "amount": 12321,
                "term": "test",
                "person": {
                    "id": 4
                },
                "country": {
                    "id": 5
                }
            },
            "user": {
                "id": 1
            }
        }