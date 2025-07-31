INSERT INTO person (email, lastName, firstName)
VALUES ('admin@mail.com', 'Vandame', 'JC')
RETURNING id;

INSERT INTO admin (password, id_person)
VALUES ('$2a$12$DMxVb9xr3RfTQ.S67cPyj.2PeGtcTtYOT4F6njVL.v3ClxZpDAh.S', 1);