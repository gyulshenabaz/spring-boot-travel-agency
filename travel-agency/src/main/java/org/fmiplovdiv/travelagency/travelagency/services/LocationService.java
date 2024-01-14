package org.fmiplovdiv.travelagency.travelagency.services;

import org.fmiplovdiv.travelagency.travelagency.dtos.CreateLocationDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.ResponseLocationDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.UpdateLocationDTO;
import org.fmiplovdiv.travelagency.travelagency.entities.Location;
import org.fmiplovdiv.travelagency.travelagency.repositories.LocationRepository;
import org.fmiplovdiv.travelagency.travelagency.services.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    private final ModelMapper modelMapper;

    public LocationService(LocationRepository locationRepository, ModelMapper modelMapper) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseLocationDTO createLocation(CreateLocationDTO createLocationDTO) {
        Location location = new Location();

        location.setStreet(createLocationDTO.getStreet());
        location.setNumber(createLocationDTO.getNumber());
        location.setCity(createLocationDTO.getCity());
        location.setCountry(createLocationDTO.getCountry());
        location.setImageUrl(createLocationDTO.getImageUrl());

        location = locationRepository.save(location);

        ResponseLocationDTO locationDTO = this.modelMapper.map(location, ResponseLocationDTO.class);

        return locationDTO;
    }

    public List<ResponseLocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();

        var allLocations = locations.stream().map(l->modelMapper.map(l, ResponseLocationDTO.class)).collect(
                Collectors.toList());

        return allLocations;
    }

    public ResponseLocationDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id).orElse(null);

        if (location == null) {
            throw new NotFoundException("Location with this ID doesn't exist");
        }

        ResponseLocationDTO locationDTO = this.modelMapper.map(location, ResponseLocationDTO.class);

        return locationDTO;
    }

    public boolean deleteLocation(Long id) {
        Location location = locationRepository.findById(id).orElse(null);

        if (location == null) {
            throw new NotFoundException("Location with this ID doesn't exist");
        }

        locationRepository.deleteById(id);

        return true;
    }

    public ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocationDTO) {
        Location location = locationRepository.findById(updateLocationDTO.getId()).orElse(null);

        if (location == null) {
            throw new NotFoundException("Location with this ID doesn't exist");
        }

        location.setStreet(updateLocationDTO.getStreet());
        location.setNumber(updateLocationDTO.getNumber());
        location.setCity(updateLocationDTO.getCity());
        location.setCountry(updateLocationDTO.getCountry());
        location.setImageUrl(updateLocationDTO.getImageUrl());

        location = locationRepository.save(location);

        ResponseLocationDTO locationDTO = this.modelMapper.map(location, ResponseLocationDTO.class);

        return locationDTO;
    }
}
