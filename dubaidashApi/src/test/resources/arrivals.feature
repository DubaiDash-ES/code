Feature: Visualize arrivals on the map

  Scenario Outline: Visualize arrivals
    Given a new state with Icao24 <Icao24> and origin country <Origin_country> and a value representing if plane is on the ground <on_ground>
    When the consumer will send an event if it is an arrival
    Then the airplane Icao24, origin country and on ground with <Icao24>, <Origin_country> and <on_ground>, respectively, should be an arrival
  
  Examples:
            | Icao24 | Origin_country   | on_ground |
            | 111111 | Portugal         | true      |
            | 222222 | Spain            | true      |
            | 333333 | Germany          | true      |