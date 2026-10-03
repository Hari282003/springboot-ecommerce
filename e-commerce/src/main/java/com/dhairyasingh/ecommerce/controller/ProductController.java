package com.dhairyasingh.ecommerce.controller;

import com.dhairyasingh.ecommerce.model.Product;
import com.dhairyasingh.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> index() {
        return productService.activeProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> show(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/latest")
    public List<Product> latestProducts() {
        return productService.getLatestProducts(6);
    }

    // Search products
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchProducts(name);
    }

    // Filter products by price
    @GetMapping("/filter")
    public List<Product> filterByPrice(
        @RequestParam Double minPrice,
        @RequestParam Double maxPrice) {

        return productService.filterByPrice(minPrice, maxPrice);
    }

    // Search products with pagination
    @GetMapping("/search/page")
    public Page<Product> searchProductsPaginated(
        @RequestParam String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        return productService.searchProductsPaginated(
            name,
            PageRequest.of(page, size)
        );
    }
}
