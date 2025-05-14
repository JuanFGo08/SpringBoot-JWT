package com.dev.api_auth.models.entitites;

import com.dev.api_auth.models.enums.RoleList;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Role extends Base{

    @Column(nullable = false)
    private RoleList name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(RoleList name) {
        this.name = name;
    }
}
