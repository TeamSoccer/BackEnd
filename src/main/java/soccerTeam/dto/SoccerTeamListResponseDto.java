package soccerTeam.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SoccerTeamListResponseDto {
    private Long id;
    private String title;
    private String name;
    private String region;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SoccerTeamListResponseDto(Long id, String title, String name, String region, String day, LocalTime startTime, LocalTime endTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.region = region;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
