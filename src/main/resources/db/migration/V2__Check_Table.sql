create table check_type (
id bigint primary key auto_increment,
name varchar(200) not null
);

insert into check_type (name) values
('HTTP_HTTPS'),
('PING');

create table checks (
id bigint primary key auto_increment,
check_type_id bigint  not null,
name varchar(200) not null,
endpoint varchar (200) not null,
check_interval varchar(100) not null,
user_id bigint not null,
status varchar (200) not null,
is_delete boolean  not null,
created_at timestamp not null
);


alter table checks
  add constraint checks_user_fk foreign key (user_id) references users(id);

alter table checks
  add constraint check_type_checks_fk foreign key (check_type_id) references check_type(id);

create table checks_report (
id bigint primary key auto_increment,
check_id bigint not null,
response_time bigint not null,
response_status bigint not null,
is_available boolean not null,
is_delete boolean  not null,
created_at timestamp not null
);

alter table checks_report
  add constraint check_report_checks_fk FOREIGN KEY (check_id) references checks(id);

