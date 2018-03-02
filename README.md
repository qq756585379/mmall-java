# mmall

![Image text](./images/4.jpeg)



```
保证在已经安装jdk，maven，tomcat，mysql等的环境并配置好
打开datasource.properties请修改db.url、db.username、db.password为自己的mysql数据库连接需要的url、username、password
打开mmall.properties，修改成自己的ftp服务器地址，账号和密码，支付宝回调的地址可以通过外网穿透进行配置。
如果用nginx配置的话，请修改本机host支持域名。然后修改ftp文件服务器的访问前缀。

```


## 插件
1.mybatis-generator-maven-plugin能自动生成mappers.xml

```
mybatis-generator需要的mysql包已经放在了tools包下，可以copy出来，放到某个位置，
并修改 datasource.properties里的db.driverLocation 为你放的路径。
使用mybatis-generator的时候就ok啦~
```


2.在idea的偏好设置选plugins,搜索mybatis plugin,安装

![Image text](./images/1.png)

3.chome插件需要翻墙（不装了）

restlet可以调试get/post

http://blog.csdn.net/zlp1992/article/details/76706017



## 偏好设置
![Image text](./images/2.png)
勾选上图的 Build project automatically


![Image text](./images/3.jpeg)
需要修改上图选项为warning

## 发布
1.阿里云万网购买域名

2.阿里云源配置官网 http://mirrors.aliyun.com/

3.使用的centos (http://mirrors.aliyun.com/help/centos)


CentOS

```
1.备份
sudo mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup

2.下载新的CentOS-Base.repo 到/etc/yum.repos.d/
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
或者
curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo

3.之后运行yum makecache生成缓存


```

