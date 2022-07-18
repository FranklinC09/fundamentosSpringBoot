package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);
}
