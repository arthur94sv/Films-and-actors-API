A web-site that allows the user to introduce their favorite movies with their actors and keep track of them. The site was build using Angular for front-end , Spring and JDBC for back-end and an Oracle database for storage. The searching of actors could be done either by ID or by the actor’s name and the results where ordered alphabetically by the name. The films could be searched using the ID, the film’s name or by selecting the genre. The results where also ordered by the film’s name. Unit testing was done for service layer and integration testing for repository layer.


## Environment set-up
In order to build and rund the application, the JDK 1.8.0 Update 201, the JRE 1.8.0 Update 201, the Oracle Database 11g Express Edition, Spring Boot 2.1.7, Angular CLI 8.3.3 and NodeJS 12.13.0. 



## How to run
1. Create the database schema by running the DDL found in the export.sql file
2. Add the ojdbc7.jar to the API class path; the API can be found at the FilmeActori REST API folder
3. Run the REST API
4. Run the front-end Angular SPA; the project can be found at the "https://github.com/arthur94sv/Films-and-actors-ANGULAR" repository
5. The application can be accessed at http://localhost:4200
