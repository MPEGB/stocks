# stocks
Stocks api is a java based backend application using REST. It contains the following endpoints.
GET /api/stocks (get a list of stocks)
GET /api/stocks/{id} (get one stock from the list)
PUT /api/stocks/{id} (update the price of a single stock)
POST /api/stocks (create a stock)
DELETE /api/stocks/{id} (delete a stock)

Application support two different profiles which are local and prod
 - local profile uses in-memory database
 - prod profile uses mysql database


Steps to build application

Go to root directory 
Type -> mvn clean install

In order to run application
 - Go to root directory 
 - To run local profile type
    - java -jar target/stocks-1.0.0.jar --spring.profiles.active=local
 - To run prod profile 
    - Execute script from stock.sql on mysql DB
    - Go to root directory type -> java -jar target/stocks-1.0.0.jar --spring.profiles.active=prod

After successful execution user will be able to view/create/update/delete stocks. Screen-shots are given below.

![Alt text](/documentation/add-stock.png?raw=true "Create Stock")

![Alt text](/documentation/added-succes.png?raw=true "Created Stock")

![Alt text](/documentation/add-more.png?raw=true "Create More Stock")

![Alt text](/documentation/update-stock.png?raw=true "Update Stock")

![Alt text](/documentation/remove-stock.png?raw=true "Delete Stock")
