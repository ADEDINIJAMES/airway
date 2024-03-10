package org.airway.airwaybackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.airway.airwaybackend.enums.FlightDirection;
import org.airway.airwaybackend.enums.FlightStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchDto {
    private Long id;
    private FlightDirection flightDirection;
    private FlightStatus flightStatus;
    private String flightNo;
    @JsonProperty("airline")
    private String airline;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private long duration;
private String  arrivalPortName;
    private String departurePortName;
    private List<ClassDto> classes;
}
