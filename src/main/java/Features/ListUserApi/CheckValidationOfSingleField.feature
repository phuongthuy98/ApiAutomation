#Author: PhuongThuy


@ListUserApi
Feature: Check validation of single field
  @UnHappyCase
  Scenario: Check validation of single field
    Given I have valid URL and invalid method
    When I send request
    Then I get status code and response
