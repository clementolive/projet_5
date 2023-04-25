// Here we just add an unauthenticated route request to trigger guard, that redirects to login

describe('Guard spec', () => {
    it('Guard successfull', () => {
      
      //Attempt to check sessions without login 
      cy.visit('/sessions')
  
      //Guard redirecting 
      cy.url().should('include', '/login')
  
    })
  
    
  });