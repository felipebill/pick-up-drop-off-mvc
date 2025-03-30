package dev.felipebill.pudo.controller.data;

import java.time.LocalDateTime;

import dev.felipebill.pudo.model.Address;
import dev.felipebill.pudo.model.Delivery;

public record CompleteDeliveryData(Long id, Address pickupPoint, Address dropoffPoint, LocalDateTime orderTime) {

	public static CompleteDeliveryData fromDelivery(final Delivery delivery) {
		return new CompleteDeliveryData(delivery.getId(), delivery.getPickupPoint(), delivery.getDropoffPoint(),
				delivery.getOrderTime());
	}
}
