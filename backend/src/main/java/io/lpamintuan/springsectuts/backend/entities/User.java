package io.lpamintuan.springsectuts.backend.entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_account")
@Data
public class User {
    
    @Id
    private UUID id;

    private String username;

    private String emailAddress;

    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") )
    private Set<Role> roles;

    public User() {

    }

    public User(UUID id, String username, String emailAddress, String password) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User(String id, String username, String emailAddress, String password) {
        this.id = UUID.fromString(id);
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User(String username, String emailAddress, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }


    @PrePersist
    public void setId() {
        this.id = UUID.randomUUID();
    }
}
