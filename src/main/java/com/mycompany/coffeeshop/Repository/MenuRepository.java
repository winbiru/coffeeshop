package com.mycompany.coffeeshop.Repository;

import com.mycompany.coffeeshop.Model.Menu;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MenuRepository extends PagingAndSortingRepository<Menu, Long> {
}
