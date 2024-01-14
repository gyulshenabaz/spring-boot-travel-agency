package org.fmiplovdiv.travelagency.travelagency.repositories;

import org.fmiplovdiv.travelagency.travelagency.entities.Location;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Long> {

}