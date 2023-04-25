describe('Not found spec', () => {
    it('Should get to not-found page', () => {
      
      cy.visit('/page-that-doesnt-exist')
      cy.get('h1').should("contain", "Page not found !")
      
  })
});