package gr.mindthecode.groupproject.repository;

import gr.mindthecode.groupproject.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Integer> {
}
