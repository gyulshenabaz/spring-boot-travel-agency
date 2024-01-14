package org.fmiplovdiv.travelagency.travelagency.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResponseHolidayDTO {

    private Long id;

    private ResponseLocationDTO location;

    private String title;

    private LocalDate startDate;

    private int duration;

    private double price;

    private int freeSlots;
}