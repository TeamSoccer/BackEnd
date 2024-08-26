package soccerTeam.common;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import soccerTeam.team.dto.SoccerTeamFileDto;
import soccerTeam.exception.InternalServerException;
import soccerTeam.type.common.CommonErrorType;

@Component
public class FileUtils {
    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    public List<SoccerTeamFileDto> parseFileInfo(Long teamIdx, MultipartFile[] files) {
        
        if (ObjectUtils.isEmpty(files)) {
            return null;
        }
        
        // 파일 정보를 저장할 객체를 생성 => 해당 메서드에서 반환하는 값
        List<SoccerTeamFileDto> fileInfoList = new ArrayList<>();
        
        // 파일을 저장할 디렉터리를 지정 (날짜별로 저장하고 존재하지 않는 경우 생성)
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime now = ZonedDateTime.now();
        String datePath = now.format(dtf);
        String storedDir = uploadDir + File.separator + datePath;
        File dir = new File(storedDir);
        if (!dir.exists()) {
            dir.mkdirs();  // 다중 디렉터리 생성
        }
        
        for (MultipartFile file : files) {
			if (file.isEmpty()) continue;
			
			String contentType = file.getContentType();
			if (ObjectUtils.isEmpty(contentType)) continue;
			
			// Content-Type을 체크해서 이미지 파일인 경우에 한 해서
			// 지정된 확장자로 저장되도록 설정 
			String fileExtension = "";
			if (contentType.contains("jpeg")) {
				fileExtension = ".jpg";
			} else if (contentType.contains("png")) {
				fileExtension = ".png";
			} else if (contentType.contains("gif")) {
				fileExtension = ".gif";
			} else {
				continue;
			}
			
			// 저장에 사용할 파일 이름을 조합 (현재 시간을 파일명으로 사용)
			String storedFileName = System.nanoTime() + fileExtension;
			
			// 파일 정보를 리스트에 저장
			SoccerTeamFileDto dto = SoccerTeamFileDto.builder()
					.teamId(teamIdx)
					.originImageName(file.getOriginalFilename())
					.imageUrl(storedDir + "/" + storedFileName)
					.size(file.getSize())
					.build();
			fileInfoList.add(dto);
			
			// 파일 저장
			dir = new File(storedDir + "/" + storedFileName);
			try {
				file.transferTo(dir);
			} catch (IOException exception) {
				throw new InternalServerException(CommonErrorType.IO);
			}
		}
        
        return fileInfoList;
    }

	public void deleteFiles(List<String> fileNames) {
		for (String fileName : fileNames) {
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
		}
	}
}
