describe('Form spec', () => {
    it('Form successfull', () => {
        //First we need to login 
        cy.login("yoga@studio.com","test!1234")

        cy.intercept(
        {
          method: 'POST',
          url: '/api/session',
        },
        [{"id":1,
        "name":"mai",
        "date":"1992-02-02T00:00:00.000+00:00",
        "teacher_id":1,
        "description":"nouvelle session pour test detail",
        "users":[2],
        "createdAt":"2023-04-06T15:37:34",
        "updatedAt":"2023-04-24T21:14:05"}]).as('session')

        // For teacher selection, before using drop-down we need to simulate Teachers API call
        cy.intercept('GET', '/api/teacher', {
            body: [
            {"id":1,"lastName":"DELAHAYE","firstName":"Margot","createdAt":"2023-04-06T13:52:42","updatedAt":"2023-04-06T13:52:42"},
            {"id":2,"lastName":"THIERCELIN","firstName":"Hélène","createdAt":"2023-04-06T13:52:42","updatedAt":"2023-04-06T13:52:42"}
        ]
        })

        cy.url().should('include', '/sessions')
        cy.get("button").contains("Create").click()

        //Filling session creation form 
        cy.get("input[formControlname=name]").type("juin")
        cy.get("input[formControlname=date]").type("2000-05-02")
        cy.get("textarea[formControlname=description]").type("session de juin pour tester le formulaire")

        // simulate click event on the drop down item (mat-option)
        cy.get('mat-select[formControlName=teacher_id]').click().get('mat-option')
        cy.get('.mat-option-text')
        .contains('Margot DELAHAYE')
        .then(option => {
            option[0].click();  // this is jquery click() not cypress click(). Needed for hidden dropdown. 
        });

        //Saving 
        cy.get("button").first().click(); 

        //Creation done, we should go back to sessions 
        cy.url().should('include', '/sessions')
    })
  });