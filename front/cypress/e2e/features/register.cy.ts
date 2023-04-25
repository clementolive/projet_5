describe("register spec", () => {
    it("register successfull", () => {
      cy.visit("/register")

      cy.intercept('POST', '/api/auth/register', {
          body: {
            email: "email", 
            firstName: "firstName", 
            lastName: "lastName", 
            password: "password"
          },
        })

      // Filling form 
      cy.get('input[formControlName=email]').type("new_test@test.com")
      cy.get('input[formControlName=firstName]').type("firstName")
      cy.get('input[formControlName=lastName]').type("lastName")
      cy.get('input[formControlName=password]').type(`${"password"}{enter}{enter}`)

      //Then it redirects to login 
      cy.url().should('include', '/login')
    })
});