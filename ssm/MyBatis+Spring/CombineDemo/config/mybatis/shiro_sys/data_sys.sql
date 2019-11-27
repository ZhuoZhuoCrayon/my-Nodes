use company_sys;
insert into sys_users values(1,'admin','3bb0b0e9f079312bb881bce0d0346e52','315a6b25cc2d8a5af98f5768e2945e7a');
insert into sys_users values(2,'user','fa2a26aa8be21a5ec4fae415be6ac657','8fb10b18001d1e492130bbdc2285a032');

insert into sys_roles values(1,'admin', '管理员');
insert into sys_roles values(2,'user','普通用户');


insert into sys_users_roles values(1,1, 1);
insert into sys_users_roles values(2,2, 2);

insert into sys_permissions values(1, '/customers/detail.do','perms[/customers/detail.do]','获取客户信息');
insert into sys_permissions values(2, '/customers/findById.do','perms[/customers/findById.do]','按id获取用户信息');
insert into sys_permissions values(3, '/customers/insert.do','perms[/customers/insert.do]','插入客户信息');
insert into sys_permissions values(4, '/customers/update.do','perms[/customers/update.do]','获取客户信息');
insert into sys_permissions values(5, '/customers/delete.do','perms[/customers/delete.do]','删除客户信息');

insert into sys_permissions values(6, '/employees/detail.do','perms[/employees/detail.do]','获取员工信息');
insert into sys_permissions values(7, '/employees/findById.do','perms[/employees/findById.do]','按id获取员工信息');
insert into sys_permissions values(8, '/employees/insert.do','perms[/employees/insert.do]','插入员工信息');
insert into sys_permissions values(9, '/employees/update.do','perms[/employees/update.do]','获取员工信息');
insert into sys_permissions values(10, '/employees/delete.do','perms[/employees/delete.do]','删除员工信息');

insert into sys_permissions values(11, '/logs/detail.do','perms[/logs/detail.do]','获取日志信息');
insert into sys_permissions values(12, '/logs/findById.do','perms[/logs/findById.do]','按id获取日志信息');
insert into sys_permissions values(13, '/logs/insert.do','perms[/logs/insert.do]','插入日志信息');
insert into sys_permissions values(14, '/logs/update.do','perms[/logs/update.do]','获取日志信息');
insert into sys_permissions values(15, '/logs/delete.do','perms[/logs/delete.do]','删除日志信息');

insert into sys_permissions values(16, '/products/detail.do','perms[/products/detail.do]','获取商品信息');
insert into sys_permissions values(17, '/products/findById.do','perms[/products/findById.do]','按id获取商品信息');
insert into sys_permissions values(18, '/products/insert.do','perms[/products/insert.do]','插入商品信息');
insert into sys_permissions values(19, '/products/update.do','perms[/products/update.do]','获取商品信息');
insert into sys_permissions values(20, '/products/delete.do','perms[/products/delete.do]','删除商品信息');

insert into sys_permissions values(21, '/purchases/detail.do','perms[/purchases/detail.do]','获取订单信息');
insert into sys_permissions values(22, '/purchases/findById.do','perms[/purchases/findById.do]','按id获取订单信息');
insert into sys_permissions values(23, '/purchases/insert.do','perms[/purchases/insert.do]','插入订单信息');
insert into sys_permissions values(24, '/purchases/update.do','perms[/purchases/update.do]','获取订单信息');
insert into sys_permissions values(25, '/purchases/delete.do','perms[/purchases/delete.do]','删除订单信息');

insert into sys_permissions values(26, '/suppliers/detail.do','perms[/suppliers/detail.do]','获取供应商信息');
insert into sys_permissions values(27, '/suppliers/findById.do','perms[/suppliers/findById.do]','按id获取供应商信息');
insert into sys_permissions values(28, '/suppliers/insert.do','perms[/suppliers/insert.do]','插入供应商信息');
insert into sys_permissions values(29, '/suppliers/update.do','perms[/suppliers/update.do]','获取供应商信息');
insert into sys_permissions values(30, '/suppliers/delete.do','perms[/suppliers/delete.do]','删除供应商信息');

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