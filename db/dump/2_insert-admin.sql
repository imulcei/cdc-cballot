INSERT INTO person (email)
VALUES ('admin@mail.com')
RETURNING id_person;

INSERT INTO admin (password, id_person)
VALUES ('motdepasse123', 1);