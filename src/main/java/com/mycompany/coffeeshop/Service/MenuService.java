package com.mycompany.coffeeshop.Service;

import com.mycompany.coffeeshop.Model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {
    Page<Menu> getAllMenu(Pageable pageable);

    Menu getMenuById(Long Id);

    Menu createMenu(Menu menu);

    Menu updateMenu(Long id, Menu menu);

    void deleteMenu(Long id);
}
