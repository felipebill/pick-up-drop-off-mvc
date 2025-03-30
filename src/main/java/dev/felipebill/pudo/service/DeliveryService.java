package dev.felipebill.pudo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.felipebill.pudo.model.Delivery;
import dev.felipebill.pudo.repository.IDeliveryRepository;

@Service
public class DeliveryService {

	private final IDeliveryRepository deliveryRepository;
	
	public DeliveryService(final IDeliveryRepository deliveryRepository) {
		this.deliveryRepository = deliveryRepository;
	}
	
	public void registerNewDelivery(Delivery delivery) {
		this.deliveryRepository.save(delivery);
	}
	
	public Delivery findById(final Long id) {
		return this.deliveryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Delivery not found for id: " + id));
	}
	
}
