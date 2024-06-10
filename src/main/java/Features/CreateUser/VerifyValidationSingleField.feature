#Author: PhuongThuy
@CreateUserApi
Feature: Check response when send request successfully

  @ValidationCase
  Scenario Outline: Verify validation single field
    Given I have valid URL and method
      | URL                         | Method |
      | https://reqres.in/api/users | POST   |
    Given I have valid request body
      | RequestBodyName                        |
      | /CreateUserApi/CreateUserRequestBody.json |
    When I send request with request body include "<fieldName>" and "<value>"
    Then I get <expectedStatusCode> and "<expectedMessage>"

    Examples:
      | fieldName | value   | expectedStatusCode | expectedMessage   |
      | name      | null    | 404                | Name is invalid.  |
      | name      | missing | 404                | Name is required. |
      | name      | ""      | 404                | Name is invalid.  |
      | name      | 1       | 404                | Name is invalid.  |
      | job       | null    | 404                | Job is invalid.   |
      | job       | missing | 404                | Job is required.  |
      | job       | ""      | 404                | Job is invalid.   |
      | job       | 1       | 404                | Job is invalid.   |
