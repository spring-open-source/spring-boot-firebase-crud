# Short Description

Rest API made using Spring Boot performing CRUD operations on collections (entities) in a firebase database

# Firebase Setup

Create the below mentioned collections thorugh firebase console

* directors
* movies
* director_movie_mappings


# Application Setup

* Copy Contents of downloaded private key from firebase to ./rook-private-key.json
* Specify the firebase database url in application.properties
* (Optional) Port number and base address can be changed in application.properties

# Run
```
mvn clean install
```
```
run as -> Spring Boot Application
```
# Navigate to below URL to view swagger-docs
```
http://localhost:9090/rook/swagger-ui.html
```