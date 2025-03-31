package dev.felipebill.pudo.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dev.felipebill.pudo.model.Delivery;

@Repository
public interface IDeliveryRepository
		extends CrudRepository<Delivery, Long>, PagingAndSortingRepository<Delivery, Long>, JpaSpecificationExecutor<Delivery>{

}
