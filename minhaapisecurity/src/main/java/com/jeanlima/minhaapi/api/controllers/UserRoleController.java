package com.jeanlima.minhaapi.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanlima.minhaapi.model.UserRole;
import com.jeanlima.minhaapi.repository.UserRoleRepository;


@RestController
@RequestMapping("/api/roles")
public class UserRoleController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostMapping
    public UserRole createRole(@RequestBody UserRole role) {
        System.out.println(role.getName());
        return userRoleRepository.save(role);
    }

    @GetMapping
    public List<UserRole> getAllRoles() {
        return userRoleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRole> getRoleById(@PathVariable Integer id) {
        Optional<UserRole> role = userRoleRepository.findById(id);
        return role.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRole> updateRole(@PathVariable Integer id, @RequestBody UserRole updatedRole) {
        return userRoleRepository.findById(id).map(role -> {
            role.setName(updatedRole.getName());
            userRoleRepository.save(role);
            return ResponseEntity.ok(role);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        if (userRoleRepository.existsById(id)) {
            userRoleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}
