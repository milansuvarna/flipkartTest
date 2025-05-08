
Feature: Validate Sort functionality for products displayed

Scenario Outline: Validate sorting by price - Low to High
Given user open Flipkart homepage
When the user search for "<product>" 
And user click on "<sort>" option
Then the user validate that the prices are sorted on page 1 and page 2

Examples:
  | product ||sort|
  | shoes   ||lowToHigh|

