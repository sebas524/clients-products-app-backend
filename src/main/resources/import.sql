insert into tbl_regions (id, name) values  (1,'South America');
insert into tbl_regions (id, name) values  (2,'North America');
insert into tbl_regions (id, name) values  (3,'Central America');
insert into tbl_regions (id, name) values  (4,'Europe');
insert into tbl_regions (id, name) values  (5,'Asia');
insert into tbl_regions (id, name) values  (6,'Africa');

insert into tbl_roles (id,name) values (1,"ROLE_ADMIN");
insert into tbl_roles (id,name) values (2,"ROLE_USER");


insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(1,"john","velez","john@gmail.com","2023-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(2,"jenny","weinert","jenny@gmail.com","2020-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(1,"karl","johnson","karl@gmail.com","2021-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(5,"james","dean","james@gmail.com","2022-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(1,"bob","mole","bob@gmail.com","2023-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(1,"jesse","uribe","jesse@gmail.com","2023-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(1,"juan","gonzalez","juan@gmail.com","2020-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(2,"anikka","schmidt","anikka@gmail.com","2021-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(2,"vesna","radulovic","vesna@gmail.com","2022-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(3,"marcela","rueda","marcela@gmail.com","2023-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(5,"jesse","uribe","1jesse@gmail.com","2023-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(3,"juan","gonzalez","1juan@gmail.com","2020-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(6,"anikka","schmidt","1anikka@gmail.com","2021-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(3,"vesna","radulovic","1vesna@gmail.com","2022-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(1,"marcela","rueda","1marcela@gmail.com","2023-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(4,"jesse","uribe","12jesse@gmail.com","2023-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(4,"juan","gonzalez","12juan@gmail.com","2020-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(6,"anikka","schmidt","12anikka@gmail.com","2021-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(6,"vesna","radulovic","12vesna@gmail.com","2022-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(4,"marcela","rueda","12marcela@gmail.com","2023-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(3,"john","velez","3john@gmail.com","2023-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(3,"jenny","weinert","3jenny@gmail.com","2020-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(5,"karl","johnson","3karl@gmail.com","2021-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(5,"james","dean","3james@gmail.com","2022-01-01");
insert into tbl_clients(region_id, first_name,last_name,email,created_at)values(3,"bob","mole","3bob@gmail.com","2023-01-01");


insert into tbl_users(enabled, id, username, password)values(1,1,"johnny","123456");
insert into tbl_users(enabled, id, username, password)values(1,2,"broc","123456");
insert into tbl_users(enabled, id, username, password)values(1,3,"sammy","123456");




insert into user_roles(role_id, user_id)values(1,1);
insert into user_roles(role_id, user_id)values(2,1);
insert into user_roles(role_id, user_id)values(1,2);
insert into user_roles(role_id, user_id)values(2,2);
insert into user_roles(role_id, user_id)values(2,2);
insert into user_roles(role_id, user_id)values(1,3);






