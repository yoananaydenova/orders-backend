package com.yoananaydenova.ordersapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column
    private LocalDateTime createdOn;
    @Column
    private LocalDateTime updatedOn;

    @Column
    private BigDecimal totalAmount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items;

    public Order() {
        this.createdOn = LocalDateTime.now();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
        this.totalAmount = calculateTotalAmount(items);
    }

    public void calculateTotalAmount(){
        this.totalAmount = calculateTotalAmount(this.items);
    }
    private static BigDecimal calculateTotalAmount(Set<OrderItem> items) {
        if(items.isEmpty()){
            return BigDecimal.ZERO;
        }
        return items.stream()
                .map(Order::calcItemAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal calcItemAmount(OrderItem item) {
        return BigDecimal.valueOf(item.getQuantity() * item.getPrice());
    }
}
