package dev.felipebill.pudo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.felipebill.pudo.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{

}
