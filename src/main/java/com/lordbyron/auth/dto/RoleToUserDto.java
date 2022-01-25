package com.lordbyron.auth.dto;

import lombok.Data;

@Data
public class RoleToUserDto {

    //@NotBlank(message = "Username is mandatory")
    private String username;
    //@NotBlank(message = "Role name is mandatory")
    private String roleName;
}
