# moneywise-api

> REST API with authentication using Spring Boot, Spring Security, OAuth2+JWT, JPA and Liquibase.

API developed for financial management, consisting of logging personal transactions.

## Dependencies

- Java 8
- Apache Maven
- MySQL
- Postman (optional)

## Running the application

Make sure you have MySQL running in your local machine, you might have to change the database connection settings in /moneywise-api/src/main/resources/application.properties

```properties
#database connection
spring.jpa.database=mysql
spring.datasource.url=jdbc:mysql://localhost/moneywise?createDatabaseIfNotExist=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
```

With the database up and running, simply run the class com.glima.moneywise.MoneywiseApplication.java

```java
package com.glima.moneywise;

import com.glima.moneywise.config.property.MoneywiseApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MoneywiseApiProperty.class)
public class MoneywiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneywiseApplication.class, args);
	}
}
```

## Usage


### Resources

The API available resources are:

- categories
- persons
- transactions

### URL

`http:localhost:8080`

### Getting the Authentication Token

`http://localhost:8080/oauth/token`

#### Headers

| chave         | Valor         |
| ------------- | ------------- |
| Content-Type  | application/x-www-form-urlencoded  |
| Authorization  | Basic YW5ndWxhcjpAbmd1bEByMA==  |

#### Body

| chave         | Valor         |
| ------------- | ------------- |
| client  | angular  |
| username  | admin@moneywise.com  |
| password  | admin  |
| grant_type  | password  |

> The Refresh Token is returned as a cookie called 'refreshToken'.

### Refreshing the Authentication Token

`https://localhost:8080/oauth/token`

#### Headers

| chave         | Valor         |
| ------------- | ------------- |
| Content-Type  | application/x-www-form-urlencoded  |
| Authorization  | Basic YW5ndWxhcjpAbmd1bEByMA==  |

#### Body

| chave         | Valor         |
| ------------- | ------------- |
| grant_type  | refresh_token  |
| refresh_token  | eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiJmMGZhZTNjZi00Y2MxLTRhYzItYTQzMS1jYTdjYzY0YzA5YWMiLCJleHAiOjE1MDgyNDQyNjQsImF1dGhvcml0aWVzIjpbIlJPTEVfUk9MRSJdLCJqdGkiOiJjMzcyNDY1Yy1kNDFkLTRjMDAtYmE4Yi01MGQ1OGM2MzFiNWMiLCJjbGllbnRfaWQiOiJhbmd1bGFyIn0.3MMwWjjSVFf2wgWq7Pe_-I2kepZVVsyLF0rCBSU1yhY  |

#### Response

```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTAwOTc3MTksInVzZXJfbmFtZSI6ImFkbWluQGFsZ2Ftb25leS5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX0NBREFTVFJBUl9DQVRFR09SSUEiLCJST0xFX1BFU1FVSVNBUl9QRVNTT0EiLCJST0xFX1JFTU9WRVJfUEVTU09BIiwiUk9MRV9DQURBU1RSQVJfTEFOQ0FNRU5UTyIsIlJPTEVfUEVTUVVJU0FSX0xBTkNBTUVOVE8iLCJST0xFX1JFTU9WRVJfTEFOQ0FNRU5UTyIsIlJPTEVfQ0FEQVNUUkFSX1BFU1NPQSIsIlJPTEVfUEVTUVVJU0FSX0NBVEVHT1JJQSJdLCJqdGkiOiIwMTRjOTNjNy05ZTM2LTRlYjUtYjYzNi1jZWQyMDg1YWFiOTIiLCJjbGllbnRfaWQiOiJhbmd1bGFyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.LpxKD9DFtO83TQPEZXXVISZBYaWmpSeObjA0cmw113A",
    "token_type": "bearer",
    "expires_in": 1799,
    "scope": "read write",
    "jti": "014c93c7-9e36-4eb5-b636-ced2085aab92"
}
```


