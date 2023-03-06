package gr.mindthecode.groupproject.repository;

import gr.mindthecode.groupproject.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
}
