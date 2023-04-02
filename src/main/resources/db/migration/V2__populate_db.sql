INSERT INTO client(name) VALUES
('Tom'),
('Jane'),
('Oliver'),
('Sara'),
('Ben'),
('Ann'),
('Anis'),
('Paul'),
('John'),
('Deyv');

INSERT INTO planet(id,name) VALUES
('EAR1','VENUS'),
('MAR2','MARS'),
('PLU3','PLUTON'),
('EAR4','EARTH'),
('JUP5','JUPITER');

INSERT INTO ticket(client_id,from_planet_id, to_planet_id) VALUES
(1,'PLU3','EAR4'),
(1,'EAR4','PLU3'),
(2,'EAR1','JUP5'),
(2,'JUP5','EAR1'),
(3,'MAR2','EAR4'),
(3,'EAR4','MAR2'),
(4,'EAR1','JUP5'),
(4,'JUP5','EAR1'),
(5,'EAR1','MAR2'),
(5,'MAR2','EAR1');