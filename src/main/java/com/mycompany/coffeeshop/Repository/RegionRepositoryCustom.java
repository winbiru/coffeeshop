package com.mycompany.coffeeshop.Repository;

import com.mycompany.coffeeshop.Model.Region;

import java.util.List;

public interface RegionRepositoryCustom {
    List<Region> findRegionsByCountry(String country);
}
