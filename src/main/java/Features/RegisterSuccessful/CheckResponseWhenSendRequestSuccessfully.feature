#Author: Phuong Thuy

@RegisterSuccessfulApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario Outline: Check response when send request successfully
    Given I have valid URL and method
      | URL                            | Method |
      | https://reqres.in/api/register | POST  |
    Given I have valid request body
      | RequestBodyName                                        |
      | /RegisterSuccessful/RegisterSuccessfulRequestBody.json |
    When I send request
    Then I get <statusCode>

    Examples:
      | statusCode |
      | 200        |