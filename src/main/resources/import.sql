
begin;
insert into category values ('Natur');
insert into category values ('Kultur');

/*insert into quiz values (1, 'jobernhard', 'Naturquiz', 'Natur');


insert into question values(1, 1, 'Was ist ein Apfel?', 1);
insert into question values(2, 2, 'Was ist ein Kürbis?', 1);

insert into answer values(1, 1, true, 'Ein Obst', 1);
insert into answer values(2, 2, false, 'Ein Gemüse', 1);

insert into answer values(4, 1, false, 'Ein Obst', 2);
insert into answer values(5, 2, true, 'Ein Gemüse', 2);
insert into answer values(6, 3, false, 'Ein Tier', 2);*/


insert into quiz values (200, 'laupeter', 'Kultur 101', 'Natur');

insert into question values(30, 1, 'Wo liegt London?', 200);
insert into question values(40, 2, 'Wer war Alan Turing?', 200);

insert into answer values(70, 1, true, 'In Großbritannien', 30);
insert into answer values(80, 2, false, 'In Frankreich', 30);

insert into answer values(90, 1, true, 'Ein Logiker', 40);
insert into answer values(100, 2, true, 'Ein Mathematiker', 40);
insert into answer values(110, 3, false, 'Ein Barde', 40);

commit;