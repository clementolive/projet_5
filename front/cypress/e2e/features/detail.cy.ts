describe('Detail spec', () => {
    it('Detail successfull', () => {
         //First we need to login 
         cy.login("yoga@studio.com","test!1234")

        //Intercept detail request 
        cy.intercept('GET', '/api/session/1', {
            body: {
            "id":1,
            "name":"mai",
            "date":"1992-02-02T00:00:00.000+00:00",
            "teacher_id":1,
            "description":"nouvelle",
            "users":[2],
            "createdAt":"2023-04-06T15:37:34",
            "updatedAt":"2023-04-24T21:14:05"
            }
        })

        //Intercept teacher request 
        cy.intercept('GET', '/api/teacher/1', {
            body: {
            "id":1,
            "lastName":"DELAHAYE",
            "firstName":"Margot",
            "createdAt":"2023-04-06T13:52:42",
            "updatedAt":"2023-04-06T13:52:42"}
        })

        

       
        //Waiting for sessions page 
        cy.url().should('include', '/sessions')

        //Go to detail and verify it 
        cy.get("button").contains("Detail").click(); 
  
    })
  });