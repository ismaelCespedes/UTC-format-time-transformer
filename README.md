## UTC-format-time-transformer
#This is a service that returns the time in UTC format within a JSON file developed with Spring Boot.

##Instructions for use:

#The service is running on the endpoint POST {{domain}}/api/transform-time
#requires a body with the format:
{
     "time": "HH: mm: ss",
     "timezone": "(+/-) HH:mm or H:m or H"
}

#example:
{
     "time": "10:04:00",
     "timezone": "-11"
}

#The response returned is of type "application/octet-stream" since it is a file with extension .json 
