#Author: Phuong Thuy
@ListUsersApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario Outline: Check response when send request successfully
    Given I have valid URL and method
      | URL                           | Method |
      | https://reqres.in/api/unknown | GET    |
    When I send request
    Then I get <statusCode>
    Then I get response body with <page>

    Examples:
      | statusCode | page |
      | 200        | 1   |
