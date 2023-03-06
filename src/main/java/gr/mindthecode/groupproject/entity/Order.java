package gr.mindthecode.groupproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id
    private Integer id;

    private Integer quantity;
    private String productCode;
    private Double finalDiscount;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;


}
