#Author: PhuongThuy

@BreedApi
Feature: Breed Api Validation

  @HappyCase
  Scenario Outline: Breed Api Validation
    Given I have valid URL and method
      | URL                         | Method |
      | https://api.thecatapi.com/v1/votes | POST   |
    Given I have valid request body
      | RequestBodyName                        |
      | /VoteApi/VoteRequestBody.json |
    When I send request with request body include "<FieldName>" and "<Value>"
    Then I get <ExpectedStatusCode> and "<ExpectedMessage>"

    Examples:
      | FieldName | Value   | ExpectedStatusCode | ExpectedMessage                       |
      | image_id  | null    | 400                | "image_id" must be a string           |
      | image_id  | missing | 400                | "image_id" is required                |
      | image_id  | ""      | 400                | "image_id" is not allowed to be empty |
      | value     | null    | 201                | SUCCESS                               |
      | value     | missing | 404                | "value" is required                   |
      | value     | ""      | 201                | SUCCESS                               |
      | sub_id    | null    | 404                | "sub_id" must be a string             |
      | sub_id    | missing | 201                | SUCCESS                               |
      | sub_id    | ""      | 404                | "sub_id" is not allowed to be empty   |