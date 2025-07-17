SET NAMES utf8mb4;

# SHOW VARIABLES LIKE 'character_set_%';

create database if not exists luban_demo_db;
use luban_demo_db;

drop table if exists `user`;
create table `user`(
                     id int primary key auto_increment comment '用户id',
                     username varchar(20) not null comment '用户名',
                     password varchar(20) not null comment '用户密码',
                     role varchar(20) not null comment '用户角色'
) engine=InnoDB default charset=utf8mb4 comment='个人信息表';
insert into `user`(username,password,role) values ('user_1','user_1','USER'),('editor_1','editor_1','EDITOR'),('adm_1','adm_1','PRODUCT_ADMIN');

drop table if exists `product`;
create table `product`(
                    id int primary key auto_increment comment '产品id',
                    name varchar(20) not null comment '产品名称',
                    intro text comment '产品简介',
                    create_time datetime default current_timestamp comment '创建时间',
                    update_time datetime default current_timestamp comment '更新时间'
) engine=InnoDB default charset=utf8mb4 comment='产品信息表';
insert into `product`(name,intro) values ('产品1','产品1简介'),('产品2','产品2简介'),('产品3','产品3简介')



# drop table if exists `user`;
# create table if not exists `user`(
#     id int primary key auto_increment comment '用户id',
#     username varchar(20) not null comment '用户名',
#     password varchar(20) not null comment '用户密码',
#     role_id int not null comment '用户角色id'
# ) engine=InnoDB default charset=utf8mb4 comment='个人信息表';
# insert into `user`(username,password,role_id) values ('user','user',1),('editor','editor',2),('admin','admin',3);
#
# drop table if exists `role`;
# create table if not exists `role`(
#     id int primary key auto_increment comment '角色id',
#     role_name varchar(20) not null comment '角色名称',
#     create_time datetime default current_timestamp comment '创建时间'
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';
# insert into `role`(role_name) values ('USER'),('EDITOR'),('PRODUCT_ADMIN' );
#
# drop table if exists `permission`;
# create table if not exists `permission`(
#     id int primary key auto_increment comment '权限id',
#     permission_name varchar(20) not null comment '权限名称',
#     create_time datetime default current_timestamp comment '创建时间'
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限信息表';
# insert into `permission`(permission_name) values ('SELECT'),('ADD'),('DELETE'),('UPDATE');
#
# drop table if exists `role_permission`;
# create table if not exists `role_permission`(
#     id int primary key auto_increment comment '角色权限id',
#     role_id int not null comment '角色id',
#     permission_id int not null comment '权限id',
#     create_time datetime default current_timestamp comment '创建时间'
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';
# insert into `role_permission`(role_id,permission_id) values (1,1),(2,1),(2,2),(2,3),(2,4),(3,1),(3,2),(3,3),(3,4);