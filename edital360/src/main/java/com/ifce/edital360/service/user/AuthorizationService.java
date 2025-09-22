package com.ifce.edital360.service.user;

import com.ifce.edital360.model.user.User;
import com.ifce.edital360.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // tenta interpretar o username como UUID (token com subject = id)
        try {
            UUID id = UUID.fromString(username);
            return userRepository.findById(id)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        } catch (IllegalArgumentException ex) {
            // não era UUID -> tentar por CPF (login tradicional)
            return userRepository.findByCpf(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        }
    }
}
