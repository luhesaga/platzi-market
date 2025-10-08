package com.platzi.platzi.market.persistence;

import com.platzi.platzi.market.domain.Product;
import com.platzi.platzi.market.domain.repository.ProductRepository;
import com.platzi.platzi.market.persistence.crud.ProductCRUDRepository;
import com.platzi.platzi.market.persistence.entity.ProductData;
import com.platzi.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDataRepository implements ProductRepository {

    private final ProductCRUDRepository productCRUDRepository;
    private final ProductMapper productMapper;

    public ProductDataRepository(ProductCRUDRepository productCRUDRepository, ProductMapper productMapper) {
        this.productCRUDRepository = productCRUDRepository;
        this.productMapper = productMapper;
    }


    @Override
    public List<Product> findAll() {
        List<ProductData> productsData = (List<ProductData>) productCRUDRepository.findAll();
        return productMapper.toProductModelList(productsData);
    }

    @Override
    public Optional<List<Product>> findByCategoryId(Long categoryId) {
        List<ProductData> productsData = productCRUDRepository.findByCategoryIdOrderByNameAsc(categoryId);
        return Optional.of(productMapper.toProductModelList(productsData));
    }

    @Override
    public Optional<List<Product>> getStockOut(Integer stock, Boolean status) {
        Optional<List<ProductData>> stockOut = productCRUDRepository.findByStockLessThanAndStatus(stock, status);
        return stockOut.map(productMapper::toProductModelList);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productCRUDRepository.findById(id)
                .map(productMapper::toProductModel);
    }

    @Override
    public Product save(Product product) {
        ProductData productData = productMapper.toProductData(product);
        return productMapper.toProductModel(productCRUDRepository.save(productData));
    }

    @Override
    public void delete(Long id) {
        productCRUDRepository.deleteById(id);
    }
}
