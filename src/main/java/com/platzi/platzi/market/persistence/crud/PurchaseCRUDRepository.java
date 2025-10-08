package com.platzi.platzi.market.persistence.crud;

import com.platzi.platzi.market.persistence.entity.PurchaseData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseCRUDRepository extends CrudRepository<PurchaseData, Long> {

    Optional<List<PurchaseData>> findByCustomerId(String customerId);


}
