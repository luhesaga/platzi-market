package com.platzi.platzi.market.domain.service;

import com.platzi.platzi.market.domain.Purchase;
import com.platzi.platzi.market.domain.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public Optional<List<Purchase>> findByCustomerId(String customerId){
        return purchaseRepository.findByCustomerId(customerId);
    }

    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }


}
