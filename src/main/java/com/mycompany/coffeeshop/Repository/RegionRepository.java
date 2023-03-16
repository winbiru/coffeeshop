package com.mycompany.coffeeshop.Repository;

import com.mycompany.coffeeshop.Model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface RegionRepository extends JpaRepository<Region, Long> {

}
