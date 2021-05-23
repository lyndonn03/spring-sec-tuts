package io.lpamintuan.springsectuts.backend.services.implementations;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.lpamintuan.springsectuts.backend.models.Role;
import io.lpamintuan.springsectuts.backend.models.User;
import io.lpamintuan.springsectuts.backend.models.Role.ROLE_TYPE;
import io.lpamintuan.springsectuts.backend.repositories.UserRepository;
import io.lpamintuan.springsectuts.backend.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        if(user.getRoles() == null)
            user.setRoles(new HashSet<Role>(Arrays.asList(new Role(ROLE_TYPE.ROLE_USER))));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
}
