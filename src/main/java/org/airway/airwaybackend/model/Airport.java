package org.airway.airwaybackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Airport {

    @Id
    private String iataCode;
    private String name;
    private String icaoCode;
    private String city;
    private String operationalHrs;
    private String state;
    @ManyToMany
    List<Flight> flights;

    @ManyToMany(mappedBy = "airports", fetch = FetchType.EAGER)
    private Set<Airline> airlines;


    public Airport(String value, String value1, String value2, String value3, String value4, String value5) {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hash(iataCode);
        return result;
    }

}