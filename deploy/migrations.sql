DROP TABLE IF EXISTS Pet;
CREATE TABLE Pet(petId SERIAL PRIMARY KEY, petType VARCHAR(255), petName VARCHAR(255), petAge INT);

DROP TABLE IF EXISTS PetType;
CREATE TABLE PetType(id SERIAL PRIMARY KEY, petType VARCHAR(255));

INSERT INTO Pet
VALUES  (1,'Cat','kitty',3),
        (2,'Dog','Blacky',3),
        (3,'Pig','Fatty',5);

INSERT INTO PetType
VALUES  (1,'Cat'),
        (2,'Dog'),
        (3,'Pig');