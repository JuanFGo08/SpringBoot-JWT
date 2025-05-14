package com.dev.api_auth.repositories;

import com.dev.api_auth.models.entitites.Role;
import com.dev.api_auth.models.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleList name);
    List<Role> findByNameIn(List<RoleList> names);

    @Query("SELECT r.name FROM Role r")
    List<RoleList> findAllNames();
}
