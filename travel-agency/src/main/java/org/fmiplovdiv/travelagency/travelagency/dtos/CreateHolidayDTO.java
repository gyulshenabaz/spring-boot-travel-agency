package org.fmiplovdiv.travelagency.travelagency.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateHolidayDTO {
    private Long location;

    private String title;

    private LocalDate startDate;

    private int duration;

    private double price;

    private int freeSlots;
}
