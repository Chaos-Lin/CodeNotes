## MySQL
win + R
输入cmd
ctrl + shift + enter
就可以以管理员身份运行命令台了
### 2. 初始化MySQL

以管理员身份，运行命令行窗口:


```
mysqld --initialize-insecure
```

### 3. 注册MySQL服务

命令行(注意必须以管理员身份启动)中，输入如下的指令，回车执行: 

```
mysqld -install
```

### 4. 启动MySQL服务

在黑框里敲入`net start mysql`，回车。

```java
net start mysql  // 启动mysql服务
    
net stop mysql  // 停止mysql服务
```

### 5. 修改默认账户密码

在黑框里敲入`mysqladmin -u root password 1234`，这里的`1234`就是指默认管理员(即root账户)的密码，可以自行修改成你喜欢的。

```
mysqladmin -u root password 1234
```

### 登录MySQL

右键开始菜单，选择`命令提示符`，打开黑框。
在黑框中输入，`mysql -uroot -p1234`，回车，出现下图且左下角为`mysql>`，则登录成功。

```
mysql -uroot -p1234
```

退出mysql:

```
exit
quit
```

登陆参数:

```
mysql -u用户名 -p密码 -h要连接的mysql服务器的ip地址(默认127.0.0.1) -P端口号(默认3306)
```

### 卸载MySQL

如果你想卸载MySQL，也很简单。

点击开始菜单，输入cmd，选择 "命令提示符"，选择右侧的 "以管理员身份运行"。
1. 敲入`net stop mysql`，回车。

```
net stop mysql
```

2. 再敲入`mysqld -remove mysql`，回车。

```
mysqld -remove mysql
```

3. 最后删除MySQL目录及相关的环境变量。

**至此，MySQL卸载完成！**

## 单表
分号结尾 不区分大小写 
注释: #注释 --注释 /*注释*/
### DDL定义
#### 数据库
创建数据库
```create database [if not exits] 数据库名```

查询所有数据库
```show databases ```

查询当前数据库
```select database() ```

使用数据库
```use 数据库名 ```

删除数据库
```drop database [if exists] 数据库名```
``` ```

#### 表

##### 创建
```
create table user(
    id int primary key comment "id唯一",
    username varchar(20) not null unique comment "用户名",
    name varchar(10) not null,
    age int,
    gender char(1) default '男'
)comment "用户表"
```
约束
```
not null
unique
primary key
default
foreign key
```
##### 查询
查询表
``` show tables```

查询表结构
```desc 表名 ```

查询建表语句
```show create table 表名 ```

##### 修改
添加字段
```alter table 表名 add 字段名 类型(长度) [comment 注释] [约束]```

修改字段类型
```alter table 表名 modify 字段名 新数据类型```

修改字段名和字段类型
```alter table 表名 change 旧字段名 新字段名 类型(长度) [comment 注释] [约束]```

删除字段
```alter table 表名 drop column 字段名 ```

修改表名
```rename table 表名 to 新表名```

##### 删除

删除表
```drop table [if exists] 表名```

会有因为外键无法删除表的问题
方法1：先删除依赖，在删除主表
```sql
-- 1. 先删除引用games表的notes表
DROP TABLE IF EXISTS `notes`;

-- 2. 然后才能删除games表
DROP TABLE IF EXISTS `games`;
```


方法2：临时禁用外键检查（推荐）
```sql
-- 1. 禁用外键检查
SET FOREIGN_KEY_CHECKS = 0;

-- 2. 删除表
DROP TABLE IF EXISTS `games`;
DROP TABLE IF EXISTS `notes`;

-- 3. 重新启用外键检查
SET FOREIGN_KEY_CHECKS = 1;
```


方法3：使用级联删除（修改表结构时）
```sql
-- 1. 删除现有外键约束
ALTER TABLE `notes` DROP FOREIGN KEY `fk_notes_game`;

-- 2. 添加级联删除的外键约束
ALTER TABLE `notes` 
ADD CONSTRAINT `fk_notes_game` 
FOREIGN KEY (`game_id`) REFERENCES `games` (`game_id`) ON DELETE CASCADE;
```
### DML增删改
#### 添加 insert

```Insert into 表名 (字段1，字段2) values(值1，值2);```
```Insert into 表名 values(值1，值2);```

批量添加
```Insert into 表名 (字段1，字段2) values(值1，值2),(值1，值2);```
```Insert into 表名values(值1，值2),(值1，值2);```

#### 修改 update
```Update 表名 set 字段名1=值1, 字段名2=值2 [where 条件];```

```Update tb_emp set name="lxc", update_time = now() where id = 1; ```

#### 删除 delete

```Delete from 表名 [where 条件];```


### DQL查询

#### select 字段列表 from 表名列表

```Select 字段1, 字段2 from 表名;```
```Select * from 表名;```
	
设置别名
```	Select 字段1 [as 别名1] , 字段2 [as 别名2] from 表名;```

去除重复记录
```	Select distinct 字段列表 from 表名;```



#### where 条件列表

```Select 字段列表 from 表名 where 条件列表;```
```>大于;>=大于等于;<小于;<=小于等于;=等于;```
```<> 不等于```
```!=不等于;```
``` is null 是null;```
```
select * from emp where name="杨逍";
select * from emp where id <= 5;
select  * from emp where job is null;
select  * from emp where job is not null;
select  * from emp where password != 123456;
```

``` between 小数and 大数;```
包含其中一个数 ``` in();```
```like 占位符(_为单字符，%任意值，模糊匹配);```

```
select  * from emp where entrydate >= '2000-01-01' and entrydate <= '2010-01-01';
select  * from emp where entrydate between '2000-01-01' and  '2010-01-01';
select  * from emp where (entrydate between '2000-01-01' and  '2010-01-01') and gender = 2;
select  * from emp where job =2 or job =3 or job=4;
select  * from emp where job in(2,3,4);
select  * from emp where name like '__';
select  * from emp where name like '张%';
```


```and &&且; or || 或; not ! 非;```

聚合函数(字段名/*)——NULL不参与计算
Count统计数量;max最大值;min最小值;avg平均值;sum求和。

```select count(id) from emp;```
```select count(0) from emp;```
```select count(*) from emp;```推荐

```select min(entrydate) from emp;```
```select max(entrydate) from emp;```
```select avg(id) from emp;```
```select sum(id) from emp;```




#### group by 分组字段列表

```select gender,count(*) from emp group by gender;```
一般select 分组字段和聚合函数

#### having 分组后条件列表

```select job,count(*) from emp where entrydate <= '2015-01-01' group by job having count(job)>=2;```

#### order by 排序字段列表
```Order by 字段1 排序方式1，字段2 排序方式2;```
Asc:升序(默认)
Desc:降序

```select * from emp order by entrydate asc;```
```select * from emp order by entrydate asc, update_time desc;```


#### limit分页列表

```Limit 起始索引 [(页码-1)*每页记录数]，查询记录数(每页记录数);```

```select * from emp limit 0, 5;```
```select * from emp limit 5, 5;```



执行顺序
From> where> group by (having)> select> order by> limit
```select * from emp where name like '张%' and gender = 1 and entrydate between '2000-01-01' and '2015-12-31' order by update_time desc limit 10;```

### DCL控制
#### 管理用户
#### 查询用户
```Use mysql;```
```Select * from user;```
Mysql数据库里的user表可以查看;
#### 创建用户
```Create user‘用户名’@‘主机名’  identified by ‘密码’;```
```主机名:localhost(本地主机);%(任意主机)```
#### 修改用户密码
```Alter user ‘用户名’@‘主机名’ identified with mysql_native_password by ‘新密码’;```
#### 删除用户
```Drop user ‘用户名’@‘主机名’ ;```

命令行:```mysql -u 用户名 -p```

#### 权限控制
```
All, all privileges 所有权限; Select 查询数据;
Insert插入数据;update修改数据;
delete删除数据;alter修改表;
Drop删除数据库/表、视图;create创建数据库/表;
```
#### 查询权限
```Show grants for ‘用户名’@‘主机名’;```
#### 授予权限
```Grant 权限列表 on 数据库名,表名 to ‘用户名’@‘主机名’;```
#### 撤销权限
```Remove 权限列表 on 数据库名,表名 from ‘用户名’@‘主机名’;```

## 多表

### 多表设计
#### 外键

创建表时:
```constraint 外键名称 foreign key 外键字段 references 主表(主表列名);```
修改表时:
```alter table 本表名 add constraint 外键名称 foreign key (外键字段) references 主表(主表列名);```
删除外键:
```	Alter table 表名drop foreign key 外键名称;```

1vN 只需要在N的一方添加字段来关联1的一方的主键
1v1 单表拆分
NvN 中间表两个外键分别关联两方主键

### 多表查询

## 事物
一组操作的集合，要么同时成功，要么同时失效
开启事务：```start transaction;``` / ```begin;``` 
提交事务：```commit;```
回滚事务：```rollback;```，只要有一个失败了，就要用回滚

### 四大特性
原子性
一致性
隔离性
持久性

## 索引
用于快速查找，B+树（多路平衡搜索树），但是插入更新删除的效率变低了
创建索引
```sql
create [unique] index 索引名 on 表名(字段名...);
```

查看索引
```sql
show index from 表名
```

删除索引
```sql
drop index 索引名 on 表名
```

## 分库分表