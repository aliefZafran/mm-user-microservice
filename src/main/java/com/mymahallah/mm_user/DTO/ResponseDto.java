package com.mymahallah.mm_user.DTO;

import com.mymahallah.mm_user.entities.Role;
import lombok.Data;
import java.util.Set;


@Data
public class ResponseDto {
    private String email;
    private String password;
    private Set<Role> roles;
}
