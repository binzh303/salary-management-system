# 薪资管理系统

## 一、系统介绍

> 本系统为个人原创项目，系2022年开发的毕业设计，如果需要自学或者作为毕设都可以有偿使用，欢迎联系微信 **byte-zb**，添加好友时备注 **薪资管理系统** ,白嫖勿扰！另本人代做Java类和微信小程序的毕设，需要的可以添加我的微信与我联系！

薪资管理系统是一个基于企业与员工关系的信息分享、传播、获取各种信息发布相结合的平台。企业能够通过该平台实现对员工薪酬进行合理的管理。同时，员工通过改平台进行考勤、请假。在工资结算后查看自己的工资情况。
为了数据的安全性考虑，本系统采用shiro安全框架进行数据权限的划分，及数据安全的保护。
本系统分为三个角色，分别为普通用户、财务人员及领导。三种角色分别拥有以下权限
- 领导
    - 部门管理
    - 员工管理
    - 角色管理
    - 岗位管理
    - 通知管理
    - 考勤查看
    - 请假审批
    - 工资条查看
- 财务人员
    - 薪资配置
    - 岗位薪资配置
    - 薪资核算
    - 考勤管理
    - 请假管理
    - 通知查看
    - 工资条查看
- 普通员工
    - 考勤管理
    - 请假管理
    - 通知查看
    - 工资条查看

除了上述功能外，三种角色都拥有更新个人信息及修改密码等系统通用功能。

## 二、系统运行截图

**登录**

![](src/main/resources/static/system/img.png)

**系统首页**

![](src/main/resources/static/system/img_1.png)

**部门管理**

![](src/main/resources/static/system/img_2.png)

**员工管理**

![](src/main/resources/static/system/img_3.png)

**角色管理**

![](src/main/resources/static/system/img_4.png)

**岗位管理**

![](src/main/resources/static/system/img_5.png)

**通知公告**

![](src/main/resources/static/system/img_6.png)

![](src/main/resources/static/system/img_7.png)

**薪资配置**

![](src/main/resources/static/system/img_8.png)

**岗位薪资配置**

![](src/main/resources/static/system/img_9.png)

**薪资核算**
财务人员可每月进行一次薪资核算，薪资核算会将所有员工的薪资进行核算，系统会通过员工对应的岗位，当月考勤情况、请假情况一级个税及五险一金的扣除情况进行核算，得到工资发方的明细。

![](src/main/resources/static/system/img_10.png)

![](src/main/resources/static/system/img_12.png)

**考勤管理**

![](src/main/resources/static/system/img_13.png)

**请假管理及审批**

![](src/main/resources/static/system/img_14.png)

![](src/main/resources/static/system/img_16.png)

**个人工资条**

![](src/main/resources/static/system/img_15.png)

## 三、访问地址及用户密码

系统正常启动后，浏览器输入 `http://localhost:9000` 即可访问。
系统用户账号密码：
- 领导： 账号：zhou  密码：123456
- 财务： 账号：wang  密码：123456
- 普通员工： 账号：liu  密码：123456

## 四、软件架构

### 基础环境：

    JDK:1.8
    MySQL:5.7
    Maven3.0

### 使用框架：

    核心框架：Spring Boot
    视图框架：Spring MVC
    ORM框架：MyBatis
    数据库连接池：Druid 1.1
    安全框架：Apache Shiro 1.4
    日志：SLF4J 1.7、Log4j
    前端框架：jQury,bootStrap,ztree

## 五、特别说明

本系统为个人原创项目，系2022年开发的毕业设计，如果需要自学或者作为毕设都可以有偿使用，欢迎联系微信 **byte-zb**，添加好友时备注 **薪资管理系统** ,白嫖勿扰！另本人代做Java类和微信小程序的毕设，需要的可以添加我的微信与我联系！