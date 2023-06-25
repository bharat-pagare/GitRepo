Feature: Adding a Employee

@AddEmp
  Scenario Outline: Verifying the addition of a Employee
    Given User has correct URLs and correct body as "<name>" and "<job>"
    When User calls "AddEmployee" API using "POST" method
    Then API call is success and Response code is 201
    And New Employee details displayed are Name "name" and job is "job"

    Examples:
    |name|job|
    |Mohan|Scientist|
    |Rahul|JRF      |

@UpdateEmp
    Scenario: Verifying the update of the employee data
      Given Given User has correct URLs and correct body as "name" and "job"
      When User calls "UpdateEmployee" API using "PUT" method
      Then API call is success and Response code is 200
      And New Employee details displayed are Name "name" and job is "job"