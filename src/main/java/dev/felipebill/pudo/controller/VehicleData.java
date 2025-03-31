package dev.felipebill.pudo.controller;

import dev.felipebill.pudo.model.Vehicle;

public record VehicleData(Long id, String driversName, String plateNumber, Integer capacity) {

	public static VehicleData fromVehicle(final Vehicle vehicle) {
		return new VehicleData(vehicle.getId(), vehicle.getDriversName(), vehicle.getPlateNumber().getValue(),
				vehicle.getCapacity());
	}

}
