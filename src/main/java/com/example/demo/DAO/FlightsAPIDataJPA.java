package com.example.demo.DAO;

import com.example.demo.Models.FlightDBModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FlightsAPIDataJPA extends JpaRepository <FlightDBModel, Integer> {

    @Query(value = "select * from flights_from_api where is_deleted=false and dep_airport=?1 and arrival_airport = ?2", nativeQuery = true)
    List<FlightDBModel> findAllRoute(String from, String to);

    @Transactional
    @Modifying
    @Query(value = "update flights_from_api set is_deleted=true where dep_airport=?1 and arrival_airport = ?2", nativeQuery = true)
    void deleteAllRoute(String from, String to);

}
