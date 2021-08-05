
begin;
insert into category values ('Natur');
insert into category values ('Kultur');

insert into quiz values (100, 'jobernhard', 'Naturquiz', 'Natur');


insert into question values(10, 1, 'Was ist ein Apfel?', 100);
insert into question values(20, 2, 'Was ist ein Kürbis?', 100);

insert into answer values(15, 1, true, 'Ein Obst', 10);
insert into answer values(25, 2, false, 'Ein Gemüse', 10);

insert into answer values(45, 1, false, 'Ein Obst', 20);
insert into answer values(55, 2, true, 'Ein Gemüse', 20);
insert into answer values(65, 3, false, 'Ein Tier', 20);


insert into quiz values (200, 'laupeter', 'Kultur 101', 'Natur');

insert into question values(30, 1, 'Wo liegt London?', 200);
insert into question values(40, 2, 'Wer war Alan Turing?', 200);

insert into answer values(70, 1, true, 'In Großbritannien', 30);
insert into answer values(80, 2, false, 'In Frankreich', 30);

insert into answer values(90, 1, true, 'Ein Logiker', 40);
insert into answer values(100, 2, true, 'Ein Mathematiker', 40);
insert into answer values(110, 3, false, 'Ein Barde', 40);

commit;