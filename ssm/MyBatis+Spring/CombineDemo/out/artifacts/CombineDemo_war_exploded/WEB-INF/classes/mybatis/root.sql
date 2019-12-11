DROP TABLE IF EXISTS purchases;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS logs;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS suppliers;
create table employees
(
	eid varchar(3) not null,
	ename varchar(15),
	city varchar(15),
	primary key(eid)
)engine=InnoDB DEFAULT CHAR SET =utf8;

create table customers
(
	cid varchar(4) not null,
	cname varchar(15),
	city varchar(15),
	visits_made int(5),
	last_visit_time datetime,
	primary key(cid)
)engine=InnoDB DEFAULT CHAR SET =utf8;

create table suppliers
(
	sid varchar(2) not null,
	sname varchar(15) not null,
	city varchar(15),
	telephone_no char(10),
	primary key(sid),
	unique(sname)
)engine=InnoDB DEFAULT CHAR SET =utf8;

create table products
(
	pid varchar(4) not null,
	pname varchar(15) not null,
	qoh int(5) not null,
	qoh_threshold int(5),
	original_price decimal(6,2),
	discnt_rate decimal(3,2),
	sid varchar(2),
	primary key(pid),
	foreign key (sid) references suppliers (sid)
)engine=InnoDB DEFAULT CHAR SET =utf8;

create table purchases
(
	purid int not null,
	cid varchar(4) not null,
	eid varchar(3) not null,
	pid varchar(4) not null,
	qty int(5),
	ptime datetime,
	total_price decimal(7,2),
	primary key (purid),
	foreign key (cid) references customers(cid),
	foreign key (eid) references employees(eid),
	foreign key (pid) references products(pid)
)engine=InnoDB DEFAULT CHAR SET =utf8;

create table logs
(
	logid int(5) not null auto_increment,
	who varchar(10) not null,
	time datetime not null,
	table_name varchar(20) not null,
	operation varchar(6) not null,
	key_value varchar(4),
	primary key (logid)
)engine=InnoDB DEFAULT CHAR SET =utf8;

insert into employees values ('e00', 'Amy', 'Vestal');
insert into employees values ('e01', 'Bob', 'Binghamton');
insert into employees values ('e02', 'John', 'Binghamton');
insert into employees values ('e03', 'Lisa', 'Binghamton');
insert into employees values ('e04', 'Matt', 'Vestal');

insert into suppliers values ('s0', 'Supplier 1', 'Binghamton', '6075555431');
insert into suppliers values ('s1', 'Supplier 2', 'NYC', '6075555432');

insert into products values ('pr00', 'Milk', 12, 10, 2.40, 0.1, 's0');
insert into products values ('pr01', 'Egg', 20, 10, 1.50, 0.2, 's1');
insert into products values ('pr02', 'Bread', 15, 10, 1.20, 0.1, 's0');
insert into products values ('pr03', 'Pineapple', 6, 5, 2.00, 0.3, 's0');
insert into products values ('pr04', 'Knife', 10, 8, 2.50, 0.2, 's1');
insert into products values ('pr05', 'Shovel', 5, 5, 7.99, 0.1, 's0');

insert into customers values ('c000', 'Kathy', 'Vestal', 2, '2017-11-28 10:25:32');
insert into customers values ('c001', 'Brown', 'Binghamton', 1, '2017-12-05 09:12:30');
insert into customers values ('c002', 'Anne', 'Vestal', 1, '2018-11-29 14:30:00');
insert into customers values ('c003', 'Jack', 'Vestal', 1, '2018-12-04 16:48:02');
insert into customers values ('c004', 'Mike', 'Binghamton', 1, '2018-11-30 11:52:16');

insert into purchases values (1, 'c000', 'e00', 'pr00', 1, '2018-10-22 12:34:22', 2.16);
insert into purchases values (2, 'c001', 'e03', 'pr03', 2, '2018-12-05 09:12:30', 2.80);
insert into purchases values (3, 'c002', 'e03', 'pr00', 1, '2018-11-29 14:30:00', 2.16);
insert into purchases values (4, 'c000', 'e01', 'pr01', 5, '2018-11-28 10:25:32', 6.00);
insert into purchases values (5, 'c004', 'e04', 'pr02', 3, '2018-11-30 11:52:16', 3.24);
insert into purchases values (6, 'c003', 'e02', 'pr05', 1, '2018-12-04 16:48:02', 7.19);