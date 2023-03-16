package com.mycompany.coffeeshop.Model;

import javax.persistence.*;
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menu_id;

    @Column(name = "menu_name", nullable = false)
    private String menu_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Menu(Long menu_id, String name, Store store) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.store = store;
    }

    public Menu() {

    }

    public Long getId() {
        return menu_id;
    }

    public void setId(Long menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_Name() {
        return menu_name;
    }

    public void setMenu_Name(String menu_name) {
        this.menu_name = menu_name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
