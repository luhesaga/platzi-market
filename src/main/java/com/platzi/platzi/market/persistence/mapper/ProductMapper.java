package com.platzi.platzi.market.persistence.mapper;

import com.platzi.platzi.market.domain.Product;
import com.platzi.platzi.market.persistence.entity.ProductData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "categoryData", target = "category")
    Product toProductModel(ProductData productData);
    List<Product> toProductModelList(List<ProductData> productList);

    @InheritInverseConfiguration
    @Mapping(target = "barcode", ignore = true)
    @Mapping(target = "categoryData", ignore = true)
    ProductData toProductData(Product product);
}
