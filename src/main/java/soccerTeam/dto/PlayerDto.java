package soccerTeam.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class PlayerDto {
	private int playerIdx;
	private String title;
	private String playerName;
	private String region;
	private String playerNumber;
	private int playerPeriod;
	private int playerOld;
	private String playerPosition;
	private Boolean playerAthlete;
	private String contents;
	private int hitCnt;
	private Date createdDatetime;
	private String creatorId;
	private Date updatedDatetime;
	private String updatorId;
	private int teamIdx;
	
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
    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }
	
}
