package dev.felipebill.pudo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

	@Embedded
	private Plate plate;
	
	@Column(name = "DS_DRIVERS_NAME")
	private String driversName;
	
	@Column(name = "NR_CAPACITY")
	private Integer capacity;
	
	@OneToMany(fetch = FetchType.LAZY ,mappedBy = "vehicle" )
	private List<Delivery> deliveries = new ArrayList<>();

	public void assignDelivery(Delivery delivery) {
		delivery.setVehicle(this);
		this.deliveries.add(delivery);
	}
	
}
