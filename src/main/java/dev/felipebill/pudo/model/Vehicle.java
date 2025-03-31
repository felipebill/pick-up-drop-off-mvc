package dev.felipebill.pudo.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Vehicle {

	private Long id;
	private String plateNumber;
	private String driversName;
	private Integer capacity;
	private Collection<Delivery> deliveries;
	
}
