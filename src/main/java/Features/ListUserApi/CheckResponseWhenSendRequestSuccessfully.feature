#Author: PhuongThuy


@ListUserApi
Feature: Check response when send request successfully
  @HappyCase
  Scenario: Check response when send request successfully
    Given I have valid URL and method
    |URL|Method|
    |https://reqres.in/api/users?page=2|GET|
    When I send request
    Then I get status code and response