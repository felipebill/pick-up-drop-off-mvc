package dev.felipebill.pudo.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import dev.felipebill.pudo.service.AddressService;

@Component
public class DeliveryFactory {

	private AddressService addressService;

	public DeliveryFactory(final AddressService addressService) {
		this.addressService = addressService;
	}

	public Delivery createNewDelivery(Long pickupId, Long dropoffId) {
		var pickupPoint = this.addressService.findById(pickupId);
		var dropoff = this.addressService.findById(dropoffId);
		var delivery = Delivery.builder().pickupPoint(pickupPoint).dropoffPoint(dropoff).orderTime(LocalDateTime.now())
				.state(DeliveryState.ORDERED)
				.build();
		return delivery;
	}

}
