package com.mycompany.coffeeshop.Service;

import com.mycompany.coffeeshop.Model.Region;
import com.mycompany.coffeeshop.Repository.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.mycompany.coffeeshop.Exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService{
    private final RegionRepository regionRepository;
    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegionById(Long id) {
        Optional<Region> region = regionRepository.findById(id);
        if (region.isPresent()) {
            return region.get();
        } else {
            throw new ResourceNotFoundException("Region", "id", id);
        }
    }

    @Override
    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Region updateRegion(Long id, Region regionDetails) {
        Region region = getRegionById(id);
        region.setRegionName(regionDetails.getRegionName());
        region.setRegionAddress(regionDetails.getRegionAddress());
        return regionRepository.save(region);
    }

    @Override
    public void deleteRegion(Long id) {
        Region region = getRegionById(id);
        regionRepository.delete(region);
    }

}
