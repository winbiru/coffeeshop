package com.mycompany.coffeeshop.Repository;

import com.mycompany.coffeeshop.Model.ERole;
import com.mycompany.coffeeshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<Object> findRoleByName(ERole roleUser);
}
