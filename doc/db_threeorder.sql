/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/6 16:52:10                            */
/*==============================================================*/


drop table if exists tb_cat_param;

drop table if exists tb_item;

drop table if exists tb_item_cat;

drop table if exists tb_item_desc;

drop table if exists tb_item_param;

drop table if exists tb_order;

drop table if exists tb_order_item;

drop table if exists tb_order_shipping;

drop table if exists tb_user;

/*==============================================================*/
/* Table: tb_cat_param                                          */
/*==============================================================*/
create table tb_cat_param
(
   id                   int not null auto_increment comment '主键id',
   cat_no               varchar(32) comment '类目编号',
   cat_param            text comment '类目参数',
   create_time          datetime comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table tb_cat_param comment '商品类目参数';

/*==============================================================*/
/* Table: tb_item                                               */
/*==============================================================*/
create table tb_item
(
   id                   int not null auto_increment comment '主键id',
   item_no              varchar(32) comment '商品编号',
   item_title           varchar(128) comment '商品标题',
   sell_point           varchar(500) comment '商品卖点',
   price                decimal(16,2) comment '单价',
   num                  decimal(16,2) comment '数量',
   bar_code             varchar(128) comment '条形码',
   pic_url              varchar(128) comment '图片',
   cat_no               varchar(128) comment '所属类目',
   status               char(1) default 'Y' comment '状态 Y:有效 N:无效',
   create_time          datetime comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table tb_item comment '商品列表';

/*==============================================================*/
/* Table: tb_item_cat                                           */
/*==============================================================*/
create table tb_item_cat
(
   id                   int not null auto_increment comment '主键id',
   cat_no               varchar(32) comment '类目编号',
   pcat_no              varchar(32) comment '上级类目编号',
   item_level           int comment '类目级别',
   item_sort            int comment '排序号',
   status               char(1) default 'Y' comment '状态 Y:有效 N:无效',
   create_time          datetime comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table tb_item_cat comment '商品类目';

/*==============================================================*/
/* Table: tb_item_desc                                          */
/*==============================================================*/
create table tb_item_desc
(
   id                   int not null auto_increment comment '主键id',
   item_no              varchar(32) comment '商品编号',
   item_desc            text comment '商品描述',
   create_time          datetime comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table tb_item_desc comment '商品描述';

/*==============================================================*/
/* Table: tb_item_param                                         */
/*==============================================================*/
create table tb_item_param
(
   id                   int not null auto_increment comment '主键id',
   item_no              varchar(32) comment '商品编号',
   item_param           text comment '商品参数',
   create_time          datetime comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table tb_item_param comment '商品参数';

/*==============================================================*/
/* Table: tb_order                                              */
/*==============================================================*/
create table tb_order
(
   id                   int not null auto_increment comment '主键id',
   order_no             varchar(32) comment '订单号',
   order_time           datetime comment '订单时间',
   pay_time             datetime comment '支付时间',
   pay_type             int default 0 comment '付款方式 0:货到付款 1:在线支付 2:公司转账',
   order_status         int comment '订单状态',
   order_amt            decimal(16,2) comment '订单金额',
   coup_amt             decimal(16,2) comment '优惠金额',
   trans_amt            decimal(16,2) comment '运费',
   act_order_amt        decimal(16,2) comment '实际订单金额=订单金额+运费-优惠金额',
   order_rate           decimal(16,6) comment '税率',
   act_tax_amt          decimal(16,2) comment '实际含税金额=实际订单金额*税率',
   pay_amt              decimal(16,2) comment '已付金额',
   buyler_id            varchar(32) comment '用户编号',
   shop_no              varchar(32) comment '店铺编号',
   seller_id            varchar(32) comment '卖家编号',
   order_desc           varchar(256) comment '订单描述',
   create_time          datetime comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table tb_order comment '订单表';

/*==============================================================*/
/* Table: tb_order_item                                         */
/*==============================================================*/
create table tb_order_item
(
   id                   int not null auto_increment comment '主键id',
   order_no             varchar(32) comment '订单号',
   item_no              varchar(32) comment '商品编号',
   item_name            varchar(128) comment '商品名称',
   item_pic_url         varchar(256) comment '商品图片地址',
   price                decimal(16,2) comment '单价',
   num                  decimal(16,2) comment '数量',
   amt                  decimal(16,2) comment '金额',
   create_time          datetime comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table tb_order_item comment '订单详情表';

/*==============================================================*/
/* Table: tb_order_shipping                                     */
/*==============================================================*/
create table tb_order_shipping
(
   id                   int not null auto_increment comment '主键id',
   order_no             varchar(32) comment '订单号',
   recv_name            varchar(64) comment '收货人姓名',
   recv_phone           varchar(32) comment '固定电话',
   recv_mobile          varchar(32) comment '移动电话',
   recv_province        varchar(12) comment '省份',
   recv_city            varchar(16) comment '城市',
   recv_district        varchar(32) comment '区、县',
   recv_address         varchar(256) comment '收货人地址',
   recv_zip             varchar(6) comment '邮政编码',
   status               char(1) comment '是否生效 Y:有效N:无效',
   create_time          datetime comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table tb_order_shipping comment '订单物流信息表';

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   id                   int not null auto_increment comment '自增主键ID',
   user_no              VARCHAR(32) comment '用户编号',
   user_name            VARCHAR(64) comment '用户名称',
   nick_name            VARCHAR(64) comment '昵称',
   phone_num            VARCHAR(64) comment '手机号',
   email_addr           VARCHAR(64) comment '邮箱',
   login_pass           VARCHAR(256) comment '登录密码',
   status               CHAR(1) default 'Y' comment '状态 Y:有效 N:无效',
   memo                 VARCHAR(256) comment '备注',
   create_time          datetime comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table tb_user comment '用户表';

