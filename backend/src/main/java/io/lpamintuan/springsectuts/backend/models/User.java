package io.lpamintuan.springsectuts.backend.models;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    private UUID id;

    @Column(length = 30)
    private String username;

    @Column(length = 100)
    private String emailAddress;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_account_roles",
               joinColumns = @JoinColumn(name = "user_account_id"),
               inverseJoinColumns = @JoinColumn(name = "role_name") )
    private Set<Role> roles;

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
