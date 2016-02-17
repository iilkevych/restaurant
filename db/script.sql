USE restaurant;

CREATE TABLE IF NOT EXISTS ACCOUNTS (
  ID bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  FIRSTNAME varchar(255) NOT NULL,
  LASTNAME varchar(255) NOT NULL,
  PASSWORD varchar(255) NOT NULL,
  USERNAME varchar(255) NOT NULL UNIQUE,
  ENABLED smallint(6) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ACCOUNT_ROLES (
  ID bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ROLE int(11) NOT NULL,
  USER_ID bigint(20) NOT NULL,
  CONSTRAINT AR_FK FOREIGN KEY (USER_ID) REFERENCES ACCOUNTS (ID)
  ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS RESTAURANTS (
  ID bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME varchar(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS MENUS (
  ID bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  REST_ID bigint(20) NOT NULL,
  PRICE bigint(20),
  CONSTRAINT MR_FK FOREIGN KEY (REST_ID) REFERENCES RESTAURANTS (ID)
  ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS DISHES (
  ID bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  MENU_ID bigint(20) NOT NULL,
  NAME varchar(255) NOT NULL,
  PRICE bigint(20),
  CONSTRAINT DM_FK FOREIGN KEY (MENU_ID) REFERENCES MENUS (ID)
  ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS VOTES (
  ACCOUNT_ID bigint(20) NOT NULL PRIMARY KEY,
  MENU_ID bigint(20) NOT NULL,
  CONSTRAINT VM_FK FOREIGN KEY (MENU_ID) REFERENCES MENUS (ID)
  ON DELETE CASCADE,
  CONSTRAINT VA_FK FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNTS (ID)
  ON DELETE CASCADE
) ENGINE=InnoDB;

DELIMITER $$
CREATE TRIGGER VOTES_UPD 
	BEFORE UPDATE
    ON VOTES
    FOR EACH ROW
    BEGIN
        IF CURTIME() > '11:00:00'
		THEN
            SIGNAL SQLSTATE '45000' SET message_text = 'You can not change your vote after 11am' ;
		END IF;
    END$$
DELIMITER ;