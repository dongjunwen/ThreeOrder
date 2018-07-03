# ThreeOrder 三人行订单系统
## 一、功能模块说明
* doc 文档介绍目录
* order-api 暴露的API接口部分 为以后拆分为RPC调用做准备
* order-common 公共模块 常用工具类 通用工具包等
* order-jdbc 数据库连接公共模块
* order-service 核心业务处理模块
* order-rest 对web页面暴露的http rest接口
## 二、执行过程
* 首先执行 mvn clean install -Dmaven.test.skip=true
* 到order-rest的target目录 找到order-rest.jar包
* 执行 nohup java -jar order-rest.jar >> nohup-order.out & 即可
* 打开 http://127.0.0.1:9006//swagger-ui.html 就能看到调试页面

