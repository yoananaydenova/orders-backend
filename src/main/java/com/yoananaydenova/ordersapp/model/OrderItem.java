package com.yoananaydenova.ordersapp.model;

import jakarta.persistence.*;

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private  double price;

    @Column
    private int quantity;

    @OneToOne
    private  Item product;

    @ManyToOne
    @JoinColumn(name = "id")
    private  Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getProduct() {
        return product;
    }

    public void setProduct(Item product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
