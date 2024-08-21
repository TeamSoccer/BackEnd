package soccerTeam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import soccerTeam.dto.CustomUserDetail;
import soccerTeam.entity.UserEntity;
import soccerTeam.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("등록된 사용자가 없습니다.");
		}
		return new CustomUserDetail(userEntity);
	}
}

