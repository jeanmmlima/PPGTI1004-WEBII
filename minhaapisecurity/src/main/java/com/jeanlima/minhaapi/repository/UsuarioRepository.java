package com.jeanlima.minhaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeanlima.minhaapi.model.User;



@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
