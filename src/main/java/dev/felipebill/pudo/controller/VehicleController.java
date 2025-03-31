package dev.felipebill.pudo.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	ResponseEntity<VehicleData> registerNewVehicle(@RequestBody NewVehicleForm form,
			UriComponentsBuilder uriComponentsBuilder) {
		var vehicle = form.toVehicle();
		this.vehicleService.registerNewVehicle(vehicle);
		var data = VehicleData.fromVehicle(vehicle);

		URI uri = uriComponentsBuilder.path("/vehicles/{id}").buildAndExpand(data.id()).toUri();
		return ResponseEntity.created(uri).body(data);
	}

}
