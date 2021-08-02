
begin;
insert into category values ('Natur');
insert into category values ('Kultur');

insert into quiz values (1, 'jobernhard', 'Naturquiz', 'Natur');


insert into question values(1, 1, 'Was ist ein Apfel?', 1);
insert into question values(2, 2, 'Was ist ein Kürbis?', 1);

insert into answer values(1, 1, true, 'Ein Obst', 1);
insert into answer values(2, 2, false, 'Ein Gemüse', 1);

insert into answer values(4, 1, false, 'Ein Obst', 2);
insert into answer values(5, 2, true, 'Ein Gemüse', 2);
insert into answer values(6, 3, false, 'Ein Tier', 2);


insert into quiz values (2, 'laupeter', 'Kultur 101', 'Natur');

insert into question values(3, 1, 'Wo liegt London?', 2);
insert into question values(4, 2, 'Wer war Alan Turing?', 2);

insert into answer values(7, 1, true, 'In Großbritannien', 3);
insert into answer values(8, 2, false, 'In Frankreich', 3);

insert into answer values(9, 1, true, 'Ein Logiker', 4);
insert into answer values(10, 2, true, 'Ein Mathematiker', 4);
insert into answer values(11, 3, false, 'Ein Schriftsteller', 4);

commit;