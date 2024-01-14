package org.fmiplovdiv.travelagency.travelagency.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateLocationDTO {
    private String street;

    private String number;

    private String city;

    private String country;

    private String imageUrl;
}
