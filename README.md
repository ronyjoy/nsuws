# nsuws
Project Structure
============================
    .
    ├── src                       # Source files
      ├── main                    
         ├── java
      ├── test                    # Unit tests
    ├── build.gradle              # Gradle build file to build and run the project
    └── README.md
    
Build and Run
============================
> Please Make sure you have Java 9 and above is installed
> The below command will build the code generate a jar file and run that jar file. 
> The server will we available in http://localhost:8080/
```
./gradlew build runApp
```

APIS
============================
    GET     /nsuws-api/statistics/decrypt (com.nsuws.resources.StatisticsAPI)
    POST    /nsuws-api/statistics/recalculate (com.nsuws.resources.StatisticsAPI)
    POST    /nsuws-api/statistics/recalculate/encrypt (com.nsuws.resources.StatisticsAPI)
### Push and Recalculate API
> Sample Request
```
curl --location --request POST 'http://localhost:8080/nsuws-api/statistics/recalculate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number": "30"
}'
```
> Sample Response 
```
{
    "avg": "29",
    "std": "2.685"
}
```

### Push Recalculate and Encrypt API Request
> Sample Request
```
curl --location --request POST 'http://localhost:8080/nsuws-api/statistics/recalculate/encrypt' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number": "33"
}'
```
> Sample Response
```
{
    "avg": "+mVjgL+dnKVW9pz5A2tVnO3aFeXOkxPVk0AwpleLX8eM",
    "std": "+vePA3izWO1rR2vH9xDZNdk+GZ9TkqIZ9iEPGXCGWjWz"
}
```

### Decrypt string Request
> Sample Request
```
curl --location --request GET 'http://localhost:8080/nsuws-api/statistics/decrypt' \
--header 'cipherTxt: +vePA3izWO1rR2vH9xDZNdk+GZ9TkqIZ9iEPGXCGWjWz' \
```
> Sample Response
```
{
    "value": "2.949"
}
```

