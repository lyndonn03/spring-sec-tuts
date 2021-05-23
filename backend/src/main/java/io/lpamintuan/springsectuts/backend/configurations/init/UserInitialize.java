package io.lpamintuan.springsectuts.backend.configurations.init;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.lpamintuan.springsectuts.backend.models.Role;
import io.lpamintuan.springsectuts.backend.models.User;
import io.lpamintuan.springsectuts.backend.models.Role.ROLE_TYPE;
import io.lpamintuan.springsectuts.backend.repositories.RoleRepository;
import io.lpamintuan.springsectuts.backend.repositories.UserRepository;

@Component
public class UserInitialize {

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void importEnumRoles() {
        List<Role.ROLE_TYPE> roles = Arrays.asList(ROLE_TYPE.values());
        roles.stream().forEach(role -> {
            roleRepository.save(new Role(role));
        });
    }

    @PostConstruct
    private void importTestUsers() {
        List<User> users = Arrays.asList(
            new User("user", "user@mail.com", passwordEncoder.encode("user")),
            new User("admin", "admin@mail.com", passwordEncoder.encode("admin"))
        );

        users.get(0).setRoles(new HashSet<Role>(Arrays.asList(new Role(ROLE_TYPE.ROLE_USER))));
        users.get(1).setRoles(new HashSet<Role>(Arrays.asList(new Role(ROLE_TYPE.ROLE_ADMIN))));

        users.stream().forEach(user -> {
            userRepository.save(user);
        });
    }
    
}
