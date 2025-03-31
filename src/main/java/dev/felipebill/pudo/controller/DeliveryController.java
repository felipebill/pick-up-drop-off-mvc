package dev.felipebill.pudo.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dev.felipebill.pudo.controller.data.CompleteDeliveryData;
import dev.felipebill.pudo.controller.form.NewDeliveryForm;
import dev.felipebill.pudo.model.Delivery;
import dev.felipebill.pudo.model.DeliveryFactory;
import dev.felipebill.pudo.service.DeliveryDispatcherService;
import dev.felipebill.pudo.service.DeliveryService;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

	DeliveryFactory deliveryFactory;

	DeliveryService deliveryService;
	
	DeliveryDispatcherService deliveryDispatcherService;

	public DeliveryController(DeliveryFactory deliveryFactory, DeliveryService deliveryService, DeliveryDispatcherService deliveryDispatcherService) {
		this.deliveryFactory = deliveryFactory;
		this.deliveryService = deliveryService;
		this.deliveryDispatcherService = deliveryDispatcherService;
	}

	@PostMapping
	ResponseEntity<CompleteDeliveryData> registerNewDelivery(@RequestBody final NewDeliveryForm form, UriComponentsBuilder uriComponentsBuilder) {
		var delivery = deliveryFactory.createNewDelivery(form.pickupPointAddressId(), form.dropoffPointAddressId());
		this.deliveryService.registerNewDelivery(delivery);
		
		this.deliveryDispatcherService.dispatchDelivery(delivery);
		
		
		var data = CompleteDeliveryData.fromDelivery(delivery);

		URI uri = uriComponentsBuilder.path("/deliveries/{id}").buildAndExpand(data.id()).toUri();

		return ResponseEntity.created(uri).body(data);
	}

	
	@GetMapping("/{id}")
	ResponseEntity<CompleteDeliveryData> findById(@PathVariable Long id){
		Delivery delivery = this.deliveryService.findById(id);
		var data = CompleteDeliveryData.fromDelivery(delivery);
		return ResponseEntity.ok(data);
	}
	

}
