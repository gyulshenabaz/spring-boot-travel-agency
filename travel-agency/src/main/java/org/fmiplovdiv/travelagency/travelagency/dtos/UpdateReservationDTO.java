package org.fmiplovdiv.travelagency.travelagency.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateReservationDTO {
    private Long id;

    private String contactName;

    private String phoneNumber;

    private Long holiday;
}
