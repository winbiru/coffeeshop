package com.mycompany.coffeeshop.Repository;

import com.mycompany.coffeeshop.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StoreRepository extends JpaRepository<Store, Long> {
}
