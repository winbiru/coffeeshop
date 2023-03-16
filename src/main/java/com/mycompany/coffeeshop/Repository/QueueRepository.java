package com.mycompany.coffeeshop.Repository;

import com.mycompany.coffeeshop.Model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface QueueRepository extends JpaRepository<Queue, Long> {
}
