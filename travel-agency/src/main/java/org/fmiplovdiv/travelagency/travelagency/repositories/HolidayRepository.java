package org.fmiplovdiv.travelagency.travelagency.repositories;

import org.fmiplovdiv.travelagency.travelagency.entities.Holiday;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends ListCrudRepository<Holiday, Long> {

}