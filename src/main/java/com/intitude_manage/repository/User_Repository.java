package com.intitude_manage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intitude_manage.model.User;

@Repository
public interface User_Repository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);

    boolean existsByName(String name);
}
