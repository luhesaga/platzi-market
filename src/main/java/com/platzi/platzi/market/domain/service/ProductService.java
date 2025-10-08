package com.platzi.platzi.market.domain.service;

import com.platzi.platzi.market.domain.Product;
import com.platzi.platzi.market.domain.repository.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<List<Product>> findByCategoryId(Long categoryId){
        return productRepository.findByCategoryId(categoryId);
    }

    public Optional<List<Product>> getStockOut(Integer stock, Boolean status){
        return productRepository.getStockOut(stock, status);
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Boolean delete(Long id){

        try {
            productRepository.delete(id);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }



}
