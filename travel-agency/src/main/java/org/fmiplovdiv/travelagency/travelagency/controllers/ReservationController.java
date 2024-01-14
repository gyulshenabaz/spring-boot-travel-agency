package org.fmiplovdiv.travelagency.travelagency.controllers;

import org.fmiplovdiv.travelagency.travelagency.dtos.CreateReservationDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.ResponseReservationDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.UpdateReservationDTO;
import org.fmiplovdiv.travelagency.travelagency.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseReservationDTO>> getAllReservation(
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber) {
        List<ResponseReservationDTO> response = reservationService.getAllReservations(phoneNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ResponseReservationDTO> getReservationById(@PathVariable("reservationId") Long id) {
        ResponseReservationDTO response = reservationService.getReservationById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseReservationDTO> createReservation(@RequestBody CreateReservationDTO toCreate) {
        ResponseReservationDTO response = reservationService.createReservation(toCreate);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ResponseReservationDTO> updateLocation(@RequestBody UpdateReservationDTO toUpdate) {
        ResponseReservationDTO response = reservationService.updateReservation(toUpdate);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Boolean> deleteReservation(@PathVariable("reservationId") Long id) {
        Boolean response = reservationService.deleteReservation(id);
        return ResponseEntity.ok(response);
    }
}