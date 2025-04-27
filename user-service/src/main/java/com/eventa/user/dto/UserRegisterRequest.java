package com.eventa.user.dto;

import com.eventa.user.entity.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterRequest {
	private String fullName;
    private String email;
    private String password;
    private Role role;  // User can choose: ATTENDEE or ORGANIZER
}
