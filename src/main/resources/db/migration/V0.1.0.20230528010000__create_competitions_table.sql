CREATE TABLE competitions (
id BIGINT NOT NULL AUTO_INCREMENT,
discipline_id BIGINT NOT NULL,
code VARCHAR (20) NOT NULL,
displayed_name VARCHAR (100) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (discipline_id) REFERENCES disciplines(id) ON DELETE CASCADE,
UNIQUE INDEX code_index (code),
UNIQUE INDEX displayed_name_index (displayed_name)
);

DROP PROCEDURE IF EXISTS createCompetition;
SET @discipline_id = (SELECT id FROM disciplines WHERE code = 'FOOTBALL');
DELIMITER //
CREATE PROCEDURE createCompetition(IN p_code VARCHAR (20), IN p_displayed_name VARCHAR (100))
BEGIN
    INSERT INTO competitions (discipline_id, code, displayed_name) VALUES (@discipline_id, p_code, p_displayed_name);
END//
DELIMITER ;

CALL createCompetition('WC', 'FIFA Чемпионат мира');
CALL createCompetition('CL', 'UEFA Лига чемпионов');
CALL createCompetition('BL1', 'Немецкая Бундеслига');
CALL createCompetition('DED', 'Голландский Эредивизи');
CALL createCompetition('BSA', 'Бразильская серия А');
CALL createCompetition('PD', 'Испанская Примера');
CALL createCompetition('FL1', 'Французская Лига');
CALL createCompetition('ELC', 'Английская футбольная Лига');
CALL createCompetition('PL', 'Английская Премьер Лига');
CALL createCompetition('PPL', 'Португальская Примейра Лига');
CALL createCompetition('EC', 'Чемпионат Европы');
CALL createCompetition('SA', 'Итальянская серия А');
CALL createCompetition('CLI', 'Кубок Либертадорес');

DROP PROCEDURE createCompetition;
