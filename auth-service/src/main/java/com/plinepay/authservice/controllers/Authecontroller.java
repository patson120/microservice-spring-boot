package com.plinepay.authservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plinepay.authservice.models.JwtRequest;
import com.plinepay.authservice.models.JwtResponse;
import com.plinepay.authservice.models.User;
import com.plinepay.authservice.services.UserService;
import com.plinepay.authservice.utility.JWTUtility;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class Authecontroller {
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    // Ajout d'un nouvel utilisateur dans la base de données
    @PostMapping("/register")
    public User  register(@RequestBody User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.addUser(user);
    }


    // Authentification d'un nouvel utilisateur
    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }

    // Liste de tous les utilisateur de la base de données
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

}
