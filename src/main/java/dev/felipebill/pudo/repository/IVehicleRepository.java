package dev.felipebill.pudo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.felipebill.pudo.model.Vehicle;

@Repository
public interface IVehicleRepository extends CrudRepository<Vehicle, Long> {

	
	
}
