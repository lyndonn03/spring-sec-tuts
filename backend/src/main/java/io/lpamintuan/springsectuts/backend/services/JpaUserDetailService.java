package io.lpamintuan.springsectuts.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.lpamintuan.springsectuts.backend.entities.MyUserDetails;
import io.lpamintuan.springsectuts.backend.entities.User;
import io.lpamintuan.springsectuts.backend.repositories.UserRepository;

@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()) 
            throw new UsernameNotFoundException("User not found");
        return MyUserDetails.build(user.get());
    }
    
}
