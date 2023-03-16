package com.mycompany.coffeeshop.Model;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order(Long order_id, LocalDateTime orderTime, Store store, List<OrderItem> orderItems) {
        this.order_id = order_id;
        this.orderTime = orderTime;
        this.store = store;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public Long getOrder_Id() {
        return order_id;
    }

    public void setId(Long order_id) {
        this.order_id = order_id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }


}
