package com.mycompany.coffeeshop.Service;

import com.mycompany.coffeeshop.Model.Region;
import java.util.List;

public interface RegionService {

    List<Region> getAllRegions();

    Region getRegionById(Long id);

    Region createRegion(Region region);

    Region updateRegion(Long id, Region regionDetails);

    void deleteRegion(Long id);
}
