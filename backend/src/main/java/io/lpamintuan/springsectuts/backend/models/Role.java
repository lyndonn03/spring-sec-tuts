package io.lpamintuan.springsectuts.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private ROLE_TYPE name;

    public static enum ROLE_TYPE {
        ROLE_USER, ROLE_ADMIN
    }

    
}
