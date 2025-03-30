package dev.felipebill.pudo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.felipebill.pudo.model.Delivery;

@Repository
public interface IDeliveryRepository extends CrudRepository<Delivery, Long>{

}
