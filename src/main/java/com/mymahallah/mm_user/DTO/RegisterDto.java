package com.mymahallah.mm_user.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterDto {
    private String email;
    private Integer matricNo;
    private Set<String> roles;
}
