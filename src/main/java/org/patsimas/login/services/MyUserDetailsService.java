package org.patsimas.login.services;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.login.domain.User;
import org.patsimas.login.dto.MyUserDetails;
import org.patsimas.login.dto.UserDto;
import org.patsimas.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ConversionService conversionService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("Load user process [username: {}] start", username);

		Optional<User> user = userRepository.findByUsername(username);

		if(user.isPresent()){
			UserDto userDto = conversionService.convert(user.get(), UserDto.class);

			log.info("Load user process completed");

			return new MyUserDetails(userDto);
		}

		user.orElseThrow(() -> new UsernameNotFoundException("Not found " + username));

		return null;
	}

}
