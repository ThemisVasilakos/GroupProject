package gr.mindthecode.groupproject.repository;

import gr.mindthecode.groupproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
