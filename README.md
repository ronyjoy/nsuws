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
> The below command will build the code generate a jar file and run that jar file. 
> The server will we available in http://localhost:8080/
```
./gradlew build runApp
```

APIS
============================
### Push and Recalculate API
> Sample Request
```
curl --location --request POST 'http://localhost:8080/nsuws-api/statistics/recalculate' --header 'Content-Type: text/plain' \
--data-raw '20'
```
> Sample Response 
```
{
    "avg": "20",
    "std": "0"
}
```

### Push Recalculate and Encrypt API Request
> Sample Request
```
curl --location --request POST 'http://localhost:8080/nsuws-api/statistics/recalculate/encrypt' --header 'Content-Type: text/plain' \
--data-raw '300'
```
> Sample Response
```
{
    "avg": "XGVWIhxGRH93UL0mQNa0eW6dZXt9A8xCgpGf2s7/gl1z0aVZXyzYZihDBtVKclPITbpDwg==",
    "std": "rmyjBvFsx/PgpM2U4azbDJ1D1AOE1xd35NjdC4XNZ1yQsYOxq+fLgzzzuXzVRX6ET/17qQ=="
}
```

### Decrypt string Request
> Sample Request
```
curl --location --request POST 'http://localhost:8080/nsuws-api/decrypt' \
--header 'Content-Type: text/plain' \
--data-raw 'mLBz2eUJcDmIilT0G+iv8HIF0gvO/oJnN4YP+w6T0J2TVxvU8cxtIWnr2U5SiXU0DZXFIA=='
```
> Sample Response
```
91.5
```

