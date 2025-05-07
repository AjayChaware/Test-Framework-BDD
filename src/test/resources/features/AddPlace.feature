Feature: Validating place APIs

  Scenario: Verify the place is added successfully
    Given Payload with AddPlaceApi
    When user sends the Post request to "AddPlaceAPI" and method is "Post"
    Then response is success with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"

    @Addplace
  Scenario Outline: Verify the places are added successfully
    Given Payload with AddPlaceApi with values "<Name>", "<Phone Number>","<Address>"
    When user sends the Post request to "AddPlaceAPI" and method is "Post"
    Then response is success with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And verify using "GetPlaceAPI" the place_id maps to the "<Name>" created
    Examples:
      | Name        | Phone Number | Address  |
      | HallRoom    | 123456791    | Yavatmal |
#      | Books Store | 9876543217   | Pune     |
#      | BandStand   | 6547893215   | Mumbai   |

  @DeletePlace
    Scenario: Verify the Delete APi works Successfully
      Given Payload with DeletePlaceAPI
      When user sends the Post request to "DeletePlaceAPI" and method is "Post"
      Then response is success with status code 200
      And "status" in response is "OK"
