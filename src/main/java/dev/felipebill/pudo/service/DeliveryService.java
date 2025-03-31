package dev.felipebill.pudo.service;

import org.springframework.stereotype.Service;

import dev.felipebill.pudo.model.Delivery;
import dev.felipebill.pudo.model.Vehicle;
import dev.felipebill.pudo.repository.IDeliveryRepository;
import jakarta.persistence.EntityNotFoundException;

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
		return this.deliveryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Delivery not found for id: " + id));
	}
	
	public void sendDeliveryOrderToVehicle(Delivery delivery, Vehicle vehicle) {
		if(delivery.getVehicle() != null) {
			throw new RuntimeException("Delivery already has a vehicle");
		}
		delivery.setVehicle(vehicle);
		this.deliveryRepository.save(delivery);
	}
	
}
