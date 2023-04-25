// Here we just add an unauthenticated route request to trigger guard, that redirects to login

describe('Guard spec', () => {
    it('Guard successfull', () => {
      
      //Attempt to check sessions without login 
      cy.visit('/sessions')
  
      //Guard redirecting 
      cy.url().should('include', '/login')
  
    })

    // it('Unauth Guard successfull', () => {
    //     //First we need to login 
    //     cy.login("yoga@studio.com","test!1234")

    //     //Attempt to check index page with login 
    //     cy.visit('')
    
    //     //Guard redirecting (important : changed URL from '/rentals' to /sessions)
    //     cy.url().should('include', '/sessions')
    
    //   })
  
    
  });