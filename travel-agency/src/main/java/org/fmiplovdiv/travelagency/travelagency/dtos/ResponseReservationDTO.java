package org.fmiplovdiv.travelagency.travelagency.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseReservationDTO {
    private Long id;

    private String contactName;

    private String phoneNumber;

    private ResponseHolidayDTO holiday;
}
