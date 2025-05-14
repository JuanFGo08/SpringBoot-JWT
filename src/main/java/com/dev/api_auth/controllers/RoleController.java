package com.dev.api_auth.controllers;

import com.dev.api_auth.models.dtos.CreateRoleDto;
import com.dev.api_auth.models.entitites.Role;
import com.dev.api_auth.models.enums.RoleList;
import com.dev.api_auth.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    public final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateRoleDto roleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        if (roleService.existsByName(roleDto.getName())) {
            return ResponseEntity.badRequest().body("Role already exists");
        }
        roleService.createRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role created successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, List<RoleList>>> list() {
        List<RoleList> roles = roleService.list();
        Map<String, List<RoleList>> response = new HashMap<>();
        response.put("roles", roles);

        return ResponseEntity.ok(response);
    }
}
