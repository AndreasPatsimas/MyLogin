package org.patsimas.login.convert;

import java.util.StringJoiner;

import org.patsimas.login.domain.User;
import org.patsimas.login.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

	@Override
	public UserDto convert(User user) {
		
		return new UserDto(user.getId(), user.getUsername(), user.getPassword(), 
				user.getActive() == 1? true : false, buildAuthorities(user));
	}
	
	private String buildAuthorities(User user){
		
		StringJoiner authorities = new StringJoiner(",");

		user.getAuthorities().forEach(authority -> {
			
			authorities.add(authority.getDescription());
		});		
		return authorities.toString();
	}

}
