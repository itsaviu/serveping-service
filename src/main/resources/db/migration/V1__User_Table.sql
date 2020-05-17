create table users(
id bigint primary key auto_increment,
email_id varchar(200) not null unique,
username varchar(200) not null,
password text not null,
active boolean not null,
created_at timestamp
);


create table roles(
id bigint primary key auto_increment,
name varchar (200) not null
);

insert into roles values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

create table user_role (
id bigint primary key auto_increment,
user_id BIGINT not null,
role_id bigint not null
);

alter table user_role add constraint user_role_mapping_role FOREIGN KEY (role_id) references roles(id);

alter table user_role add constraint user_role_mapping_users FOREIGN KEY (user_id) references users(id);
