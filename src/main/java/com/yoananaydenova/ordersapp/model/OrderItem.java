package com.yoananaydenova.ordersapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private double price;

    @Column
    private int quantity;

    @OneToOne
    private Item item;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    public OrderItem() {
    }

    public OrderItem(double price, int quantity, Item item, Order order) {
        this.price = price;
        this.quantity = quantity;
        this.item = item;
        this.order = order;
    }

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


}
