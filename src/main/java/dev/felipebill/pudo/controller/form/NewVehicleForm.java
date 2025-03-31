package dev.felipebill.pudo.controller.form;

import dev.felipebill.pudo.model.Plate;
import dev.felipebill.pudo.model.Vehicle;

public record NewVehicleForm(String driversName, String plateNumber, Integer capacity) {

	public Vehicle toVehicle() {
		var plate = new Plate(plateNumber);
		Vehicle vehicle = Vehicle.builder().plate(plate).capacity(capacity).driversName(driversName).build();
		return vehicle;
	}

}
