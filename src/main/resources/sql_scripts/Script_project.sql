create table public.companies
(
	entity_legal	character varying(10),
	name character varying(50) not null,
	constraint company_entityLegal_PK primary key (entityLegal)
);

insert into companies (entity_legal, name)
values ('БСЗ', 'Брусника');

create table public.locations
(
	location_id bigint primary key,
	name character varying(50),
	entity_legal character varying(10),
	foreign key (entity_legal) references companies (entity_legal)
);

create table public.subdivisions
(
	sub_id bigint primary key,
	sub_name character varying(50),
	location_id bigint,
	foreign key (location_id) references locations (location_id)
);

create table public.departments
(
	depart_id bigint primary key,
	depart_name character varying(50),
	sub_id bigint,
	foreign key (sub_id) references subdivisions (sub_id)
);

create table public.groups
(
	group_id bigint primary key,
	group_name character varying(50),
	depart_id bigint,
	foreign key (depart_id) references departments (depart_id)
);

create table public.posts
(
	post_id bigserial primary key,
	name character varying(50)
);

create table public.type_works
(
	type_id bigserial primary key,
	type_name character varying(50)
);

create table public.workers
(
	id character varying(15) primary key,
	group_id bigint references groups (group_id),
	fio character varying(80),
	post_id bigint references posts (post_id),
	type_id bigint references type_works (type_id)
);

insert into locations (location_id, name, entity_legal)
values
(1000, 'Проектирование', 'БСЗ'),
(1001, 'Дирекция', 'БСЗ'),
(1002, 'Тюмень', 'БСЗ'),
(1003, 'Сургут', 'БСЗ'),
(1004, 'Омск', 'БСЗ'),
(1005, 'Новосибирск', 'БСЗ'),
(1006, 'Московская область', 'БСЗ'),
(1007, 'Екатеринбург', 'БСЗ'),
(1008, 'Штаб', 'БСЗ');

insert into subdivisions (sub_id, sub_name, location_id)
values
(1000, 'Арес', 1000),
(1001, 'Артемида', 1000),
(1002, 'Афина', 1000),
(1003, 'Гера', 1000),
(1004, '', 1000),
(1010, 'Афина', 1001),
(1011, 'Орфей', 1001),
(1020, 'Афина', 1002),
(1021, 'Гера', 1002),
(1022, 'Гермес', 1002),
(1030, 'Афина', 1003),
(1031, 'Гера', 1003),
(1032, 'Гермес', 1003),
(1033, '', 1003),
(1040, 'Афина', 1004),
(1041, 'Гера', 1004),
(1042, 'Гермес', 1004),
(1043, 'Гефест', 1004),
(1050, 'Афина', 1005),
(1051, 'Гера', 1005),
(1052, 'Гермес', 1005),
(1053, '', 1005),
(1054, 'Гефест', 1005),
(1060, 'Афина', 1006),
(1061, 'Гера', 1006),
(1062, 'Гермес', 1006),
(1063, '', 1006),
(1064, 'Гефест', 1006),
(1070, 'Афина', 1007),
(1071, 'Гера', 1007),
(1072, 'Гермес', 1007),
(1073, '', 1007),
(1074, 'Гефест', 1007),
(1075, 'Афродита', 1007),
(1080, 'Афродита', 1008),
(1081, 'Гера', 1008),
(1082, 'Гестия', 1008);

insert into departments (depart_id, depart_name, sub_id)
values
(1000, 'Бельгия', 1000),
(1001, 'Великобритания', 1000),
(1100, '', 1001),
(1200, '', 1002),
(1300, 'Хорватия', 1003),
(2000, '', 1010),
(3000, '', 1020),
(3100, 'Швейцария', 1021),
(3101, 'Сербия', 1021),
(3102, 'Словения', 1021),
(3103, 'Хорватия', 1021),
(4000, '', 1070),
(4100, '', 1075),
(4200, 'Швейцария', 1071),
(4201, '', 1071),
(4202, 'Сербия', 1071),
(4203, 'Словения', 1071),
(4204, 'Хорватия', 1071);

insert into public."groups" (group_id, group_name, depart_id)
values
(0001, 'Москва', 1000),
(0002, 'Стамбул', 1001),
(0003, '', 1001),
(0010, 'Лондон', 1100),
(0020, 'Санкт-Петербург', 1200),
(0030, '', 1300),
(1001, 'Санкт-Петербург', 2000),
(1002, '', 2000),
(2000, '', 3000),
(2010, '', 3100),
(2020, 'Нижний Новгород', 3101),
(2021, 'Самара', 3101),
(2022, '', 3101),
(2030, '', 3102),
(2040, 'Уфа', 3103),
(2041, '', 3103);

insert into public.type_works (type_name)
values
('Бизнес'),
('Сервис'),
('БП/Цифра');

insert into public.posts (name) values
('Директор'),
('Руководитель отдела'),
('Руководитель группы'),
('Главный специалист-архитектор'),
('Главный специалист-инженер'),
('Ведущий инженер'),
('Ведущий конструктор'),
('Специалист'),
('Главный ассистент'),
('Главный бизнес-ассистент'),
('Главный бухгалтер');

insert into public.workers (id, group_id, fio, post_id, type_id) values
('БСЗ131', 0001, 'Вакансия', 4, 1),
('БСЗ132', 0002, 'Вакансия', 8, 2);