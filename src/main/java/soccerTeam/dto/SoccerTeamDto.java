package soccerTeam.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import lombok.Data;

@Data
public class SoccerTeamDto {
	private int teamIdx;
	private String title;
	private String teamName;
	private String region;
	private String teamNumber;
	private int teamPeriod;
	private String teamDay;
	private int teamTime;
	private String teamOld;
	private String needPosition;
	private int needPositionNumber;
	private int athleteNumber;
	private String contents;
	private int hitCnt;
	private Date createdDatetime;
	private String creatorId;
	private Date updatedDatetime;
	private String updatorId;
	
	private List<SoccerTeamFileDto> fileInfoList;
	
	public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
    
    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
    
 // 날짜 포맷팅
    public String getCreatedAtFormatted() {
        return formatDate(createdDatetime);
    }

    public String getUpdatedAtFormatted() {
        return formatDate(updatedDatetime);
    }

    private String formatDate(Date date) {
        if (date == null) return "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
 // getters and setters
    public int getTeamIdx() {
        return teamIdx;
    }

    public void setTeamIdx(int teamIdx) {
        this.teamIdx = teamIdx;
    }
}

