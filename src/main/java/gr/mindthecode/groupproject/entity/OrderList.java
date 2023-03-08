package gr.mindthecode.groupproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderListId;

    private Integer Quantity;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product products;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orders;

    public Integer getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(Integer orderListId) {
        this.orderListId = orderListId;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
