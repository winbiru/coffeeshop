package com.mycompany.coffeeshop.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long store_id;

    @Column(name = "store_name")
    private String store_name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "store")
    private List<Menu> menus = new ArrayList<>();;

    @OneToMany(mappedBy = "shop")
    private List<Queue> queues;

    @OneToMany(mappedBy = "shop")
    private List<Order> orders;

    @OneToMany(mappedBy = "shop")
    private List<Customer> customers;

    public Store(Long store_id, String name, Region region, List menus) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.region = region;
        this.menus = menus;
    }

    public Store() {
    }

    public Long getStore_Id() {
        return store_id;
    }

    public void setStore_Id(Long id) {
        this.store_id = store_id;
    }

    public String getStore_Name() {
        return store_name;
    }

    public void setStore_Name(String store_name) {
        this.store_name = store_name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
