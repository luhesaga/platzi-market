package com.platzi.platzi.market.persistence.mapper;

import com.platzi.platzi.market.domain.Purchase;
import com.platzi.platzi.market.persistence.entity.PurchaseData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseProductMapper.class})
public interface PurchaseMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "products", target = "products")
    Purchase toPurchaseModel(PurchaseData purchaseData);
    List<Purchase> toPurchaseModelList(List<PurchaseData> purchaseList);

    @InheritInverseConfiguration
    @Mapping(target = "customerData", ignore = true)
    PurchaseData toPurchaseData(Purchase purchase);
}
