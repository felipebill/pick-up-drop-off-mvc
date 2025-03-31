package dev.felipebill.pudo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.felipebill.pudo.controller.form.NewVehicleForm;
import dev.felipebill.pudo.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	final VehicleService vehicleService;
	
	public VehicleController(final VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	@PostMapping
	void registerNewVehicle(@RequestBody NewVehicleForm form){
		
	}
	
}
