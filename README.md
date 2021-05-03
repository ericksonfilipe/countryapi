# Country API

### Overview
Country API is an application developed using:
* [Java 16](https://openjdk.java.net/projects/jdk/16/)
* [SpringBoot](https://spring.io/guides/gs/spring-boot/ )
* [Maven](https://maven.apache.org/guides/index.html)

### OpenApi specification
It can be found in the root of the project in the `countryapi.yaml` file.


### Instructions to run the project locally
1. Download the project;
2. Enter the `countryapi` folder;
```
cd countryapi
```
3. Start the application:
```
./mvnw clean spring-boot:run
```
4. The application should be running on `http://localhost:8080`

If you just want to run the test suite, run the command below:
```
./mvnw test
```

### Related Projects
* [Country WebApp](https://github.com/ericksonfilipe/countrywebapp)

### Possible improvements
* Use of in-memory cache with evicting policy every x unit of time;
* Change code format to follow java/SpringBoot guidelines;
* Organize the packages to follow SpringBoot guidelines;
* Use [WebClient](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html) instead of RestTemplate;
* Improve logging;
* ...
