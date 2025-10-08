package com.platzi.platzi.market.persistence.mapper;

import com.platzi.platzi.market.domain.PurchaseProduct;
import com.platzi.platzi.market.persistence.entity.PurchaseProductData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface PurchaseProductMapper {

    @Mapping(source = "id.productId", target = "productId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "status", target = "status")
    PurchaseProduct toPurchaseProductModel(PurchaseProductData purchaseProductData);

    @InheritInverseConfiguration
    @Mapping(target = "id.purchaseId", ignore = true)
    @Mapping(target = "purchaseData", ignore = true)
    @Mapping(target = "productData", ignore = true)
    PurchaseProductData toPurchaseProductData(PurchaseProduct purchaseProduct);
}
