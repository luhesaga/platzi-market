package com.platzi.platzi.market.persistence;

import com.platzi.platzi.market.domain.Purchase;
import com.platzi.platzi.market.domain.repository.PurchaseRepository;
import com.platzi.platzi.market.persistence.crud.PurchaseCRUDRepository;
import com.platzi.platzi.market.persistence.entity.PurchaseData;
import com.platzi.platzi.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseDataRepository implements PurchaseRepository {

    private final PurchaseCRUDRepository purchaseCRUDRepository;
    private final PurchaseMapper purchaseMapper;

    public PurchaseDataRepository(PurchaseCRUDRepository purchaseCRUDRepository, PurchaseMapper purchaseMapper) {
        this.purchaseCRUDRepository = purchaseCRUDRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public List<Purchase> findAll() {
        return purchaseMapper.toPurchaseModelList((List<PurchaseData>) purchaseCRUDRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> findByCustomerId(String customerId) {
        return purchaseCRUDRepository.findByCustomerId(customerId)
                .map(purchaseMapper::toPurchaseModelList);
    }

    @Override
    public Purchase save(Purchase purchase) {
        PurchaseData purchaseData = purchaseMapper.toPurchaseData(purchase);
        purchaseData.getProducts().forEach(productData -> productData.setPurchaseData(purchaseData));

        return purchaseMapper.toPurchaseModel(purchaseCRUDRepository.save(purchaseData));
    }
}
