package dev.felipebill.pudo.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dev.felipebill.pudo.repository.AddressRepository;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	private final AddressRepository addressRepository;
	
	public AddressController(final AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	@PostMapping
	ResponseEntity<NewlyRegisteredAddressData> registerNewAddress(
			@RequestBody RegisterNewAddressForm form,
			UriComponentsBuilder uriComponentsBuilder
			) {
		var address = form.toAddress();
		this.addressRepository.save(address);
		var data = NewlyRegisteredAddressData.fromAddress(address);
		
		URI uri = uriComponentsBuilder.path("/addresses/{id}").buildAndExpand(data.id()).toUri();
		
		return ResponseEntity.created(uri).body(data);
	}
	
}
