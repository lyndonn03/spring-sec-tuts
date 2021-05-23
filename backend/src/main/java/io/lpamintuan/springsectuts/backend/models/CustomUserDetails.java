package io.lpamintuan.springsectuts.backend.models;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails {

    private UUID id;
    private String username;
    private String emailAddress;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UUID id, String username, String emailAddress, String password,
    Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.authorities = authorities;
    }

    public CustomUserDetails() {
        
    }

    public static UserDetails build(User user) {
        Set<? extends GrantedAuthority> authorities = user.getRoles().stream()
                                                        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                                                        .collect(Collectors.toSet());
                                                        
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getEmailAddress(), user.getPassword(), authorities);
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    
}
