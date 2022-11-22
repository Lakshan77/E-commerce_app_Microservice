package com.lakicode.productservice.controller;

import com.lakicode.productservice.model.Product;
import com.lakicode.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity addExpense(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateExpense(@RequestBody Product product){
        productService.updateProduct(product);
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllExpense(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getExpenseByName(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpense(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
