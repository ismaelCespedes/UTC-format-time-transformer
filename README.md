## UTC-format-time-transformer
This is a service that receives a time and a timeZone and returns the time in UTC format within a JSON file, it is developed with Spring Boot.

## Used components:
-Ubuntu 20.04.2 LTS<br />
-Java 11 <br />
-Maven 3.6.3 <br />
-Spring boot 2.4.2 <br />
-testng 7.4 <br />

## Instructions for use:
<p>To generate the project .jar file use the command <strong>mvn clean install</strong>. This command will generate the jar in the target folder.
To run the project through the .jar use the command <strong>java -jar jarFileName.jar</strong>.</p>
<p>In this case you can run <strong>java -jar target/com.utc.format.transformer.service-0.0.1-SNAPSHOT.jar</strong> in the root of the project.</p>
     
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

## The curl request is:      
curl --location --request POST 'https://spring-format-transformet-serv.herokuapp.com/api/transform-time' \
--header 'Content-Type: application/json' \
--data-raw '{  
    "time": "10:04:00",
    "timezone": "-1"
}'

The response returned is of type "application/octet-stream" since it is a file with extension .json <br />

<p>The unit test of the service was done with <strong>testng</strong>, and it is found in the Test folder.</p>

This service is published in the Url: https://spring-format-transformet-serv.herokuapp.com/
