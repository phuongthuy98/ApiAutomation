#Author: PhuongThuy
@VoteApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario Outline: Check response when send request successfully
    Given I have valid URL and method
      | URL                         | Method |
      | https://api.thecatapi.com/v1/votes | POST   |
    Given I have valid request body
      | RequestBodyName                        |
      | /VoteApi/VoteRequestBody.json |
    When I send request
    Then I get <statusCode>

    Examples:
      | statusCode |
      | 201        |
