package com.platzi.platzi.market.persistence.mapper;

import com.platzi.platzi.market.domain.Category;
import com.platzi.platzi.market.persistence.entity.CategoryData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    Category toCategoryModel(CategoryData categoryData);

    @InheritInverseConfiguration
    @Mapping(target = "productData", ignore = true)
    CategoryData toCategoryData(Category category);
}
