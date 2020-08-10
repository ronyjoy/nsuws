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
> Please Make sure you have __Java 9 or above__ is installed
> The below command will build the code generate a jar file and run that jar file. 
> The server will we available in http://localhost:8080/

> Build and run the server in linux based system
```
./gradlew build runApp
```
> Build and run the server in windows
``` 
gradlew build runApp
```

APIS
============================
    POST    /nsuws-api/statistics/recalculate
    POST    /nsuws-api/statistics/recalculate/encrypt
    GET     /nsuws-api/statistics/decrypt 
### Push and Recalculate API 
> POST /nsuws-api/statistics/recalculate
 
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
> POST    /nsuws-api/statistics/recalculate/encrypt

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
> GET     /nsuws-api/statistics/decrypt 

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


Design Consideration
============================
Design  | Reason
------------- | -------------
Std Deviation Calculation | Inorder to calculate the std deviation efficiently(memory/CPU) used a formula described in the following discussion. with this formula only four value will be memory at any point of time Reference:  https://math.stackexchange.com/questions/775391/can-i-calculate-the-new-standard-deviation-when-adding-a-value-without-knowing-t   
BigDecimal for Calculation | It can handle very large and very small floating point numbers with great precision but compensating with the time complexity a bit.
Synchronized recalculate method in StatisticsService  | The recalculate method has to do the Std Deviation/average calculation as a unit of work. otherwise two threads could read the same value from the data store and the store will end up have wrong statistics data
Singleton Store | Singleton store is used to store the current std deviation/average/total numbers , because as per the requirement this data can be wiped when system restarts.
Encryption | AES-GCM is used,AES data encryption is a more mathematically efficient and elegant cryptographic algorithm. AES-GCM is written in parallel which means throughput is significantly higher than AES-CBC by lowering encryption overheads.
   
   