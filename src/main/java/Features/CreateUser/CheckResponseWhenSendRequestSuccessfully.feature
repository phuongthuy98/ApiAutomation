#Author: PhuongThuy
@CreateUserApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario Outline: Check response when send request successfully
    Given I have valid URL and method
      | URL                         | Method |
      | https://reqres.in/api/users | POST   |
    Given I have valid request body
      | RequestBodyName                        |
      | /CreateUserApi/CreateUserRequestBody.json |
    When I send request
    Then I get <statusCode>

    Examples:
      | statusCode |
      | 201        |
