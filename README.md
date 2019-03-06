
# nettest

## 网络测试中心

- 支持的协议有：ws(websocket),tcp,udt,http
- 支持RESTful的测试，适用的协议有：ws,tcp,udt,http
- 测试数据和报告存入平台的测试库
- 适合大企业建立测试中心
- 每个人管理自己的测试数据
- 所有服务方法全中心可见

## 使用说明书

![测试中心主页](https://github.com/carocean/cj.studio.nettest/blob/master/documents/img/home.jpg)

![测试中心压力测试](https://github.com/carocean/cj.studio.nettest/blob/master/documents/img/runner.jpg)

### 部署：
#### 运行中台网关
- 进入目录：cj.studio.nettest/cj.studio.nettest.be/cmdtools/gateway
- sh gateway.sh
- 使用man查看命令，也可手动更改配置，一看便知，在gateway/conf下

#### 运行作业服务器网关
- 进入目录 cj.studio.nettest/cj.studio.nettest.jobserver/cmdtools/gateway
- sh gateway.sh
- 使用man查看命令，也可手动更改配置，一看便知，在gateway/conf下

#### 运行前端网关
- 进入目录 cj.studio.nettest/cj.studio.nettest.fe/cmdtools/gateway
- sh gateway.sh
- 使用man查看命令，也可手动更改配置，一看便知，在gateway/conf下
- 修改连接中台网关和作业服务器的ip地址及端口

#### mongodb配置
- 安装mongodb
- 在github上签出cj.studio.netdisk项目，进入cj.studio.netdisk/cmdtools/disk/
- sh disk.sh -h ip:port   其中的-h指向的是mongodb服务器地址
- 进入命令窗后man一下，根据帮助创建netos网盘，在进入到netos网盘下创建test存储空间。（如果想改名需修改网关程序目录下的assemblies下的jar包中的cj/assembly.json等的连接库名）

#### 现在可以跑下程序看下
