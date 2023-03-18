package com.mycompany.coffeeshop.Service;

import com.mycompany.coffeeshop.Exception.ResourceNotFoundException;
import com.mycompany.coffeeshop.Model.Menu;
import com.mycompany.coffeeshop.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MenuServiceImpl implements MenuService{

    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }
    @Override
    public Page<Menu> getAllMenu(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    @Override
    public Menu getMenuById(Long Id) {
        return menuRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Menu not found with id: ","",Id));
    }

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu updateMenu(Long id, Menu menu) {
        Menu existingMenu = menuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu not found with id: ","",id));
        existingMenu.setName(menu.getName());
        existingMenu.setPrice(menu.getPrice());
        return menuRepository.save(existingMenu);
    }

    @Override
    public void deleteMenu(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Menu not found with id: ","",id);
        }
    }
}
