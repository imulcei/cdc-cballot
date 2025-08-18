CREATE TABLE person(
   id UUID,
   email VARCHAR(50)  NOT NULL,
   lastname VARCHAR(250)  NOT NULL,
   firstname VARCHAR(200) ,
   PRIMARY KEY(id),
   UNIQUE(email)
);

CREATE TABLE "admin"(
   id UUID,
   "password" VARCHAR(100)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(id),
   FOREIGN KEY(id) REFERENCES person(id)
);

CREATE TABLE teacher(
   id UUID,
   "password" VARCHAR(100)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(id),
   FOREIGN KEY(id) REFERENCES person(id)
);

CREATE TABLE course(
   id SERIAL,
   libelle VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE session(
   id SERIAL,
   name VARCHAR(50)  NOT NULL,
   end_date DATE,
   start_date DATE NOT NULL,
   id_course INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_course) REFERENCES course(id)
);

CREATE TABLE election(
   id SERIAL,
   "start_date" TIMESTAMP NOT NULL,
   end_date TIMESTAMP NOT NULL,
   id_session INTEGER NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(id_session),
   FOREIGN KEY(id_session) REFERENCES session(id)
);

CREATE TABLE pair(
   id INTEGER,
   "counter" SERIAL,
   id_election INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_election) REFERENCES election(id)
);

CREATE TABLE student(
   id UUID,
   id_pair INTEGER,
   id_session INTEGER NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(id),
   FOREIGN KEY(id_pair) REFERENCES pair(id),
   FOREIGN KEY(id_session) REFERENCES session(id),
   FOREIGN KEY(id) REFERENCES person(id)
);

CREATE TABLE voter(
   id UUID,
   vote_cast BOOLEAN,
   email VARCHAR(250)  NOT NULL,
   id_election INTEGER NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(email),
   FOREIGN KEY(id_election) REFERENCES election(id)
);


CREATE TABLE teacher_course(
   id_teacher UUID,
   id_course INTEGER,
   PRIMARY KEY(id_teacher, id_course),
   FOREIGN KEY(id_teacher) REFERENCES teacher(id),
   FOREIGN KEY(id_course) REFERENCES course(id)
);
