package com.platzi.platzi.market.web.controller;

import com.platzi.platzi.market.domain.Product;
import com.platzi.platzi.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Find all products",
    description = "Find all products")
    @ApiResponse(responseCode = "200", description = "Found products")
    public ResponseEntity<List<Product>> findAll(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> findByCategoryId(@PathVariable("id") Long categoryId){

        List<Product> products = productService.findByCategoryId(categoryId).orElse(null);

        return ((products != null) && !products.isEmpty()) ?
                new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/stock/{stock}/{status}")
    public List<Product> getStockOut(@PathVariable("stock") Integer stock, @PathVariable("status") Boolean status){
        return productService.getStockOut(stock, status).orElse(null);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a product by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Product> findById(
            @Parameter(description = "The ID of the product", required = true, example = "7")
            @PathVariable("id") Long id){
        return productService.findById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(Boolean.TRUE.equals(productService.delete(id))){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
