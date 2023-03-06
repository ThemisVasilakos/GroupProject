package gr.mindthecode.groupproject.repository;

import gr.mindthecode.groupproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
