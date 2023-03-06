package gr.mindthecode.groupproject.controller;

import gr.mindthecode.groupproject.entity.Product;
import gr.mindthecode.groupproject.repository.OrderRepository;
import gr.mindthecode.groupproject.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public GroupController(ProductRepository repo, OrderRepository orderRepository) {
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

}
