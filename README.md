# 登录网址
https://ddlnt.xyz

# linus命令

cd /home

mkdir -p luban_springcloud_demo/data/nacos_data

mkdir -p luban_springcloud_demo/data/mysql_data

cd luban_springcloud_demo

## 上传zip目录中的压缩包init.zip（vue前端dist、配置文件nacos_config.zip）到当前目录 （luban_springcloud_demo）
unzip init.zip

rm init.zip

docker run -d --name luban-nacos-demo -e MODE=standalone -p 8848:8848 -p 9848:9848 -p 9849:9849  nacos/nacos-server:v2.3.2

## 进入nacos控制台，http://IP:8848/nacos
## 导入配置文件：nacos_config.zip
## 复制容器内文件到宿主机
docker cp luban-nacos-demo:/home/nacos/conf ./data/nacos_data

docker cp luban-nacos-demo:/home/nacos/data ./data/nacos_data

docker cp luban-nacos-demo:/home/nacos/logs ./data/nacos_data

docker stop luban-nacos-demo

docker rm luban-nacos-demo

## 部署环境（确认MySQL初始化文件/home/luban_springcloud_demo/init/mysql_init/init.sql存在）
docker-compose -f docker-compose.env.yml pull

docker-compose -f docker-compose.env.yml up -d

## Ldap初始化文件（/home/luban_springcloud_demo/init/openldap_init/init.ldif）放入容器临时目录，执行文件导入数据
docker cp ./init/openldap_init/init.ldif luban-openldap-demo:/tmp/init.ldif

docker exec luban-openldap-demo ldapadd -x -D "cn=admin,dc=ddlnt,dc=xyz" -w ldapPassword -f /tmp/init.ldif

## 拉取镜像
docker pull ddlnt.xyz:5000/server-gateway:1.1

docker pull ddlnt.xyz:5000/service-user:1.1

docker pull ddlnt.xyz:5000/service-product:1.1
## 部署服务
docker-compose -f docker-compose.service.yml up -d


# 测试接口

## 服务器接口测试及结果：
### Ldap登录（ldap_user_1）
curl -X POST -H "Content-Type: application/json" -d '{"username":"ldap_user_1","password":"ldap_user_1"}' http://ddlnt.xyz:7573/service-user/ldap_user/login

{"code":200,"message":"成功","data":"11e9f826-2f6a-416f-85e3-b6042ae77cbb"}

### ldap_user_1添加产品
curl -X POST -H "Content-Type: application/json" -H "token: 11e9f826-2f6a-416f-85e3-b6042ae77cbb" -d '{"name":"产品","intro":"产品介绍"}' http://ddlnt.xyz:7573/service-user/product/add

{"code":50,"message":"用户权限不足","data":null}

### MySQL登录（adm_1）
curl -X POST -H "Content-Type: application/json" -d '{"username":"adm_1","password":"adm_1"}' http://ddlnt.xyz:7573/service-user/user/login

{"code":200,"message":"成功","data":"a77e026c-4c27-4949-b270-f34e87ca1381"}

### ldap_editor_1查询产品
curl -X POST -H "Content-Type: application/json" -H "token: a77e026c-4c27-4949-b270-f34e87ca1381" -d '{"pageNum":"1","pageSize":"10"}' http://ddlnt.xyz:7573/service-user/product/query

{"code":200,"message":"成功","data":[{"id":1,"name":"产品1","intro":"产品1简介","createTime":"2025-07-17T16:45:33","updateTime":"2025-07-17T16:45:33"},{"id":2,"name":"产品2","intro":"产品2简介","createTime":"2025-07-17T16:45:33","updateTime":"2025-07-17T16:45:33"},{"id":3,"name":"产品3","intro":"产品3简介","createTime":"2025-07-17T16:45:33","updateTime":"2025-07-17T16:45:33"},{"id":4,"name":"产品测试","intro":"我的产品","createTime":"2025-07-17T17:41:07","updateTime":"2025-07-17T09:41:35"}]}

### ldap_editor_1添加产品+查询添加结果
curl -X POST -H "Content-Type: application/json" -H "token: a77e026c-4c27-4949-b270-f34e87ca1381" -d '{"name":"产品5","intro":"产品5"}' http://ddlnt.xyz:7573/service-user/product/add

{"code":200,"message":"成功","data":true}

curl -X POST -H "Content-Type: application/json" -H "token: a77e026c-4c27-4949-b270-f34e87ca1381" -d '{"pageNum":"5","pageSize":"1"}' http://ddlnt.xyz:7573/service-user/product/query

{"code":200,"message":"成功","data":[{"id":5,"name":"产品5","intro":"产品5","createTime":"2025-07-17T21:25:34","updateTime":"2025-07-17T21:25:34"}]}

### ldap_editor_1修改产品+查询修改结果
curl -X PUT -H "Content-Type: application/json" -H "token: a77e026c-4c27-4949-b270-f34e87ca1381" -d '{"id":"5","name":"产品5","intro":"产品的介绍"}' http://ddlnt.xyz:7573/service-user/product/update

{"code":200,"message":"成功","data":true}

curl -X POST -H "Content-Type: application/json" -H "token: a77e026c-4c27-4949-b270-f34e87ca1381" -d '{"pageNum":"5","pageSize":"1"}' http://ddlnt.xyz:7573/service-user/product/query

{"code":200,"message":"成功","data":[{"id":5,"name":"产品5","intro":"产品的介绍","createTime":"2025-07-17T21:25:34","updateTime":"2025-07-17T13:26:59"}]}

### ldap_editor_1删除产品+查询删除结果
curl -X DELETE -H "Content-Type: application/json" -H "token: a77e026c-4c27-4949-b270-f34e87ca1381" http://ddlnt.xyz:7573/service-user/product/delete/5

{"code":200,"message":"成功","data":true}

curl -X POST -H "Content-Type: application/json" -H "token: a77e026c-4c27-4949-b270-f34e87ca1381" -d '{"pageNum":"1","pageSize":"10"}' http://ddlnt.xyz:7573/service-user/product/query

{"code":200,"message":"成功","data":[{"id":1,"name":"产品1","intro":"产品1简介","createTime":"2025-07-17T16:45:33","updateTime":"2025-07-17T16:45:33"},{"id":2,"name":"产品2","intro":"产品2简介","createTime":"2025-07-17T16:45:33","updateTime":"2025-07-17T16:45:33"},{"id":3,"name":"产品3","intro":"产品3简介","createTime":"2025-07-17T16:45:33","updateTime":"2025-07-17T16:45:33"},{"id":4,"name":"产品测试","intro":"我的产品","createTime":"2025-07-17T17:41:07","updateTime":"2025-07-17T09:41:35"}]}



## Windows本地接口测试及结果：
### MySQL登录（user_1）
curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"user_1\",\"password\":\"user_1\"}" http://localhost:7573/service-user/user/login

{"code":200,"message":"成功","data":"0aeef068-98d0-4785-95a7-801e79227632"}

### user_1添加产品
curl -X POST -H "Content-Type: application/json" -H "token: 0aeef068-98d0-4785-95a7-801e79227632" -d "{\"name\":\"产品\",\"intro\":\"产品介绍\"}" http://localhost:7573/service-user/product/add

{"code":50,"message":"用户权限不足","data":null}

### Ldap登录（ldap_editor_1）
curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"ldap_editor_1\",\"password\":\"ldap_editor_1\"}" http://localhost:7573/service-user/ldap_user/login

{"code":200,"message":"成功","data":"bf3fa7cf-73ea-48f6-97f7-419c3f11bc8d"}

### ldap_editor_1查询产品
curl -X POST -H "Content-Type: application/json" -H "token: bf3fa7cf-73ea-48f6-97f7-419c3f11bc8d" -d "{\"pageNum\":\"1\",\"pageSize\":\"10\"}" http://localhost:7573/service-user/product/query

{"code":200,"message":"成功","data":[{"id":1,"name":"产品1","intro":"产品1简介","createTime":"2025-07-15T18:25:39","updateTime":"2025-07-15T18:25:39"},{"id":2,"name":"产品2","intro":"产品2简介","createTime":"2025-07-15T18:25:39","updateTime":"2025-07-15T18:25:39"},{"id":3,"name":"产品3","intro":"产品3简介","createTime":"2025-07-15T18:25:39","updateTime":"2025-07-15T18:25:39"}]}

### ldap_editor_1添加产品+查询添加结果
curl -X POST -H "Content-Type: application/json" -H "token: bf3fa7cf-73ea-48f6-97f7-419c3f11bc8d" -d "{\"name\":\"产品4\",\"intro\":\"产品4\"}" http://localhost:7573/service-user/product/add

{"code":200,"message":"成功","data":true}

curl -X POST -H "Content-Type: application/json" -H "token: bf3fa7cf-73ea-48f6-97f7-419c3f11bc8d" -d "{\"pageNum\":\"4\",\"pageSize\":\"1\"}" http://localhost:7573/service-user/product/query

{"code":200,"message":"成功","data":[{"id":12,"name":"产品4","intro":"产品4","createTime":"2025-07-17T20:51:58","updateTime":"2025-07-17T20:51:58"}]}

### ldap_editor_1修改产品+查询修改结果
curl -X PUT -H "Content-Type: application/json" -H "token: bf3fa7cf-73ea-48f6-97f7-419c3f11bc8d" -d "{\"id\":\"12\",\"name\":\"产品4\",\"intro\":\"产品的介绍\"}" http://localhost:7573/service-user/product/update

{"code":200,"message":"成功","data":true}

curl -X POST -H "Content-Type: application/json" -H "token: bf3fa7cf-73ea-48f6-97f7-419c3f11bc8d" -d "{\"pageNum\":\"4\",\"pageSize\":\"1\"}" http://localhost:7573/service-user/product/query

{"code":200,"message":"成功","data":[{"id":12,"name":"产品4","intro":"产品的介绍","createTime":"2025-07-17T20:51:58","updateTime":"2025-07-17T12:58:51"}]}

### ldap_editor_1删除产品+查询删除结果
curl -X DELETE -H "Content-Type: application/json" -H "token: bf3fa7cf-73ea-48f6-97f7-419c3f11bc8d" http://localhost:7573/service-user/product/delete/12

{"code":200,"message":"成功","data":true}

curl -X POST -H "Content-Type: application/json" -H "token: bf3fa7cf-73ea-48f6-97f7-419c3f11bc8d" -d "{\"pageNum\":\"1\",\"pageSize\":\"10\"}" http://localhost:7573/service-user/product/query

{"code":200,"message":"成功","data":[{"id":1,"name":"产品1","intro":"产品1简介","createTime":"2025-07-15T18:25:39","updateTime":"2025-07-15T18:25:39"},{"id":2,"name":"产品2","intro":"产品2简介","createTime":"2025-07-15T18:25:39","updateTime":"2025-07-15T18:25:39"},{"id":3,"name":"产品3","intro":"产品3简介","createTime":"2025-07-15T18:25:39","updateTime":"2025-07-15T18:25:39"}]}

