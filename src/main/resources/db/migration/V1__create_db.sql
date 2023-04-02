CREATE TABLE Client(
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(200) NOT NULL CHECK(CHAR_LENGTH(name)>=3 )
);

CREATE TABLE Planet (
   id VARCHAR(50) NOT NULL PRIMARY KEY ,
   name VARCHAR(500) NOT NULL,
   CONSTRAINT planet_check_name CHECK(CHAR_LENGTH(name) >= 1),
    CONSTRAINT planet_check_id CHECK(regexp_like(id,'\b[A-Z0-9]+\b'))
 );

CREATE TABLE Ticket(
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
client_id BIGINT,
from_planet_id VARCHAR,
to_planet_id VARCHAR,
CONSTRAINT ticket_fk_client FOREIGN KEY (client_id) REFERENCES Client(id),
CONSTRAINT ticket_fk_from_planet FOREIGN KEY (from_planet_id) REFERENCES Planet(id),
CONSTRAINT ticket_fk_to_planet FOREIGN KEY (to_planet_id) REFERENCES Planet(id)
);