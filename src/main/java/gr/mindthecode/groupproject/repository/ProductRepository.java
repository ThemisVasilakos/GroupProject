package gr.mindthecode.groupproject.repository;

import gr.mindthecode.groupproject.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByDescriptionContainingIgnoreCaseOrPrice(String description,Double price,
                                                        PageRequest pageable);
}
