INSERT INTO person (email, lastName, firstName)
VALUES ('admin@mail.com', "Vandame", "JC")
RETURNING id_person;

INSERT INTO admin (password, id_person)
VALUES ('motdepasse123', 1);