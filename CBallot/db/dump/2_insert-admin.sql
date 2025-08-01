INSERT INTO person (id, email, lastName, firstName)
VALUES (gen_random_uuid(), 'admin@mail.com', 'Vandame', 'JC')
RETURNING id as id_person;

INSERT INTO admin ("password", id)
VALUES ('$2a$12$DMxVb9xr3RfTQ.S67cPyj.2PeGtcTtYOT4F6njVL.v3ClxZpDAh.S', (SELECT id FROM person WHERE email = 'admin@mail.com'));