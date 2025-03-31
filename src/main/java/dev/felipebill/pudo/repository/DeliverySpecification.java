package dev.felipebill.pudo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import dev.felipebill.pudo.model.Delivery;
import dev.felipebill.pudo.model.DeliveryState;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class DeliverySpecification implements Specification<Delivery> {
    private final Optional<Long> vehicleId;
    private final Optional<DeliveryState> state;

    public DeliverySpecification(Optional<Long> vehicleId, Optional<DeliveryState> state) {
        this.vehicleId = vehicleId;
        this.state = state;
    }

    @Override
    public Predicate toPredicate(Root<Delivery> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        vehicleId.ifPresent(id -> 
            predicates.add(cb.equal(root.get("vehicle").get("id"), id))
        );

        state.ifPresent(s -> 
            predicates.add(cb.equal(root.get("state"), s))
        );

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
