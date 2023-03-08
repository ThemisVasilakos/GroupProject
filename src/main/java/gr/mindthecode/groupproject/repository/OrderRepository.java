package gr.mindthecode.groupproject.repository;

import gr.mindthecode.groupproject.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Integer> {

    Page<Orders> findByOrderContainingIgnoreCase(Optional<Integer> orderId,
                                                 PageRequest pageable);
}
