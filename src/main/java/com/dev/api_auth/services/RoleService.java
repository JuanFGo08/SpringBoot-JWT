package com.dev.api_auth.services;

import com.dev.api_auth.models.dtos.CreateRoleDto;
import com.dev.api_auth.models.entitites.Role;
import com.dev.api_auth.models.enums.RoleList;
import com.dev.api_auth.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public boolean existsByName(String name) {
        return roleRepository.findByName(RoleList.valueOf(name)).isPresent();
    }

    public void createRole(CreateRoleDto roleDto) {
        Role role = new Role(RoleList.valueOf(roleDto.getName()));
        roleRepository.save(role);
    }

    public List<RoleList> list() {
        return roleRepository.findAllNames();
    }
}
