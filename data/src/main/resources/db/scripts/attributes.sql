insert into public.attribute(defaultvalue, description, name) values ('6', 'Excellent grade', 'AExcellentGrade') on conflict do nothing;
insert into public.attribute(defaultvalue, description, name) values ('5.5', 'Excellent grade lower threshold', 'ALowExcellentGrade') on conflict do nothing;
insert into public.attribute(defaultvalue, description, name) values ('4.5', 'Excellent grade', 'AVeryGoodGrade') on conflict do nothing;
insert into public.attribute(defaultvalue, description, name) values ('3.5', 'Excellent grade', 'AGoodGrade') on conflict do nothing;
insert into public.attribute(defaultvalue, description, name) values ('3', 'Excellent grade', 'AAverageGrade') on conflict do nothing;
