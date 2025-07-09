CREATE TABLE IF NOT EXISTS posts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    heading VARCHAR(150) NOT NULL,
    body VARCHAR(2048),
    likes BIGINT,
    image BLOB,
    creation_timestamp TIMESTAMP NOT NULL
);

    CREATE TABLE IF NOT EXISTS tags (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    post_id BIGINT REFERENCES posts(id) ON DELETE SET NULL,
    tag VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS commentaries (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    post_id BIGINT NOT NULL REFERENCES posts(id) ON DELETE CASCADE,
    text VARCHAR(150),
    creation_timestamp TIMESTAMP NOT NULL
);

INSERT INTO posts(heading, body, creation_timestamp) VALUES ('What is Lorem Ipsum?', 'What is Lorem Ipsum?
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s ' ||
                                                                 'standard dummy text ever since the 1500s, ' ||
                                                                 'when an unknown printer took a galley of ' ||
                                                                 'type and scrambled it to make a type' ||
                                                                 'specimen book.', CURRENT_TIMESTAMP);
INSERT INTO tags(post_id, tag) VALUES (1, 'knowledge');
INSERT INTO tags(post_id, tag) VALUES (1, 'frontend');
INSERT INTO tags(post_id, tag) VALUES (1, 'it');

INSERT INTO posts(heading, body, creation_timestamp) VALUES ('Для провального экшена Marvel''s Avengers хотели выпустить большое' ||
                                         'дополнение с Капитаном Марвел и новыми героями', 'Супергеройский экшен ' ||
                                        'Marvel''s Avengers от Crystal Dynamics и Square Enix обещал стать масштабной ' ||
                                        'игрой-сервисом с продолжительной поддержкой, но стал катастрофическим провалом ' ||
                                        'для разработчиков. Поддержка игры уже давно прекращена, и проект официально ' ||
                                        'был изъят из публичного доступа, но как стало известно сегодня, ' ||
                                        'перед этим для него хотели выпустить последнее финальное обновление с новым контентом.',CURRENT_TIMESTAMP);

INSERT INTO tags(post_id, tag) VALUES (2, 'games');
INSERT INTO tags(post_id, tag) VALUES (2, 'marvel');
INSERT INTO tags(post_id, tag) VALUES (2, 'enix');
INSERT INTO tags(post_id, tag) VALUES (2, 'avengers');

INSERT INTO posts(heading, body, creation_timestamp) VALUES ('Heading3', 'Text3', CURRENT_TIMESTAMP);

INSERT INTO tags(post_id, tag) VALUES (3, 'tag1');
INSERT INTO tags(post_id, tag) VALUES (3, 'tag2');
INSERT INTO tags(post_id, tag) VALUES (3, 'tag3');

INSERT INTO posts(heading, body, creation_timestamp) VALUES ('4', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('5', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('6', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('7', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('8', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('9', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('10', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('11', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('12', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('13', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('14', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('15', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('16', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('17', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('18', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('19', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('20', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('21', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('22', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('23', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('24', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('25', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('26', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('27', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('28', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('29', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);
INSERT INTO posts(heading, body, creation_timestamp) VALUES ('30', 'WARNEVERCHANGES', CURRENT_TIMESTAMP);

