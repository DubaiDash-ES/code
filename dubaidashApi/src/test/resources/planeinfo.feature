Feature: Visualize plane info on map

  Scenario Outline: Visualize plane info
    Given a new state with Icao24 <Icao24> and origin country <Origin_country> and velocity <velocity>
    When the user checks the airplane info
    Then the airplane Icao24, origin country and velocity should be <Icao24>, <Origin_country> and <velocity>, respectively

  Examples:
            | Icao24 | Origin_country | velocity |
            | 111111 | Portugal       | 150      |
            | 222222 | Spain          | 50       |
            | 333333 | Qatar          | 130      |