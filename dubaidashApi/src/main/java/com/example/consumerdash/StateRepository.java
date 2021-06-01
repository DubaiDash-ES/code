package com.example.consumerdash;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateRepository extends CrudRepository<State, Long> {
    List<State> findByIcao24 (String icao24);
    List<State> findAll ();
    @Query(value = "SELECT * from state WHERE origin_country=?1 ", nativeQuery = true)
    List<State> findByorigin_country(String origin_country);
}
