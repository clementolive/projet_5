# Installation
### Install database :
Install a MySQL database on default port **3306**: 

- Create an empty database called "test

- Run projet_5/resources/script.sql on any local dabatase manager
(Phpmyadmin from Wamp for example)

### Install the app : 
> git clone https://github.com/achmoye/projet_5

This app is divided in two parts. There is a guide for frontend and backend.
# Frontend
Go to frontend folder: 
> cd projet_5/front
>
Install dependencies:

> npm install
> npm i --save-dev @types/jest
> npm i @testing-library/user-event
> npm install cypress -D

Launch Front-end:

> npm run start;

### Run the tests (Jest)
Launching test:

> npm run test

for following change:

> npm run test:watch

Generating Jest coverage: 

> npm test -- --coverage

Check the coverage here :
> /front/coverage/jest/lcov-report/index.html

### End-to-end tests (Cypress)
> npm run e2e
>
Then generate coverage:
> npm run e2e:coverage

Check coverage here : 
> /front/coverage/lcov-report/index.html
# Backend 
Go to project_5/back folder and run: 
> mvn clean install 
> 
### Run the app : 
With your IDE, open this folder : back/src/main/java/com.openclassrooms.starterjwt 

And run SpringBootSecurityJwtApplication.java

### Run the tests (JUnit)
Run the command : (for both unit and integration tests)
> mvn clean verify 

### Generate coverage reports : 
To generate everything needed : (already done by previous step)
> mvn clean verify

You will find 3 separate reports in back/src/target/site folder: 

For unit tests coverage:
> jacoco-unit-test-coverage-report/index.html

For integration tests coverage: 
> jacoco-integration-test-coverage-report/index.html

For merged coverage:
> jacoco-merged-test-coverage-report/index.html