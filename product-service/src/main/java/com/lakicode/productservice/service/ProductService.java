package com.lakicode.productservice.service;

import com.lakicode.productservice.model.Product;
import com.lakicode.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product){
        productRepository.insert(product);
    }

    public void updateProduct(Product product){
        Product savedProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Product by ID %s", product.getId())));

        savedProduct.setName(product.getName());
        savedProduct.setProductCategory(product.getProductCategory());
        savedProduct.setPrice(product.getPrice());

        productRepository.save(product);
    }

    public List<Product> getAllProduct(){

        return productRepository.findAll();
    }

    public Product getProductByName(String name){

        return productRepository.findByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Expense by name %s", name)));
    }

    public void deleteProduct(String id){
        productRepository.deleteById(id);
    }
}
