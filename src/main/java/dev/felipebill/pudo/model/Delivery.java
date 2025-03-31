package dev.felipebill.pudo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_DELIVERY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DELIVERY")
	private Long id;

	@OneToOne
	@JoinColumn(name = "CD_PICK_UP")
	private Address pickupPoint;

	@OneToOne
	@JoinColumn(name = "CD_DROP_OFF")
	private Address dropoffPoint;

	@Column(name = "TM_ORDER_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime orderTime;

	@Column(name = "TM_DELIVERY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime deliverTime;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_VEHICLE")
	private Vehicle vehicle;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TP_STATE")
	private DeliveryState state;

}
