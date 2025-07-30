CREATE TABLE person(
   id_person SERIAL PRIMARY KEY,
   email VARCHAR(50)  NOT NULL,
   UNIQUE(email)
);

CREATE TABLE admin(
   id SERIAL PRIMARY KEY,
   "password" VARCHAR(50)  NOT NULL,
   id_person INTEGER NOT NULL,
   UNIQUE(id_person),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE teacher(
   id SERIAL PRIMARY KEY,
   "password" VARCHAR(50)  NOT NULL,
   id_person INTEGER NOT NULL,
   UNIQUE(id_person),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE formation(
   id_class SERIAL PRIMARY KEY,
   "name" VARCHAR(50)  NOT NULL
);

CREATE TABLE session(
   id SERIAL PRIMARY KEY,
   "name" VARCHAR(50)  NOT NULL,
   id_class INTEGER NOT NULL,
   FOREIGN KEY(id_class) REFERENCES formation(id_class)
);

CREATE TABLE election(
   id_election SERIAL PRIMARY KEY,
   "start_date" TIMESTAMP NOT NULL,
   end_date TIMESTAMP NOT NULL,
   id_session INTEGER NOT NULL,
   UNIQUE(id_session),
   FOREIGN KEY(id_session) REFERENCES session(id)
);

CREATE TABLE pair(
   id_binome INTEGER PRIMARY KEY,
   full_name VARCHAR(50)  NOT NULL,
   pair_number INTEGER NOT NULL,
   "counter" SERIAL,
   id_election INTEGER NOT NULL,
   FOREIGN KEY(id_election) REFERENCES election(id_election)
);

CREATE TABLE vote(
   id_vote INTEGER PRIMARY KEY,
   vote_cast BOOLEAN,
   id_unique UUID NOT NULL,
   id_election INTEGER NOT NULL,
   FOREIGN KEY(id_election) REFERENCES election(id_election)
);

CREATE TABLE student(
   id_student SERIAL PRIMARY KEY,
   id_vote INTEGER,
   id_binome INTEGER,
   id_session INTEGER NOT NULL,
   id_person INTEGER NOT NULL,
   UNIQUE(id_person),
   FOREIGN KEY(id_vote) REFERENCES vote(id_vote),
   FOREIGN KEY(id_binome) REFERENCES pair(id_binome),
   FOREIGN KEY(id_session) REFERENCES session(id),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE teacher_formation(
   id_teacher INTEGER,
   id_formation INTEGER,
   PRIMARY KEY(id_teacher, id_formation),
   FOREIGN KEY(id_teacher) REFERENCES teacher(id),
   FOREIGN KEY(id_formation) REFERENCES formation(id_class)
);
