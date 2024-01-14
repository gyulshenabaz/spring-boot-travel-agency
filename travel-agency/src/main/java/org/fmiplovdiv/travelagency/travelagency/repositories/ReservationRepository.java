package org.fmiplovdiv.travelagency.travelagency.repositories;

import org.fmiplovdiv.travelagency.travelagency.entities.Reservation;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends ListCrudRepository<Reservation, Long> {

}