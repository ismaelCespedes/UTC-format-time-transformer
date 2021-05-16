## UTC-format-time-transformer
This is a service that returns the time in UTC format within a JSON file developed with Spring Boot.
## Used components:
-java 11
-maven 3.6.3
-spring boot 2.4.2

## Instructions for use:
The service is running on the endpoint POST {{domain}}/api/transform-time
requires a body with the format:
{
     "time": "HH: mm: ss",
     "timezone": "(+/-) HH:mm or H:m or H"
}

example:
     POST localhost:8080/api/transform-time
     body:
          {
               "time": "10:04:00",
               "timezone": "-11"
          }
          
## the curl request is:      
curl --location --request POST 'https://spring-format-transformet-serv.herokuapp.com/api/transform-time' \
--header 'Content-Type: application/json' \
--data-raw '{  
    "time": "10:04:00",
    "timezone": "-1"
}'

The response returned is of type "application/octet-stream" since it is a file with extension .json 

This service is published in the Url: https://spring-format-transformet-serv.herokuapp.com/
