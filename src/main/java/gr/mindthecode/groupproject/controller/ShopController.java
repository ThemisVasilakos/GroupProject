package gr.mindthecode.groupproject.controller;

import gr.mindthecode.groupproject.entity.Orders;
import gr.mindthecode.groupproject.entity.Product;
import gr.mindthecode.groupproject.repository.OrderRepository;
import gr.mindthecode.groupproject.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class ShopController {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ShopController(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
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

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        Product match = productRepository.findById(id)
                .orElseThrow();
        productRepository.delete(match);
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        Orders match = orderRepository.findById(id)
                .orElseThrow();
        orderRepository.delete(match);
    }
    @PutMapping("/products/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        if (!id.equals(product.getProductId())){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "id in path does not patch id in body");
        }
        return productRepository.save(product);
    }

    @PutMapping("/order/{id}")
    public Orders update(@PathVariable Integer id, @RequestBody Orders order) {
        if (!id.equals(order.getOrderId()) ){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "id in path does not patch id in body");
        }
        return orderRepository.save(order);
    }

    @GetMapping("/products")
    public Page<Product> allProducts(
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ) {
    PageRequest paging = PageRequest
            .of(page, size)
            .withSort(sort.equalsIgnoreCase("ASC") ?
                    Sort.by("description").ascending() :
                    Sort.by("description").descending());

    Page<Product> res;
        if (description==null && price == null) {
            res = productRepository.findAll(paging);
        } else {
        res = productRepository.findByDescriptionContainingIgnoreCaseOrPrice(description,price,paging);
    }

        return res;
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