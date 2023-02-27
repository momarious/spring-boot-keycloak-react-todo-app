package com.momarious.userapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	private String email;
	private String password;
	private String username;
	private String firstname;
	private String lastname;

	// private int statusCode;
	// private String status;
}
