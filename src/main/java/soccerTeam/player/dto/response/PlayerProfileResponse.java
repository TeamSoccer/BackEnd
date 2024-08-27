package soccerTeam.player.dto.response;

import soccerTeam.player.repository.PlayerEntity;

public record PlayerProfileResponse(
        Long id,
        String name,
        String email,
        String username,
        String phoneNumber,
        String region,
        Integer period,
        Integer age,
        Boolean athlete) {
    public static PlayerProfileResponse of(PlayerEntity player) {
        return new PlayerProfileResponse(
                player.getId(),
                player.getName(),
                player.getEmail(),
                player.getUsername(),
                player.getPhoneNumber(),
                player.getRegion(),
                player.getPeriod(),
                player.getAge(),
                player.getAthlete());
    }
}
