package com.arcyriea.usersecuritypractice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class UserModel {
    
    private String email;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
}
