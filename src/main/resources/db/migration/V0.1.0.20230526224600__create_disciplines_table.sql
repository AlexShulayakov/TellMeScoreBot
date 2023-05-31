CREATE TABLE disciplines (
id BIGINT NOT NULL AUTO_INCREMENT,
code VARCHAR (20) NOT NULL,
displayed_name VARCHAR (100) NOT NULL,
PRIMARY KEY (id),
UNIQUE INDEX code_index (code),
UNIQUE INDEX displayed_name_index (displayed_name)
);

INSERT INTO disciplines (code, displayed_name) VALUES
('FOOTBALL', 'Футбол'),
('ICE_HOCKEY', 'Хоккей'),
('BASKETBALL', 'Баскетбол');
