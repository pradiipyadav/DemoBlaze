@Pet
Feature: PetStore Demo

  Scenario: Get available Pets
    Given I have access to "pet", "findByStatus"
    When I request for all "available" pets
    Then I should see all the available pets

  Scenario: Post new Pet and delete the pet
    Given I have access to "pet"
    When I Post a pets
    Then I should see Pet added
    When I update pet status to "Sold"
    Then Pet status should be updated
    When I delete the new pet
    Then Pet should be deleted
