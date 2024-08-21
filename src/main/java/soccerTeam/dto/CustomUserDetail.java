package soccerTeam.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import soccerTeam.entity.UserEntity;

public class CustomUserDetail implements UserDetails {
	private UserEntity userEntity;
	
	public CustomUserDetail(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList();
		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return userEntity.getRole();
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}

}

