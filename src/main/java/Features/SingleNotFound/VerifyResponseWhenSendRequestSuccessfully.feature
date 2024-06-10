#Author: PhuongThuy

@SingleUserApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario Outline: Check response when send request successfully
    Given I have valid URL and method
      | URL                              | Method |
      | https://reqres.in/api/unknown/23 | GET    |
    When I send request
    Then I get <statusCode>

    Examples:
      | statusCode |
      | 404        |