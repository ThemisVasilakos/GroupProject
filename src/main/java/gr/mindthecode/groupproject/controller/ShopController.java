package gr.mindthecode.groupproject.controller;

import gr.mindthecode.groupproject.entity.Orders;
import gr.mindthecode.groupproject.entity.Product;
import gr.mindthecode.groupproject.repository.OrderRepository;
import gr.mindthecode.groupproject.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopController {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ShopController(ProductRepository repo, OrderRepository orderRepository) {
        this.productRepository = repo;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/products")
    public Product newProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productRepository.findById(id)
                .orElseThrow();
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

}
