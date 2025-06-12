Feature: Object API automation

  Scenario: Verify an item can be created
    Given a "Apple MacBook Pro 16" item is created
    And is a "Intel Core i9" CPU model
    And has a price of "1849.99"
    When the request to add the item is made
    Then a 200 response code is returned
    And a "Apple MacBook Pro 16" is created

  Scenario: Ability to return an item
    Given an existing item is created with name "Test Item"
    When the request to get the item by id is made
    Then a 200 response code is returned
    And the item name is "Test Item"

  Scenario: Ability to list multiple items
    Given multiple items are created
    When the request to list all items is made
    Then a 200 response code is returned
    And the list contains at least 13 items
    And the below contains mobile phone details:
      | id | name                              |
      | 1  | Google Pixel 6 Pro                |
      | 2  | Apple iPhone 12 Mini, 256GB, Blue |
      | 3  | Apple iPhone 12 Pro Max           |




