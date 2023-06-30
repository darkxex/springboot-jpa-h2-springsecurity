package com.bci.crud.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bci.crud.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

}
