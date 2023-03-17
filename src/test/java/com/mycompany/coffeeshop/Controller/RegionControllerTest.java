package com.mycompany.coffeeshop.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.coffeeshop.Exception.ResourceNotFoundException;
import com.mycompany.coffeeshop.Model.Region;
import com.mycompany.coffeeshop.Service.RegionService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest(RegionController.class)
public class RegionControllerTest {
    @InjectMocks
    RegionController regionController;

    @Mock
    RegionService regionService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getRegions() {
        // Arrange
        List<Region> regions = new ArrayList<>();
        regions.add(new Region(1L, "Region 1", new ArrayList<>()));
        regions.add(new Region(2L, "Region 2", new ArrayList<>()));

        when(regionService.getAllRegions()).thenReturn(regions);

        // Act
        ResponseEntity<Object> response = regionController.getRegions();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(regions, response.getBody());
    }

    @Test
    public void getRegionById() throws ResourceNotFoundException {
        // Arrange
        Region region = new Region(1L, "Region 1", new ArrayList<>());

        when(regionService.getRegionById(1L)).thenReturn(region);

        // Act
        ResponseEntity<Object> response = regionController.getRegionById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(region, response.getBody());
    }

    @Test
    public void getRegionByIdWithInvalidId() throws ResourceNotFoundException {
        // Arrange
        when(regionService.getRegionById(1L)).thenThrow(ResourceNotFoundException.class);

        // Act
        ResponseEntity<Object> response = regionController.getRegionById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createRegion() {
        // Arrange
        Region region = new Region(1L, "Region 1", new ArrayList<>());

        when(regionService.createRegion(region)).thenReturn(region);

        // Act
        ResponseEntity<Object> response = regionController.createRegion(region);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(region, response.getBody());
    }

    @Test
    public void updateRegion() throws ResourceNotFoundException {
        // Arrange
        Region region = new Region(1L, "Region 1", new ArrayList<>());

        when(regionService.updateRegion(1L, region)).thenReturn(region);

        // Act
        ResponseEntity<Object> response = regionController.updateRegion(1L, region);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(region, response.getBody());
    }

    @Test
    public void updateRegionWithInvalidId() throws ResourceNotFoundException {
        // Arrange
        Region region = new Region(1L, "Region 1", new ArrayList<>());

        when(regionService.updateRegion(1L, region)).thenThrow(ResourceNotFoundException.class);

        // Act
        ResponseEntity<Object> response = regionController.updateRegion(1L, region);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
