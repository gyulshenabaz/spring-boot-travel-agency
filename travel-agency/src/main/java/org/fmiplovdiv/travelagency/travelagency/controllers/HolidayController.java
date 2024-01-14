package org.fmiplovdiv.travelagency.travelagency.controllers;

import org.fmiplovdiv.travelagency.travelagency.dtos.CreateHolidayDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.ResponseHolidayDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.UpdateHolidayDTO;
import org.fmiplovdiv.travelagency.travelagency.services.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holidays")
public class HolidayController {
    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/{holidayId}")
    public ResponseEntity<ResponseHolidayDTO> getHolidayById(@PathVariable("holidayId") Long id) {
        ResponseHolidayDTO response = holidayService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseHolidayDTO>> getAllHolidays(
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "duration", required = false) Integer duration) {
        List<ResponseHolidayDTO> response = holidayService.getAllHolidays(location, startDate, duration);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseHolidayDTO> createHoliday(@RequestBody CreateHolidayDTO toCreate) {
        ResponseHolidayDTO response = holidayService.createHoliday(toCreate);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ResponseHolidayDTO> updateHoliday(@RequestBody UpdateHolidayDTO toUpdate) {
        ResponseHolidayDTO response = holidayService.updateHoliday(toUpdate);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{holidayId}")
    public ResponseEntity<Boolean> deleteHoliday(@PathVariable("holidayId") Long id) {
        Boolean deletionSuccessful = holidayService.deleteHoliday(id);
        return ResponseEntity.ok(deletionSuccessful);
    }
}
