package dev.felipebill.pudo.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Entity
@Table(name = "TB_VEHICLE")
public class Vehicle {

	@Id
	@Column(name = "ID_VEHICLE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DS_PLATE")
	private String plateNumber;
	
	@Column(name = "DS_DRIVERS_NAME")
	private String driversName;
	
	@Column(name = "NR_CAPACITY")
	private Integer capacity;
	
	@Transient
	private Collection<Delivery> deliveries;
	
}
