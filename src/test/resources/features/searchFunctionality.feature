Feature: Search Functionality on the Website

  As a user of the website,
  I want to search for products
  so that I can find items I am interested in purchasing.

  Scenario Outline: Search for a Product and Verify Results
    Given I am a user of the website
    When I search for a product using the term "<searchTerm>"
    Then I should see the search results
    And there should be at least <minProductCount> products in the search results
    When I click on the first product in the results
    Then I should be taken to the details page for that product

    Examples:
      | searchTerm | minProductCount |
      | book       | 5               |
