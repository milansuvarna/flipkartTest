Introduction -

This framework consist of UI functional automation for Flipkart.com (Sort functionality and Add to Cart Validation). The number of test cases are limited as of now but can be easily added later. 
NOTE : The projects download Maven dependencies when run for the first time. Subsequent runs will be faster.

Prerequisites:

Chrome browser preferably latest version should be installed in the runtime environment. Java 8 Optional - IDE prefereably Visual Studio should be available for running tests via it.

Instructions to run:

Open Terminal on vs code and run the maven command "mvn clean test". This can be later used to run targeted tests based on group tags, different xml for Smoke or other tests etc using Jenkins.

Reports:

A cucumber report link is generated in the target folder, this can be opened via terminal with this command open "cucumber report path". 
