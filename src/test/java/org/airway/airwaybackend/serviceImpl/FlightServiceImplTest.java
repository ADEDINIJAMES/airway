package org.airway.airwaybackend.serviceImpl;

import org.airway.airwaybackend.dto.FlightSearchDto;
import org.airway.airwaybackend.dto.FlightSearchResponse;
import org.airway.airwaybackend.enums.FlightDirection;
import org.airway.airwaybackend.exception.FlightNotFoundException;
import org.airway.airwaybackend.model.*;
import org.airway.airwaybackend.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FlightServiceImplTest {
    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightServiceImp;
    @Spy
    public  List<FlightSearchDto> flightDTOList;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
    }
    @Test
    void TestSearchAvailableFlight() {
        Airport departurePort = new Airport();
        Airport arrivalPort = new Airport();
        List<Classes> classesList = new ArrayList<>();
        Classes classes = new Classes();
        classes.setId(1L);
        classes.setClassName("Economy");
        classes.setTotalPrice(BigDecimal.valueOf(100000));

        Classes classes1 = new Classes();
        classes.setId(2L);
        classes.setClassName("Business");
        classes.setTotalPrice(BigDecimal.valueOf(100000));
        classesList.add(classes);
        classesList.add(classes1);

        LocalDate departureDate = LocalDate.of(2024, 2, 25);
        LocalDate returnDate2= LocalDate.of(2024, 3, 25);
        arrivalPort.setIataCode("YOL");
        departurePort.setIataCode("ABV");
        LocalDate returnDate1 = null;
        Duration duration = Duration.ofMinutes(23);
        LocalTime timeDeparture = LocalTime.of(6,0,0);
        LocalTime timeArrival = timeDeparture.plusMinutes(duration.toMinutes());
        int noOfAdult = 2;
        FlightSearchResponse flightSearchResponse;
        int noOfChildren = 1;
        int noOfInfant = 0;
        Flight flight1 = new Flight();
        flight1.setId(1L);
        Flight flight2 = new Flight();
        Airline airline = new Airline();
        airline.setName("Dana");
        flight2.setAirline(airline);
        flight1.setAirline(airline);
        flight2.setId(2L);
        flight1.setArrivalPort(arrivalPort);
        flight1.setDeparturePort(departurePort);
        flight2.setArrivalPort(arrivalPort);
        flight2.setDeparturePort(departurePort);
        flight2.setDepartureDate(returnDate2);
        flight1.setDepartureDate(departureDate);
        FlightDirection flightDirection1 = FlightDirection.ROUND_TRIP;
        Seat seat = new Seat();
        seat.setId(1L);
        for(Classes classes2: classesList){
            classes2.setSeat(seat);
        }

        flight1.setClasses(classesList);
        flight2.setClasses(classesList);
        when (flightRepository.searchByDeparturePortAndArrivalPortAndDepartureDateAndNoOfAdultGreaterThanEqualAndNoOfChildrenGreaterThanEqualAndNoOfInfantGreaterThanEqual(departurePort,arrivalPort,departureDate,noOfAdult,noOfChildren,noOfInfant)).thenReturn(Collections.singletonList(flight1));
        when(flightRepository.searchByDeparturePortAndArrivalPortAndDepartureDateAndNoOfAdultGreaterThanEqualAndNoOfChildrenGreaterThanEqualAndNoOfInfantGreaterThanEqual(departurePort,arrivalPort,returnDate2,noOfAdult,noOfChildren,noOfInfant)).thenReturn(Collections.singletonList(flight2));
Map< String,FlightSearchResponse> flightResponse = flightServiceImp.searchAvailableFlight(departurePort,arrivalPort,departureDate, null, null,noOfAdult,noOfChildren, noOfInfant);
//        Map< String,FlightSearchResponse> flightResponse2;
//        flightResponse2 = flightServiceImp.searchAvailableFlight(departurePort,arrivalPort,departureDate, flightDirection1,returnDate2,noOfAdult,noOfChildren, noOfInfant);


        assertNotNull(flightDTOList);
        assertFalse(flightDTOList.isEmpty());
        assertNotNull(flightResponse);
     //   assertNotNull(flightResponse2);
    //assertEquals(0, flightResponse.size());
     //   assertEquals(1, flightResponse2.size());

//        assertEquals(flight1.getId(), flightDTOList.get(0).getId());
//       assertEquals(flight2.getId(), flightDTOList.get(1).getId());

    }

    @Test
    void TestSearchAvailableFlight_NoFlightsFound(){
        Airport departurePort = new Airport();
        Airport arrivalPort = new Airport();
        LocalDate departureDate = LocalDate.of(2024, 2, 25);
        LocalDate arrivalDate2= departureDate.plusDays(1);
        LocalDate returnDate2 = LocalDate.of(2024, 3, 25);
        LocalDate returnDate1 = null;
        LocalDate arrivalDate1 = null;
        Duration duration = Duration.ofMinutes(23);
        LocalTime timeDeparture = LocalTime.of(6,0,0);
        LocalTime timeArrival = timeDeparture.plusMinutes(duration.toMinutes());
        int noOfAdult = 2;
        int noOfChildren = 1;
        int noOfInfant = 0;
        FlightDirection flightDirection1 = FlightDirection.ROUND_TRIP;


        when(flightRepository.searchByDeparturePortAndArrivalPortAndDepartureDateAndNoOfAdultGreaterThanEqualAndNoOfChildrenGreaterThanEqualAndNoOfInfantGreaterThanEqual(departurePort,arrivalPort,departureDate,noOfAdult,noOfChildren,noOfInfant)).thenReturn(Collections.emptyList());
        when(flightRepository.searchByDeparturePortAndArrivalPortAndDepartureDateAndNoOfAdultGreaterThanEqualAndNoOfChildrenGreaterThanEqualAndNoOfInfantGreaterThanEqual(departurePort,arrivalPort,returnDate2,noOfAdult,noOfChildren,noOfInfant)).thenReturn(Collections.emptyList());

        assertThrows(FlightNotFoundException.class, ()->
                flightServiceImp.searchAvailableFlight(departurePort,arrivalPort,departureDate,flightDirection1,returnDate2,noOfAdult,noOfChildren,noOfInfant));
    }

}
