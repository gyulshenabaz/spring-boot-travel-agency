package org.fmiplovdiv.travelagency.travelagency.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateHolidayDTO {
    private Long id;

    private Long location;

    private String title;

    private LocalDate startDate;

    private int duration;

    private double price;

    private int freeSlots;
}
