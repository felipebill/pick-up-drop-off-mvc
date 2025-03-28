package dev.felipebill.pudo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ADDRESS")
	private Long id;
	
	@Column(name = "DS_STREET")
	private String street;
	@Column(name = "NR_NUMBER")
	private Integer number;
	
	
	@Column(name = "DS_NEIGHBORHOOD")
	private String neighborhood;

	@Column(name = "DS_NEIGHBORHOOD")
	private PostalCode postalCode;
	
	@Column(name = "TP_STATE")
	@Enumerated(EnumType.STRING)
	private State state;

	@Column(name = "TP_COUNTRY")
	@Enumerated(EnumType.STRING)
	private Country country;
	
}
