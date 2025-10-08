package com.platzi.platzi.market.persistence.crud;

import com.platzi.platzi.market.persistence.entity.ProductData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCRUDRepository extends CrudRepository<ProductData, Long> {

    List<ProductData> findByCategoryIdOrderByNameAsc(Long categoryId);

    Optional<List<ProductData>> findByStockLessThanAndStatus(Integer stock, Boolean status);

}
