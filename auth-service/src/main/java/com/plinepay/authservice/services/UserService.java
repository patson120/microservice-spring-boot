package com.plinepay.authservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.plinepay.authservice.config.CustomUserDetails;
import com.plinepay.authservice.models.User;
import com.plinepay.authservice.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);

        // System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // System.err.println(username);
        // System.err.println(user);
        // System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    // Service d'ajout d'un nouvel utilisateur dans la base de données
    public User addUser(User user) {
        return userRepository.save(user);
    }


    // Liste de tous les utilisateur de la base de données
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
