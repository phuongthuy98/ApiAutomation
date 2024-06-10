#Author: PhuongThuy

@BreedApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario Outline: Check response when send request successfully
    Given I have valid URL and method
      | URL                                           | Method |
      | https://dog.ceo/api/breed/husky/images/random | GET    |
    When I send request
    Then I get <statusCode>

    Examples:
      | statusCode |
      | 200        |