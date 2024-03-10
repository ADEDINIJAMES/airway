package org.airway.airwaybackend.service;

import org.airway.airwaybackend.dto.FlightSearchDto;
import org.airway.airwaybackend.dto.FlightSearchResponse;
import org.airway.airwaybackend.enums.FlightDirection;
import org.airway.airwaybackend.enums.FlightStatus;
import org.airway.airwaybackend.model.Airport;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FlightService {
    String deleteFlight(Long Id);
     Map<String,FlightSearchResponse> searchAvailableFlight(Airport departurePort, Airport arrivalPort, LocalDate departureDate, FlightDirection flightDirection, LocalDate returnDate, int noOfAdult, int noOfChildren, int noOfInfant) ;

}
