@call_model:ring-all @floor-supervisor @consultation-listen @bug @GEMMA-12041 @monitor-agent
Feature:Consultation-Listen
  This feature file check CALLER-INFO, component for the following:
  1. Listen Conference

  Background:
    Given "AgentARingAll" logins to GEMMA as "Call Taker"
    And "AgentBRingAll" logins to GEMMA as "Floor Supervisor"
    And "AgentARingAll" receives & answers call from the originating number "RingAllOriginating"

  @CallerInfo @Regression @AdaptationsNeeded
  Scenario: AgentB listens an active call as Floor Supervisor.
    When "AgentB" listens a call as Floor Supervisor
    Then  Caller info header should be "9-1-1 CALL (SUPERVISOR IS LISTENING)" in "AgentA"

  @testmonitor
  Scenario: Supervisor sees the listen header in the Agent Tab.
    When "AgentBRingAll" listens a call as Floor Supervisor
    And "AgentBRingAll" clicks agent tab
    Then listen header "is" displayed in the "AgentBRingAll" Agent Tab

  @testmonitor
  Scenario: Supervisor sees the listen header after AgentA releases the call.
    When"AgentBRingAll" listens a call as Floor Supervisor
    And "AgentBRingAll" clicks agent tab
    And "AgentARingAll" releases the call
    Then listen header "is" displayed in the "AgentBRingAll" Agent Tab

  @CallerInfo @testmonitor
  Scenario: Supervisor listens to a 911 call and verifies caller info.
    When "AgentBRingAll" clicks listen as Floor Supervisor for "AgentARingAll agentUsername" through agents tab
    Then Caller info header displays "911" "" "SUPERVISOR_IS_LISTENING" information in "AgentARingAll"
      | Participant1       | Participant2  |
      | RingAllOriginating | AgentBRingAll |
    And Synopsis panel has "green" background color in "AgentARingAll"
    * Synopsis panel should display the correct header for "" "SUPERVISOR_LISTEN" with "911" in "AgentBRingAll"
    * Synopsis panel has "grey" background color in "AgentBRingAll"

  @testmonitor
  Scenario: Listen button is activated in the Agent Tab while listening.
    When "AgentBRingAll" listens a call as Floor Supervisor
    Then Caller info header displays "" "" "SUPERVISOR_IS_LISTENING" information in "AgentARingAll"
      | Participant1 | Participant2  |
      | originating  | AgentBRingAll |
    And "AgentBRingAll" clicks agent tab
    And Synopsis panel has "green" background color in "AgentARingAll"
    And Synopsis panel should display the correct header for "" "SUPERVISOR_LISTEN" with "911" in "AgentBRingAll"
    And Synopsis panel has "grey" background color in "AgentBRingAll"
    And Listen button in the Agent Tab "is" activated immediately in "AgentBRingAll" Agent Tab

  @testmonitor
  Scenario: Listen button is deactivated after releasing the call.
    When "AgentBRingAll" listens a call as Floor Supervisor
    Then Caller info header displays "" "" "SUPERVISOR_IS_LISTENING" information in "AgentARingAll"
      | Participant1 | Participant2  |
      | originating  | AgentBRingAll |
    And "AgentBRingAll" clicks agent tab
    And Synopsis panel has "green" background color in "AgentARingAll"
    And Synopsis panel should display the correct header for "" "SUPERVISOR_LISTEN" with "911" in "AgentBRingAll"
    And Synopsis panel has "grey" background color in "AgentBRingAll"
    When "AgentARingAll" releases the call
    Then Listen button in the Agent Tab "is not" activated immediately in "AgentBRingAll" Agent Tab

  @testmonitor
  Scenario: Double click deactivates listening for the supervisor.
    When "AgentBRingAll" listens a call as Floor Supervisor
    Then Caller info header displays "" "" "SUPERVISOR_IS_LISTENING" information in "AgentARingAll"
      | Participant1 | Participant2  |
      | originating  | AgentBRingAll |
    And "AgentBRingAll" clicks agent tab
    And Synopsis panel has "green" background color in "AgentARingAll"
    And Synopsis panel should display the correct header for "" "SUPERVISOR_LISTEN" with "911" in "AgentBRingAll"
    And Synopsis panel has "grey" background color in "AgentBRingAll"
    Then "AgentBRingAll" double clicks the existing listen button "is not" visible after leaving from the listening action
    And Synopsis panel has "grey" background color in "AgentARingAll"

  @testmonitor
  Scenario: Listen button is not activated during call hold.
    When "AgentBRingAll" listens a call as Floor Supervisor
    And "AgentARingAll" puts the call on private hold
    Then Listen button in the Agent Tab "is not" activated immediately in "AgentBRingAll" Agent Tab

  @testmonitor
  Scenario: Listen circle is visible after unholding the call.
    When "AgentBRingAll" listens a call as Floor Supervisor
    And "AgentARingAll" puts the call on private hold
    And "AgentARingAll" unholds the call
    Then Listen button in the Agent Tab "is" activated immediately in "AgentBRingAll" Agent Tab