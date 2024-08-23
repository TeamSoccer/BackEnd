package soccerTeam.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import soccerTeam.player.repository.PlayerEntity;

public class CustomUserDetails implements UserDetails {

	private final PlayerEntity playerEntity;

	public CustomUserDetails(PlayerEntity playerEntity) {
		this.playerEntity = playerEntity;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();

		collection.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {

				return playerEntity.getRole();
			}
		});

		return collection;
	}

	@Override
	public String getPassword() {
		return playerEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return playerEntity.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
