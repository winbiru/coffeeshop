package com.mycompany.coffeeshop.Controller;

import com.mycompany.coffeeshop.Exception.ResourceNotFoundException;
import com.mycompany.coffeeshop.Model.Region;
import com.mycompany.coffeeshop.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

    private final RegionService regionService;
    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }


    @GetMapping
    public ResponseEntity<Object> getRegions() {
        return new ResponseEntity<>(regionService.getAllRegions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRegionById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(regionService.getRegionById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createRegion(@RequestBody Region region) {
        return new ResponseEntity<>(regionService.createRegion(region), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRegion(@PathVariable Long id, @RequestBody Region region) {
        try {
            return new ResponseEntity<>(regionService.updateRegion(id, region), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegion(@PathVariable Long id) {
        try {
            regionService.deleteRegion(id);
            return new ResponseEntity<>("Region with id " + id + " deleted successfully.", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
