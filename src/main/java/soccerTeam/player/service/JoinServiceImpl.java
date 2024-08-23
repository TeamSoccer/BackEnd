package soccerTeam.player.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import soccerTeam.dto.JoinDto;
import soccerTeam.exception.BadRequestException;
import soccerTeam.player.dto.response.PlayerSimpleResponse;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.player.repository.PlayerRepository;
import soccerTeam.type.player.PlayerErrorType;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final PlayerRepository playerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public PlayerSimpleResponse joinProcess(JoinDto joinDto) {
        // 동일한 username을 사용하고 있는지 확인
        if (playerRepository.existsByUsername(joinDto.getUsername())) {
            throw new BadRequestException(PlayerErrorType.USERNAME_ALREADY_EXIST);
        }

        // 패스워드와 패스워드 확인이 일치하는지 확인
        if (!joinDto.checkPassword()) {
            throw new BadRequestException(PlayerErrorType.CONFIRM_PASSWORD_NOT_MATCH);
        }

        // JoinDto의 값을 UserEntity에 설정
        PlayerEntity playerEntity = PlayerEntity.builder()
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .username(joinDto.getUsername())
                .password(joinDto.getPassword())
                .phoneNumber(joinDto.getPhoneNumber())
                .period(joinDto.getPeriod())
                .age(joinDto.getAge())
                .athlete(joinDto.getAthlete())
                .build();

        // 패스워드 암호화 처리 및 역할을 설정
        playerEntity.setPassword(bCryptPasswordEncoder.encode(playerEntity.getPassword()));
        playerEntity.setRole("ROLE_USER"); // 사용자 역할을 구분하는 문자로 "ROLE_" 접두어를 사용

        // UserEntity를 저장
        PlayerEntity player = playerRepository.save(playerEntity);
        return new PlayerSimpleResponse(player.getId(), player.getUsername());
    }
}
