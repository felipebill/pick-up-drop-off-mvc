package dev.felipebill.pudo.service;

import org.springframework.stereotype.Service;

import dev.felipebill.pudo.model.Address;
import dev.felipebill.pudo.repository.AddressRepository;

@Service
public class AddressService {

	AddressRepository addressRepository;

	AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public Address findById(Long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Address not found for id: " + id));
	}

}
