
@tag
Feature: Purchase the order from e-commerce website
  I want to use this template for my feature file

	Background:
	Given I landed on e-commerce page 
	
#String products[] = { "IPHONE 13 PRO", "ADIDAS ORIGINAL"};

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with user name <name> and password <password>  
    When I add product <product> to cart 
    And Checkout <product> and submit the order 
    Then "THANKYOU FOR THE ORDER." message is displayed in confrimation page 

    Examples: 
      | name                 | password         | product        |
      | johnqwerty@gmail.com |  12345678A!1q    | ADIDAS ORIGINAL|

      
      
      