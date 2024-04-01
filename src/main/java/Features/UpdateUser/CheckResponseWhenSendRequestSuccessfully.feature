#Author: PhuongThuy
@CreateUserApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario: Check response when send request successfully
    Given I have valid URL and method
      | URL                         | Method |
      | https://reqres.in/api/users/2 | PUT   |
     When I send request with request body
     Then I get status code and response
