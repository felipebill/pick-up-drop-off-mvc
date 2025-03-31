package dev.felipebill.pudo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dev.felipebill.pudo.model.Vehicle;

@Repository
public interface IVehicleRepository extends CrudRepository<Vehicle, Long>, PagingAndSortingRepository<Vehicle, Long> {

	@Query("SELECT v FROM Vehicle v WHERE v.plate.value = :plateNumber")
	Optional<Vehicle> findByPlateNumber(String plateNumber);

}
