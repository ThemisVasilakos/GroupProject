package gr.mindthecode.groupproject.controller;

import gr.mindthecode.groupproject.entity.Product;
import gr.mindthecode.groupproject.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        Product match = productRepository.findById(id)
                .orElseThrow();
        productRepository.delete(match);
    }


    @PutMapping("/products/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> searchProduct = productRepository.findById(id);

        if(searchProduct.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400),"Not found");
        }
        searchProduct.get().setDescription(product.getDescription());
        searchProduct.get().setPrice(product.getPrice());

        return productRepository.save(searchProduct.get());
    }

    @GetMapping("/products")
    public Page<Product> allProducts(
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort) {
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


}