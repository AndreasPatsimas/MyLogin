package org.patsimas.login.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;

	private String username;

	private String password;

	private boolean active;

	private String authorities;
}
