package gr.mindthecode.groupproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Order {
    @Id
    private Integer id;

    private Integer quantity;
    private String productCode;
    private Double finalDiscount;

    @OneToMany(mappedBy="order")
    private List<Product> products;


}
