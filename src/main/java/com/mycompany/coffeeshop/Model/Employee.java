package com.mycompany.coffeeshop.Model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    @Column(name = "employee_name", nullable = false)
    private String employee_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // constructors, getters and setters

    public Employee(Long employee_id, String name, Store store) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.store = store;
    }

    public Employee() {
    }

    public Long getEmployeeById() {
        return employee_id;
    }

    public void setId(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_Name() {
        return employee_name;
    }

    public void setEmployee_Name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

}
