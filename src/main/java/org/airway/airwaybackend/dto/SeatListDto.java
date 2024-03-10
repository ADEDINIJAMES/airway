package org.airway.airwaybackend.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.airway.airwaybackend.model.Passenger;
import org.airway.airwaybackend.model.Seat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatListDto {
    private Long id;
    private Long seatId;
    private String seatLabel;
    private Boolean occupied;
}
