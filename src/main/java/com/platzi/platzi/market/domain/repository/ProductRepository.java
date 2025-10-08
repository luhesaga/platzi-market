package com.platzi.platzi.market.domain.repository;

import com.platzi.platzi.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();
    Optional<List<Product>> findByCategoryId(Long categoryId);
    Optional<List<Product>> getStockOut(Integer stock, Boolean status);
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Long id);
}
