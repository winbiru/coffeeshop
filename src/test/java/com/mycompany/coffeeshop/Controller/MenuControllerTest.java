package com.mycompany.coffeeshop.Controller;

import com.mycompany.coffeeshop.Model.Menu;
import com.mycompany.coffeeshop.Service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class MenuControllerTest {
    @Mock
    private MenuService menuService;

    @InjectMocks
    private MenuController menuController;

    @Test
    public void testGetAllMenus() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Menu> menus = new PageImpl<>(Arrays.asList(new Menu(), new Menu()), pageable, 2);
        Mockito.when(menuService.getAllMenu(pageable)).thenReturn(menus);

        ResponseEntity<Page<Menu>> response = menuController.getAllMenus(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(menus, response.getBody());
    }

    @Test
    public void testGetMenuById() {
        Long id = 1L;
        Menu menu = new Menu();
        Mockito.when(menuService.getMenuById(id)).thenReturn(menu);

        ResponseEntity<Menu> response = menuController.getMenuById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(menu, response.getBody());
    }

    @Test
    public void testCreateMenu() {
        Menu menu = new Menu();
        Mockito.when(menuService.createMenu(menu)).thenReturn(menu);

        ResponseEntity<Menu> response = menuController.createMenu(menu);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(menu, response.getBody());
    }

    @Test
    public void testUpdateMenu() {
        Long id = 1L;
        Menu menu = new Menu();
        Mockito.when(menuService.updateMenu(id, menu)).thenReturn(menu);

        ResponseEntity<Menu> response = menuController.updateMenu(id, menu);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(menu, response.getBody());
    }

    @Test
    public void testDeleteMenu() {
        Long id = 1L;

        ResponseEntity<Void> response = menuController.deleteMenu(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        Mockito.verify(menuService, Mockito.times(1)).deleteMenu(id);
    }

}
