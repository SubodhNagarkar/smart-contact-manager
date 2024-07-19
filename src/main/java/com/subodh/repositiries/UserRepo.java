package com.subodh.repositiries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subodh.entities.User;

public interface UserRepo extends JpaRepository<User,String>{

    Optional<User> findByEmail(String email);
}
