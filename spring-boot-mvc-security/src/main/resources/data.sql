create table users (
    id serial primary key,
    username varchar (30) not null,
    surname varchar (30) not null,
    email varchar (30) not null,
    password varchar (70) not null
);

create table roles (
    id serial primary key,
    role varchar (30) not null
);

create table users_roles (
    user_id int not null,
    role_id int not null,
    foreign key (user_id) references users (id),
    foreign key (role_id) references  roles (id)
);



insert into users (username, surname, email, password) values ('kylych', 'sydykov', 'kylych@mail.ru', '$2a$10$RXHSuZSCmXOE2zA/Qant3e3iYairp2.fYvIs5vBUEPhoxe9CU4pya');
insert into users (username, surname, email, password) values ('aktan', 'chakiev', 'aktan@mail.ru', '$2a$10$E4z3ywEvvxdNSbXWYZjoP.o/pHmm.3lNlEYcEja09mf95/4CeMLa6');



insert into roles (role) values ('ROLE_USER');
insert into roles (role) values ('ROLE_ADMIN');



insert into users_roles (user_id, role_id) values (1, 1);
insert into users_roles (user_id, role_id) values (2, 2);