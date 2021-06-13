Feature: Visualize the most popular country

  Scenario: Visualize most popular country
    Given states with Icao24 and origin country
    When the user opens the web ui
    Then he can visualize the most popular country