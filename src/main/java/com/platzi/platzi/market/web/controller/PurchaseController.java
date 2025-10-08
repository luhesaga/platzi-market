package com.platzi.platzi.market.web.controller;

import com.platzi.platzi.market.domain.Purchase;
import com.platzi.platzi.market.domain.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> findAll(){
        return new ResponseEntity<>(purchaseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Purchase>> findByCustomerId(@PathVariable("id") String customerId){

        List<Purchase> purchases = purchaseService.findByCustomerId(customerId).orElse(null);

        return ((purchases != null) && !purchases.isEmpty()) ?
                new ResponseEntity<>(purchases, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
