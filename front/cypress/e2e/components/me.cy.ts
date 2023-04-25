describe('Me spec', () => {
    it('Me successfull', () => {
        //First we need to login 
        cy.login("yoga@studio.com","test!1234")

        cy.intercept('GET', '/api/user/1', {
            body: {
                id: 1,
                email: "email",
                lastName: "lastName",
                firstName: "firstName",
                admin: false,
                password: "password",
                createdAt: new Date(),
                updatedAt: new Date()
            } 
        })

        cy.intercept('DELETE', '/api/user/1', {
            
        })

        //Verify account page 
        cy.get("span").contains("Account").click(); 
        cy.get('h1').should("contain", "User information")

        //Testing back button (different from browser default back button)
        cy.get("mat-icon").contains("arrow_back").click(); 
        cy.url().should('include', '/sessions')
        
        //To the account page again 
        cy.get("span").contains("Account").click(); 
        cy.get('h1').should("contain", "User information")

        //Deleting account 
        cy.get("button").first().click(); 
        cy.url().should('include', '/sessions')
    })
});