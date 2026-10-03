package com.dhairyasingh.ecommerce.repository;

import com.dhairyasingh.ecommerce.model.Category;
import com.dhairyasingh.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE p.active = true")
    List<Product> findActiveProducts();

    @Query(value = "SELECT * FROM products p ORDER BY p.created_at DESC LIMIT :limit", nativeQuery = true)
    List<Product> findLatestProducts(@Param("limit") int limit);

    // Search products by name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Search active products by name
    List<Product> findByNameContainingIgnoreCaseAndActiveTrue(String name);

    // Filter products by price range
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Search + pagination
    Page<Product> findByNameContainingIgnoreCase(
        String name,
        Pageable pageable
    );
}
