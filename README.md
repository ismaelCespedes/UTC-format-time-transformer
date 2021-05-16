## UTC-format-time-transformer
This is a service that receives a time and a timeZone and returns the time in UTC format within a JSON file, it is developed with Spring Boot.
## Used components:
-Java 11 <br />
-Maven 3.6.3 <br />
-Spring boot 2.4.2 <br />

## Instructions for use:
The service is running on the endpoint POST {{domain}}/api/transform-time. Requires a body with the format:<br />
{<br />
     "time": "HH: mm: ss",<br />
     "timezone": "(+/-) HH:mm or H:m or H"<br />
}<br />

example:<br />
     POST localhost:8080/api/transform-time<br />
     body:<br />
          {<br />
               "time": "10:04:00",<br />
               "timezone": "-11"<br />
          }<br />
          
## the curl request is:      
curl --location --request POST 'https://spring-format-transformet-serv.herokuapp.com/api/transform-time' \
--header 'Content-Type: application/json' \
--data-raw '{  
    "time": "10:04:00",
    "timezone": "-1"
}'

The response returned is of type "application/octet-stream" since it is a file with extension .json <br />

This service is published in the Url: https://spring-format-transformet-serv.herokuapp.com/
