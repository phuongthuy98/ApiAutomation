#Author: PhuongThuy

@BreedApi
Feature: Breed Api Validation

  @HappyCase
  Scenario Outline: Breed Api Validation
    Given I have valid URL and method of Breed Api
      | URL                                               | Method |
      | https://dog.ceo/api/breed/breedname/images/random | GET    |
    When I send request "<FieldName>" and "<Value>"
    Then I get <ExpectedStatusCode> and "<ExpectedMessage>"

    Examples:
      | FieldName | Value   | ExpectedStatusCode | ExpectedMessage                               |
      | breedName | missing | 404                | Breed not found (master breed does not exist) |
      | breedName | dsafds  | 404                | Breed not found (master breed does not exist) |