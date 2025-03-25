package com.dev.api_auth.models.dtos;

import com.dev.api_auth.models.enums.RoleList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    public String userName;
    public String email;
    public String password;
    public String confirmPassword;
    public List<RoleList> roles;
}
