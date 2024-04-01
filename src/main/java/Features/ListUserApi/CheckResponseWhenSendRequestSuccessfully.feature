#Author: PhuongThuy

@ListUsersApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario Outline: Check response when send request successfully
    Given I have valid URL and method
      | URL                                | Method |
      | https://reqres.in/api/users?page=2 | GET    |
    When I send request
    Then I get <statusCode> and response
    Then I get response body with <page>

    Examples:
      | statusCode | page |
      | 200        | 2    |