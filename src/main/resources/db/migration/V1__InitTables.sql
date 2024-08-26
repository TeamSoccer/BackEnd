CREATE TABLE player_entity (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(255) NOT NULL,
                               email VARCHAR(255) NOT NULL,
                               username VARCHAR(255) UNIQUE NOT NULL,
                               password VARCHAR(255) NOT NULL,
                               role INT NOT NULL,
                               phone_number VARCHAR(255) NOT NULL,
                               region VARCHAR(255) NOT NULL,
                               `period` INT,
                               age INT NOT NULL,
                               athlete BOOLEAN NOT NULL
);

CREATE TABLE soccer_team_entity (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             player_id BIGINT,
                             title VARCHAR(255) NOT NULL,
                             name VARCHAR(255) NOT NULL,
                             region VARCHAR(255) NOT NULL,
                             phone_number VARCHAR(255) NOT NULL,
                             `period` INT,
                             day VARCHAR(255),
                             start_time TIME,
                             end_time TIME,
                             age_average INT,
                             need_position VARCHAR(255),
                             need_position_cnt VARCHAR(255),
                             athlete_cnt INT,
                             content VARCHAR(255),
                             hit_cnt INT DEFAULT 0,
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             CONSTRAINT fk_team_player FOREIGN KEY (player_id) REFERENCES player_entity(id)
);


CREATE TABLE enroll_entity (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               team_id BIGINT NOT NULL,
                               player_id BIGINT NOT NULL,
                               title VARCHAR(255) NOT NULL,
                               content VARCHAR(255) NOT NULL,
                               hit_cnt INT DEFAULT 0,
                               created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                               updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               CONSTRAINT fk_enroll_team FOREIGN KEY (team_id) REFERENCES soccer_team_entity(id),
                               CONSTRAINT fk_enroll_player FOREIGN KEY (player_id) REFERENCES player_entity(id)
);


CREATE TABLE soccer_team_file_entity (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              team_id BIGINT NOT NULL,
                              origin_image_name VARCHAR(255),
                              image_url VARCHAR(255),
                              size INT,
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              CONSTRAINT fk_image_team FOREIGN KEY (team_id) REFERENCES soccer_team_entity(id)
);