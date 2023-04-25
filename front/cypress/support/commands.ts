// ***********************************************
// This example namespace declaration will help
// with Intellisense and code completion in your
// IDE or Text Editor.
// ***********************************************
declare namespace Cypress {
  interface Chainable<Subject = any> {
    login(username:string, password:string): Chainable<any>;
  }
}
//
// function customCommand(param: any): void {
//   console.warn(param);
// }
//
// NOTE: You can use it like so:
// Cypress.Commands.add('customCommand', customCommand);
//
// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
//Cypress.Commands.add("login", (email, password) => { })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })

// This custom command allows easy login for tests that need it. 
// A yoga session has been added in intercept() for testing. 
Cypress.Commands.add("login", (username: string, password: string) => {
    cy.visit('/login')

    cy.intercept('POST', '/api/auth/login', {
        body: {
          id: 1,
          username: 'userName',
          firstName: 'firstName',
          lastName: 'lastName',
          admin: true
        },
      })
  
      //Adds a session in API for testing 
      cy.intercept(
        {
          method: 'GET',
          url: '/api/session',
        },
        [{"id":1,
        "name":"mai",
        "date":"1992-02-02T00:00:00.000+00:00",
        "teacher_id":1,
        "description":"nouvelle",
        "users":[2],
        "createdAt":"2023-04-06T15:37:34",
        "updatedAt":"2023-04-24T21:14:05"}]).as('session')
  
      cy.get('input[formControlName=email]').type(username)
      cy.get('input[formControlName=password]').type(`${password}{enter}{enter}`)
  })