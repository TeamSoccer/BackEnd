package soccerTeam.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import soccerTeam.dto.CustomUserDetails;
import soccerTeam.exception.NotFoundException;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.player.repository.PlayerRepository;
import soccerTeam.type.player.PlayerErrorType;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //DB에서 조회
        PlayerEntity userData = playerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(PlayerErrorType.PLAYER_NOT_FOUND));

        if (userData != null) {
            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
