package dev.felipebill.pudo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.felipebill.pudo.controller.form.NewDeliveryForm;
import dev.felipebill.pudo.model.DeliveryFactory;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

	DeliveryFactory deliveryFactory;
	
	public DeliveryController(DeliveryFactory deliveryFactory) {
		this.deliveryFactory = deliveryFactory;
	}
	
	@PostMapping
	void registerNewDelivery(@RequestBody final NewDeliveryForm form) {
		System.out.println(form);
		var delivery = deliveryFactory.createNewDelivery(form.pickupPointAddressId(), form.dropoffPointAddressId());
		System.out.println(delivery.getPickupPoint().getStreet());
		System.out.println("ok");
	}
	
}
