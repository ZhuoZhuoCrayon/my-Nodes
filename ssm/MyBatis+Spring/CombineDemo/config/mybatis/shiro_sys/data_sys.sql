use company_sys;
insert into sys_users values(1,'admin','95657d3e3052fb39d70d610c70a9a575','87cc486c53b49f72f5b96bb55d93bc7f');
insert into sys_users values(2,'tycoding','7de3848c92d39e98f7c74139f1a079d7','6478ccf88032592fe9396f008408400b');

insert into sys_roles values(1,'admin', '管理员');
insert into sys_roles values(2,'user','普通用户');


insert into sys_users_roles values(1,1, 1);
insert into sys_users_roles values(2,2, 2);

insert into sys_permissions values(1, '/customers/detail','perms[/customers/detail]','获取客户信息');
insert into sys_permissions values(2, '/customers/findById','perms[/customers/findById]','按id获取用户信息');
insert into sys_permissions values(3, '/customers/insert','perms[/customers/insert]','插入客户信息');
insert into sys_permissions values(4, '/customers/update','perms[/customers/update]','获取客户信息');
insert into sys_permissions values(5, '/customers/delete','perms[/customers/delete]','删除客户信息');

insert into sys_permissions values(6, '/employees/detail','perms[/employees/detail]','获取员工信息');
insert into sys_permissions values(7, '/employees/findById','perms[/employees/findById]','按id获取员工信息');
insert into sys_permissions values(8, '/employees/insert','perms[/employees/insert]','插入员工信息');
insert into sys_permissions values(9, '/employees/update','perms[/employees/update]','获取员工信息');
insert into sys_permissions values(10, '/employees/delete','perms[/employees/delete]','删除员工信息');

insert into sys_permissions values(11, '/logs/detail','perms[/logs/detail]','获取日志信息');
insert into sys_permissions values(12, '/logs/findById','perms[/logs/findById]','按id获取日志信息');
insert into sys_permissions values(13, '/logs/insert','perms[/logs/insert]','插入日志信息');
insert into sys_permissions values(14, '/logs/update','perms[/logs/update]','获取日志信息');
insert into sys_permissions values(15, '/logs/delete','perms[/logs/delete]','删除日志信息');

insert into sys_permissions values(16, '/products/detail','perms[/products/detail]','获取商品信息');
insert into sys_permissions values(17, '/products/findById','perms[/products/findById]','按id获取商品信息');
insert into sys_permissions values(18, '/products/insert','perms[/products/insert]','插入商品信息');
insert into sys_permissions values(19, '/products/update','perms[/products/update]','获取商品信息');
insert into sys_permissions values(20, '/products/delete','perms[/products/delete]','删除商品信息');

insert into sys_permissions values(21, '/purchases/detail','perms[/purchases/detail]','获取订单信息');
insert into sys_permissions values(22, '/purchases/findById','perms[/purchases/findById]','按id获取订单信息');
insert into sys_permissions values(23, '/purchases/insert','perms[/purchases/insert]','插入订单信息');
insert into sys_permissions values(24, '/purchases/update','perms[/purchases/update]','获取订单信息');
insert into sys_permissions values(25, '/purchases/delete','perms[/purchases/delete]','删除订单信息');

insert into sys_permissions values(26, '/suppliers/detail','perms[/suppliers/detail]','获取供应商信息');
insert into sys_permissions values(27, '/suppliers/findById','perms[/suppliers/findById]','按id获取供应商信息');
insert into sys_permissions values(28, '/suppliers/insert','perms[/suppliers/insert]','插入供应商信息');
insert into sys_permissions values(29, '/suppliers/update','perms[/suppliers/update]','获取供应商信息');
insert into sys_permissions values(30, '/suppliers/delete','perms[/suppliers/delete]','删除供应商信息');

insert into sys_roles_permissions values (1,1,1);
insert into sys_roles_permissions values (2,1,2);
insert into sys_roles_permissions values (3,1,3);
insert into sys_roles_permissions values (4,1,4);
insert into sys_roles_permissions values (5,1,5);
insert into sys_roles_permissions values (6,1,6);
insert into sys_roles_permissions values (7,1,7);
insert into sys_roles_permissions values (8,1,8);
insert into sys_roles_permissions values (9,1,9);
insert into sys_roles_permissions values (10,1,10);
insert into sys_roles_permissions values (11,1,11);
insert into sys_roles_permissions values (12,1,12);
insert into sys_roles_permissions values (13,1,13);
insert into sys_roles_permissions values (14,1,14);
insert into sys_roles_permissions values (15,1,15);
insert into sys_roles_permissions values (16,1,16);
insert into sys_roles_permissions values (17,1,17);
insert into sys_roles_permissions values (18,1,18);
insert into sys_roles_permissions values (19,1,19);
insert into sys_roles_permissions values (20,1,20);
insert into sys_roles_permissions values (21,1,21);
insert into sys_roles_permissions values (22,1,22);
insert into sys_roles_permissions values (23,1,23);
insert into sys_roles_permissions values (24,1,24);
insert into sys_roles_permissions values (25,1,25);
insert into sys_roles_permissions values (26,1,26);
insert into sys_roles_permissions values (27,1,27);
insert into sys_roles_permissions values (28,1,28);
insert into sys_roles_permissions values (29,1,29);
insert into sys_roles_permissions values (30,1,30);

insert into sys_roles_permissions values (31,2,1);
insert into sys_roles_permissions values (32,2,6);
insert into sys_roles_permissions values (33,2,11);
insert into sys_roles_permissions values (34,2,16);
insert into sys_roles_permissions values (35,2,21);
insert into sys_roles_permissions values (36,2,26);