create table Category(id bigint, name varchar(25), primary key(id));
insert into Category values (1, 'Natur');
insert into Category values (2, 'Kultur');

create table User(id bigint, username varchar(25), primary key(id));
insert into User(1,'theErstellerIn');

create table Quiz(id bigint, title varchar(25), categoryId bigint, userId bigint, primary key(id), foreign key(categoryId) references Category(id), foreign key(userId) references QuizUser(id));
insert into Quiz values (1, 'Naturquiz', 1, 1);

create table Question(nr int, text varchar(25), quizId bigint ,primary key(nr), foreign key(quizId) references Quiz(id));

create table Answer(nr int, text varchar(25), correctAnswer boolean, questionNr int, primary key(nr), foreign key(questionNr) references Question(nr));

