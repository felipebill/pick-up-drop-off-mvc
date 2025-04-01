package dev.felipebill.pudo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.felipebill.pudo.model.Country;
import dev.felipebill.pudo.model.State;

@Controller
public class AddressController {
	
	
	

    @GetMapping("address-form")
    public String getAddressForm(Model model) {
        List<State> states = Arrays.asList(State.values());
        List<Country> countries = Arrays.asList(Country.values());
        model.addAttribute("states", states); 
        model.addAttribute("countries", countries);
        return "address-form"; 
    
    }
	
}
