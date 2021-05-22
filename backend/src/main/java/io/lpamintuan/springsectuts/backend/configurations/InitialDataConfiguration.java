package io.lpamintuan.springsectuts.backend.configurations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import io.lpamintuan.springsectuts.backend.entities.ERole;
import io.lpamintuan.springsectuts.backend.entities.Role;
import io.lpamintuan.springsectuts.backend.entities.User;
import io.lpamintuan.springsectuts.backend.repositories.RoleRepository;
import io.lpamintuan.springsectuts.backend.repositories.UserRepository;

@Configuration
public class InitialDataConfiguration {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    
    @PostConstruct
    public void addRolesToDB() {
        List<ERole> roles = Arrays.asList(ERole.values());
        roles.stream().forEach(role -> {
            Role r = new Role(role);
            roleRepository.save(r);
        });
    }

    @PostConstruct
    public void addUsersToDB() {
        User user1  = new User("user", "test@mail.com", "user");
        user1.setRoles(new HashSet<Role>(Arrays.asList(
            new Role(ERole.ROLE_USER)
        )));
        userRepository.save(user1);

        User user2  = new User("admin", "test@mail.com", "admin");
        user2.setRoles(new HashSet<Role>(Arrays.asList(
            new Role(ERole.ROLE_ADMIN)
        )));
        userRepository.save(user2);
    }
}
