package dev.felipebill.pudo.service;

import org.springframework.stereotype.Service;

import dev.felipebill.pudo.model.Vehicle;
import dev.felipebill.pudo.repository.IVehicleRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VehicleService {

	private IVehicleRepository vehicleRepository;
	
	public VehicleService(final IVehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	public void registerNewVehicle(Vehicle vehicle) {
		this.vehicleRepository.save(vehicle);
	}

	public Vehicle findById(Long id) {
		return this.vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id: " + id));
	}
	
	
	
	
}
