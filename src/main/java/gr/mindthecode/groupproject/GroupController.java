package gr.mindthecode.groupproject;

import gr.mindthecode.groupproject.entity.Product;
import gr.mindthecode.groupproject.repository.OrderRepository;
import gr.mindthecode.groupproject.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {
    private final ProductRepository repo;
    private final OrderRepository repo;

    public GroupController(ProductRepository repo) {
        this.repo = repo;
    }
    @GetMapping("/products/{id}")
    public Product one(@PathVariable Integer id) {
        return repo.findById(id)
                .orElseThrow();
    }
    @PostMapping("/products")
    public Product newProduct(@RequestBody Product product) {
        return repo.save(product);
    }
}
