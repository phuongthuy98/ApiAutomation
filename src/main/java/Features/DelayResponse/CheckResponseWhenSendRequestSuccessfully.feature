#Author: Phuong Thuy
@ListUsersApi
Feature: Check response when send request successfully

  @HappyCase
  Scenario: Check response when send request successfully
    Given I have valid URL and method
      | URL                             | method |
      | https://reqres.in/api/users?delay=3 | GET    |
    When I send request
    Then Response returns StatusCode