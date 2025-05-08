
Feature:Add to Cart Functionality

Scenario Outline: Validate total sum in cart page
Given user open Flipkart homepage
When the user search for "<product>" 
And user click on "<sort>" option
And user click on the second available product in the list, and click on Add to cart
And user click on the third available product in the list, and click on Add to cart.
And user goes to cart page and verify the total sum
Then total sum shoud be validate

Examples:
  | product ||sort|
  | shoes   ||lowToHigh|