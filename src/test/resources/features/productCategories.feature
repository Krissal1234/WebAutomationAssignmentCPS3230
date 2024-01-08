Feature: Reachability of product categories

  In order to test the reachability of Scott's product categories
  As a user of the this website
  I want to be able to visit the online shopping section, view items in a category and visit the details page of a category


  Scenario Outline:
    Given I am a user of the website
    When I visit the online shop website
    And I click on the "<category-name>" category
    Then I should be taken to "<category-name>" category
    And the category should show at least <min-product-count> products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

    Examples:
      | category-name    | min-product-count |
      | Bakery           |5                  |
      | Pets             |6                  |
      | Drinks           |7                  |
      | Household        |8                  |
      | Health & Beauty  |9                  |




   Scenario Outline: Search for a Product and Verify Results
    Given I am a user of the website
    When I search for a product using the term "<searchTerm>"
    Then I should see the search results
    And there should be at least <minProductCount> products in the search results
    When I click on the first product in the results
    Then I should be taken to the details page for that product

    Examples:
      | searchTerm | minProductCount |
      | bread       | 5              |
      |beer         |6               |

