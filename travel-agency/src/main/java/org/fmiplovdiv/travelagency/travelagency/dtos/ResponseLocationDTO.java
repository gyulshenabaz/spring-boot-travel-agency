package org.fmiplovdiv.travelagency.travelagency.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseLocationDTO {

    private Long id;

    private String street;

    private String number;

    private String city;

    private String country;

    private String imageUrl;
}