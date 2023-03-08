package gr.mindthecode.groupproject.controller;

import gr.mindthecode.groupproject.entity.Orders;
import gr.mindthecode.groupproject.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController
public class OrdersController {
    private final OrderRepository orderRepository;

    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository=orderRepository;
    }

    @PostMapping("/order")
    public Orders newOrder(@RequestBody Orders order) {
        return orderRepository.save(order);
    }

    @GetMapping("/order/{id}")
    public Orders getOrder(@PathVariable Integer id) {
        return orderRepository.findById(id)
                .orElseThrow();
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        Orders match = orderRepository.findById(id)
                .orElseThrow();
        orderRepository.delete(match);
    }

    @PutMapping("/order/{id}")
    public Orders update(@PathVariable Integer id, @RequestBody Orders order) {
        Optional<Orders> searchOrder = orderRepository.findById(id);

        if(searchOrder.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400),"Not found");
        }
        searchOrder.get().setQuantity(order.getQuantity());
        searchOrder.get().setProductCode(order.getProductCode());
        searchOrder.get().setFinalDiscount(order.getFinalDiscount());
        searchOrder.get().setProducts(order.getProducts());

        return orderRepository.save(searchOrder.get());
    }

    @GetMapping("/order")
    public Page<Orders> allOrders(
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) String productCode,
            @RequestParam(required = false) Double finalDiscount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("orderId").ascending() :
                        Sort.by("orderId").descending());

        Page<Orders> res;
        if (quantity == null && productCode == null && finalDiscount == null) {
            res = orderRepository.findAll(paging);
        } else {
            res = orderRepository.findByProductCodeContainingIgnoreCaseOrQuantityOrFinalDiscount(productCode, quantity,
                    finalDiscount ,paging);
        }

        return res;
    }
}
