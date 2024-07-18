package br.com.victor.authapi.config;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.victor.authapi.config.security.SecurityConfiguration;
import br.com.victor.authapi.domain.entities.User;
import br.com.victor.authapi.domain.repositories.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    
    @Autowired
    SecurityConfiguration security;

    @Override
    public void run(String... args) throws Exception {
    	userRepository.deleteAll();
    	
    	
    	User user1 = new User(null, "victor", "jv@gmail.com", security.passwordEncoder().encode("12345678"));
    	
       
    	userRepository.saveAll(Arrays.asList(user1));
    	
    }
}
