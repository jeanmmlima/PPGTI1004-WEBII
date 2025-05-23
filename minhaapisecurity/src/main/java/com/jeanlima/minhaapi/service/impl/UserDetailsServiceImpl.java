package com.jeanlima.minhaapi.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jeanlima.minhaapi.model.CustomUserDetails;
import com.jeanlima.minhaapi.model.User;
import com.jeanlima.minhaapi.model.UserRole;
import com.jeanlima.minhaapi.repository.UserRoleRepository;
import com.jeanlima.minhaapi.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        logger.debug("Entrando no loadUserByUsername...");
        User user = usuarioRepository.findByUsername(username).get();
        if(user == null){
            logger.error("Usuário não encontrado: " + username);
            throw new UsernameNotFoundException("Usuário não foi encontrado!!");
        }
        logger.info("Usuário encontrado com sucesso!");
        return new CustomUserDetails(user);
    }

    @Transactional
    public User save(User user) {

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            final Set<UserRole> managedRoles = user.getRoles().stream()
                .map(role -> {
                    if (role.getId() != null) {
                        return userRoleRepository.findById(role.getId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                "Role não foi encontrada: " + role.getId()));
                    }
                    throw new IllegalArgumentException("Role deve ter ID válido");
                })
                .collect(Collectors.toSet());
            
            user.setRoles(managedRoles);
        }

        
        return usuarioRepository.save(user);
    }
    
}
