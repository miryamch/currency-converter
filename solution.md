# Currency Converter
Simple web application to fetch and display properly localized the price of a cryptocurrency.
## Tech stack
- Java 17
- Spring Boot 
- Thymeleaf
- Maven
- H2 database 

Note: This app was created with Bootify.io - more documentation [can be found here](https://bootify.io/docs/). 
## Start the application

From the directory `currency-converter` run
```
mvn spring-boot:run
```
The landing page is available on [localhost:8080](localhost:8080)

The database is accessible from the [H2 console](http://localhost:8080/h2-console)

## Remarks 

- The application relies on an external api ([ip-api.com](http://ip-api.com/json/)) to resolve the country by ip. In case that api is unavailable, 
a fallback locale is used to format the price (`en_US`). 
- Determining the locale using the IP is an interesting challenge, but in real life applications, the locale can 
be simply determined from the request's locale. It should match the user's locale. 
- Once the country of the ip address is determined, it is not straightforward to decide language should be used for the 
formatting, as a country may have several languages. The solution used in this application is to have a data store of arbitrarily assigned languages for each country. 
*note: This database is currently incomplete.*  
