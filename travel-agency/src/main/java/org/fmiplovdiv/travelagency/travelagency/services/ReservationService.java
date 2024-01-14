package org.fmiplovdiv.travelagency.travelagency.services;

import org.fmiplovdiv.travelagency.travelagency.dtos.CreateReservationDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.ResponseReservationDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.UpdateReservationDTO;
import org.fmiplovdiv.travelagency.travelagency.entities.Reservation;
import org.fmiplovdiv.travelagency.travelagency.repositories.HolidayRepository;
import org.fmiplovdiv.travelagency.travelagency.repositories.ReservationRepository;
import org.fmiplovdiv.travelagency.travelagency.services.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final HolidayRepository holidayRepository;

    private final ModelMapper modelMapper;

    public ReservationService(ReservationRepository reservationRepository, HolidayRepository holidayRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.holidayRepository = holidayRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        if (reservation == null) {
            throw new NotFoundException("Reservation with this ID doesn't exist");
        }

        ResponseReservationDTO responseReservationDTO  = this.modelMapper.map(reservation, ResponseReservationDTO.class);

        return responseReservationDTO;
    }

    public List<ResponseReservationDTO> getAllReservations(String phoneNumber) {
        List<Reservation> reservations = reservationRepository.findAll();

        reservations = customFilterReservationsByPhone(reservations, phoneNumber);

        var allReservations = reservations.stream().map(r->modelMapper.map(r, ResponseReservationDTO.class)).collect(
                Collectors.toList());

        return allReservations;
    }

    public ResponseReservationDTO createReservation(CreateReservationDTO createReservationDTO) {
        if (!holidayRepository.existsById(createReservationDTO.getHoliday())) {
            throw new NotFoundException("Holiday with this ID doesn't exist");
        }

        Reservation reservation = new Reservation();
        reservation.setContactName(createReservationDTO.getContactName());
        reservation.setPhoneNumber(createReservationDTO.getPhoneNumber());
        reservation.setHoliday(holidayRepository.findById(createReservationDTO.getHoliday()).get());

        reservation = reservationRepository.save(reservation);

        ResponseReservationDTO responseReservationDTO  = this.modelMapper.map(reservation, ResponseReservationDTO.class);

        return responseReservationDTO;
    }

    public ResponseReservationDTO updateReservation(UpdateReservationDTO updateReservationDTO) {
        Reservation reservation = reservationRepository.findById(updateReservationDTO.getId()).orElse(null);

        if (reservation == null) {
            throw new NotFoundException("Reservation with this ID doesn't exist");
        }

        reservation.setContactName(updateReservationDTO.getContactName());
        reservation.setPhoneNumber(updateReservationDTO.getPhoneNumber());
        reservation.setHoliday(holidayRepository.findById(updateReservationDTO.getHoliday()).get());

        reservation = reservationRepository.save(reservation);

        ResponseReservationDTO responseReservationDTO  = this.modelMapper.map(reservation, ResponseReservationDTO.class);

        return responseReservationDTO;
    }

    public boolean deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        if (reservation == null) {
            throw new NotFoundException("Reservation with this ID doesn't exist");
        }

        reservationRepository.deleteById(id);
        return true;
    }

    private List<Reservation> customFilterReservationsByPhone(List<Reservation> reservations, String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            reservations = reservations.stream()
                    .filter(r -> r.getPhoneNumber().equals(phoneNumber))
                    .collect(Collectors.toList());
        }
        return reservations;
    }
}