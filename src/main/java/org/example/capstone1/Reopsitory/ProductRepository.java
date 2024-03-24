package org.example.capstone1.Reopsitory;

import org.example.capstone1.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Integer> findRatingsByProductId(Integer productId);
}
