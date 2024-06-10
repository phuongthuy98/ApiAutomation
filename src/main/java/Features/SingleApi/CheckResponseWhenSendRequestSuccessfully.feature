#Author: Phuong Thuy
@ListUsersApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario Outline: Check response when send request successfully
    Given I have valid URL and method
      | URL                             | Method |
      | https://reqres.in/api/unknown/2 | GET    |
    When I send request
    Then I get <statusCode>

    Examples:
      | statusCode |
      | 200        |
