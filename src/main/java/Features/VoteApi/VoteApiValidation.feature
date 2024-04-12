#Author: PhuongThuy

@BreedApi
Feature: Breed Api Validation

  @ValidationCase
  Scenario Outline: Breed Api Validation Cases
    Given I have valid URL and method and request body
      | URL                                | Method |RequestBodyName|
      | https://api.thecatapi.com/v1/votes | POST   |/VoteApi/VoteRequestBody.json|
    When I send request with request body include "<fieldName>" and "<value>"
    Then I get <expectedStatusCode> and "<expectedMessage>"

    Examples:
      | fieldName | value   | expectedStatusCode | expectedMessage                       |
      | image_id  | null    | 404                | image_id must be a string           |
      | image_id  | missing | 400                | image_id is required                |
      | image_id  | ""      | 400                | image_id is not allowed to be empty |
      | value     | null    | 201                | SUCCESS                               |
      | value     | missing | 404                | value is required                   |
      | value     | ""      | 201                | SUCCESS                               |
      | sub_id    | null    | 404                | sub_id must be a string             |
      | sub_id    | missing | 201                | SUCCESS                               |
      | sub_id    | ""      | 404                | sub_id is not allowed to be empty   |