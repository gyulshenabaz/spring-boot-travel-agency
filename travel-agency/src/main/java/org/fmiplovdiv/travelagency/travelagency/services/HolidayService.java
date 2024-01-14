package org.fmiplovdiv.travelagency.travelagency.services;

import org.fmiplovdiv.travelagency.travelagency.dtos.CreateHolidayDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.ResponseHolidayDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.UpdateHolidayDTO;
import org.fmiplovdiv.travelagency.travelagency.entities.Holiday;
import org.fmiplovdiv.travelagency.travelagency.repositories.HolidayRepository;
import org.fmiplovdiv.travelagency.travelagency.repositories.LocationRepository;
import org.fmiplovdiv.travelagency.travelagency.services.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;

    private final LocationRepository locationRepository;

    private final ModelMapper modelMapper;

    public HolidayService(HolidayRepository holidayRepository, LocationRepository locationRepository, ModelMapper modelMapper) {
        this.holidayRepository = holidayRepository;
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseHolidayDTO createHoliday(CreateHolidayDTO createHolidayDTO) {
        if (!locationRepository.existsById(createHolidayDTO.getLocation())) {
            throw new NotFoundException("Location with this ID doesn't exist");
        }

        Holiday holiday = new Holiday();
        holiday.setLocation(locationRepository.findById(createHolidayDTO.getLocation()).get());
        holiday.setTitle(createHolidayDTO.getTitle());
        holiday.setStartDate(createHolidayDTO.getStartDate());
        holiday.setDuration(createHolidayDTO.getDuration());
        holiday.setPrice(createHolidayDTO.getPrice());
        holiday.setFreeSlots(createHolidayDTO.getFreeSlots());

        holiday = holidayRepository.save(holiday);

        ResponseHolidayDTO holidayDTO = this.modelMapper.map(holiday, ResponseHolidayDTO.class);

        return holidayDTO;
    }

    public List<ResponseHolidayDTO> getAllHolidays(String location, String startDate, Integer duration) {
        List<Holiday> holidays = holidayRepository.findAll();

        holidays = customFilterHolidaysByLocation(holidays, location);
        holidays = customFilterHolidaysByStartDate(holidays, startDate);
        holidays = customFilterHolidaysByDuration(holidays, duration);

        var allHolidays = holidays.stream().map(h->modelMapper.map(h, ResponseHolidayDTO.class)).collect(
                Collectors.toList());

        return allHolidays;
    }

    public ResponseHolidayDTO getById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElse(null);

        if (holiday == null) {
            throw new NotFoundException("Holiday with this ID doesn't exist");
        }

        ResponseHolidayDTO holidayDTO = this.modelMapper.map(holiday, ResponseHolidayDTO.class);

        return holidayDTO;
    }

    public ResponseHolidayDTO updateHoliday(UpdateHolidayDTO updateHolidayDTO) {
        Holiday holiday = holidayRepository.findById(updateHolidayDTO.getId()).orElse(null);

        if (holiday == null) {
            throw new NotFoundException("Holiday with this ID doesn't exist");
        }

        if (!locationRepository.existsById(updateHolidayDTO.getLocation())) {
            throw new NotFoundException("Location with this ID doesn't exist");
        }

        holiday.setLocation(locationRepository.findById(updateHolidayDTO.getLocation()).get());
        holiday.setTitle(updateHolidayDTO.getTitle());
        holiday.setStartDate(updateHolidayDTO.getStartDate());
        holiday.setDuration(updateHolidayDTO.getDuration());
        holiday.setPrice(updateHolidayDTO.getPrice());
        holiday.setFreeSlots(updateHolidayDTO.getFreeSlots());

        ResponseHolidayDTO holidayDTO = this.modelMapper.map(holiday, ResponseHolidayDTO.class);

        return holidayDTO;

    }

    public boolean deleteHoliday(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElse(null);

        if (holiday == null) {
            throw new NotFoundException("Holiday with this ID doesn't exist");
        }

        holidayRepository.deleteById(id);
        return true;
    }

    /* Customize Filters for Holidays */
    private List<Holiday> customFilterHolidaysByLocation(List<Holiday> holidays, String locationFilter) {
        if (locationFilter != null && !locationFilter.isEmpty()) {
            holidays = holidays.stream()
                    .filter(h -> h.getLocation().getCity().equalsIgnoreCase(locationFilter) ||
                            h.getLocation().getCountry().equalsIgnoreCase(locationFilter))
                    .collect(Collectors.toList());
        }
        return holidays;
    }

    private List<Holiday> customFilterHolidaysByStartDate(List<Holiday> holidays, String startFilterDate) {
        if (startFilterDate != null && !startFilterDate.isEmpty()) {
            holidays = holidays.stream()
                    .filter(h -> h.getStartDate().isEqual(LocalDate.parse(startFilterDate)))
                    .collect(Collectors.toList());
        }
        return holidays;
    }

    private List<Holiday> customFilterHolidaysByDuration(List<Holiday> holidays, Integer durationFilter) {
        if (durationFilter != null) {
            holidays = holidays.stream()
                    .filter(h -> h.getDuration() == durationFilter)
                    .collect(Collectors.toList());
        }
        return holidays;
    }
}