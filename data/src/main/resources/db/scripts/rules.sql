insert into public.rule (defaultscore, description, isactive, name)
values (1000, 'Family with a child, if the spouse is on regular form of study.', true, 'Rule001')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (1000, 'Student, raising a child alone.', true, 'Rule002')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (1000, 'Student with a dead parent.', true, 'Rule003')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (1000, 'Student in a large family, if any of the siblings studies.', true, 'Rule004')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (1000, 'Disabled student.', true, 'Rule005')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (1000, 'Student, studying for doctor''s degree.', true, 'Rule006')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (1000, 'Foreign student.', true, 'Rule007')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (100, 'Student with grade 6.00.', true, 'Rule008')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (90, 'Student with grade >= 5.50 and < 6.00.', true, 'Rule009')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (80, 'Student with grade >= 4.50 and < 5.50.', true, 'Rule010')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (70, 'Student with grade >= 3.50 and < 4.50.', true, 'Rule011')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (60, 'Student with grade >= 3.00 and < 3.5.', true, 'Rule012')
on conflict do nothing;

insert into public.rule (defaultscore, description, isactive, name)
values (30, 'Student with grade < 3.', true, 'Rule013')
on conflict do nothing;