package com.platzi.platzi.market.domain.repository;

import com.platzi.platzi.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> findAll();
    Optional<List<Purchase>> findByCustomerId(String customerId);
    Purchase save(Purchase purchase);
    // Purchase findById(Long id);
    // void delete(Long id);
}
