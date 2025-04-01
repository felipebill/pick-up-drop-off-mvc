package dev.felipebill.pudo.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dev.felipebill.pudo.controller.data.VehicleData;
import dev.felipebill.pudo.controller.form.NewVehicleForm;
import dev.felipebill.pudo.model.Vehicle;
import dev.felipebill.pudo.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleApi {

	final VehicleService vehicleService;

	public VehicleApi(final VehicleService vehicleService) {
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

	@GetMapping("/{id}")
	ResponseEntity<VehicleData> findById(@PathVariable Long id) {
		Vehicle vehicle = this.vehicleService.findById(id);
		var data = VehicleData.fromVehicle(vehicle);
		return ResponseEntity.ok(data);
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(required = false) Optional<String> plateNumber,
			@PageableDefault(size = 10) Pageable pageable) {

		if (plateNumber.isPresent()) {
			// Busca por número da placa
			Vehicle vehicle = this.vehicleService.findByPlateNumber(plateNumber.get());
			var data = VehicleData.fromVehicle(vehicle);
			return ResponseEntity.ok(data);
		}

		// Busca paginada de todos os veículos
		Page<Vehicle> page = this.vehicleService.findAll(pageable);
		return ResponseEntity.ok(page);
	}

}
