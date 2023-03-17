package com.mycompany.coffeeshop.Model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long region_id;

    @Column(name = "region_name")
    private String RegionName;
    @Column(name = "region_address")
    private String RegionAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "region")
    private List<Store> stores;

    public Region() {

    }

    public Region(Long region_id, String RegionName, List<Store> stores) {
        this.region_id = region_id;
        this.RegionName = RegionName;
        this.stores = stores;

    }

    public Long getRegion_Id() {
        return region_id;
    }

    public void setRegion_Id(Long region_id) {
        this.region_id = region_id;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String RegionName) {
        this.RegionName = RegionName;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public String getRegionAddress() {
        return RegionAddress;
    }

    public void setRegionAddress(String RegionAddress) {
        this.RegionAddress = RegionAddress;
    }

}
