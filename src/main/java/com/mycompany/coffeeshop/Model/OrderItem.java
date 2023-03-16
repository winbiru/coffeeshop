package com.mycompany.coffeeshop.Model;


import javax.persistence.*;
@Entity
@Table(name = "orderitem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderitem_id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    // getters and setters


    public OrderItem(Long orderitem_id, int quantity, Order order, Menu menu) {
        this.orderitem_id = orderitem_id;
        this.quantity = quantity;
        this.order = order;
        this.menu = menu;
    }

    public OrderItem() {
    }

    public Long getOrderitem_id() {
        return orderitem_id;
    }

    public void setOrderitem_id(Long orderitem_id) {
        this.orderitem_id = orderitem_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

