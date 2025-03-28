package dev.felipebill.pudo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@PostMapping
	void registerNewAddress(@RequestBody RegisterNewAddressForm form) {
		var address = form.toAddress();
		
	}
	
}
