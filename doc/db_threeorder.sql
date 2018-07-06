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
   id                   int not null auto_increment comment '����id',
   cat_no               varchar(32) comment '��Ŀ���',
   cat_param            text comment '��Ŀ����',
   create_time          datetime comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table tb_cat_param comment '��Ʒ��Ŀ����';

/*==============================================================*/
/* Table: tb_item                                               */
/*==============================================================*/
create table tb_item
(
   id                   int not null auto_increment comment '����id',
   item_no              varchar(32) comment '��Ʒ���',
   item_title           varchar(128) comment '��Ʒ����',
   sell_point           varchar(500) comment '��Ʒ����',
   price                decimal(16,2) comment '����',
   num                  decimal(16,2) comment '����',
   bar_code             varchar(128) comment '������',
   pic_url              varchar(128) comment 'ͼƬ',
   cat_no               varchar(128) comment '������Ŀ',
   status               char(1) default 'Y' comment '״̬ Y:��Ч N:��Ч',
   create_time          datetime comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table tb_item comment '��Ʒ�б�';

/*==============================================================*/
/* Table: tb_item_cat                                           */
/*==============================================================*/
create table tb_item_cat
(
   id                   int not null auto_increment comment '����id',
   cat_no               varchar(32) comment '��Ŀ���',
   pcat_no              varchar(32) comment '�ϼ���Ŀ���',
   item_level           int comment '��Ŀ����',
   item_sort            int comment '�����',
   status               char(1) default 'Y' comment '״̬ Y:��Ч N:��Ч',
   create_time          datetime comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table tb_item_cat comment '��Ʒ��Ŀ';

/*==============================================================*/
/* Table: tb_item_desc                                          */
/*==============================================================*/
create table tb_item_desc
(
   id                   int not null auto_increment comment '����id',
   item_no              varchar(32) comment '��Ʒ���',
   item_desc            text comment '��Ʒ����',
   create_time          datetime comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table tb_item_desc comment '��Ʒ����';

/*==============================================================*/
/* Table: tb_item_param                                         */
/*==============================================================*/
create table tb_item_param
(
   id                   int not null auto_increment comment '����id',
   item_no              varchar(32) comment '��Ʒ���',
   item_param           text comment '��Ʒ����',
   create_time          datetime comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table tb_item_param comment '��Ʒ����';

/*==============================================================*/
/* Table: tb_order                                              */
/*==============================================================*/
create table tb_order
(
   id                   int not null auto_increment comment '����id',
   order_no             varchar(32) comment '������',
   order_time           datetime comment '����ʱ��',
   pay_time             datetime comment '֧��ʱ��',
   pay_type             int default 0 comment '���ʽ 0:�������� 1:����֧�� 2:��˾ת��',
   order_status         int comment '����״̬',
   order_amt            decimal(16,2) comment '�������',
   coup_amt             decimal(16,2) comment '�Żݽ��',
   trans_amt            decimal(16,2) comment '�˷�',
   act_order_amt        decimal(16,2) comment 'ʵ�ʶ������=�������+�˷�-�Żݽ��',
   order_rate           decimal(16,6) comment '˰��',
   act_tax_amt          decimal(16,2) comment 'ʵ�ʺ�˰���=ʵ�ʶ������*˰��',
   pay_amt              decimal(16,2) comment '�Ѹ����',
   buyler_id            varchar(32) comment '�û����',
   shop_no              varchar(32) comment '���̱��',
   seller_id            varchar(32) comment '���ұ��',
   order_desc           varchar(256) comment '��������',
   create_time          datetime comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table tb_order comment '������';

/*==============================================================*/
/* Table: tb_order_item                                         */
/*==============================================================*/
create table tb_order_item
(
   id                   int not null auto_increment comment '����id',
   order_no             varchar(32) comment '������',
   item_no              varchar(32) comment '��Ʒ���',
   item_name            varchar(128) comment '��Ʒ����',
   item_pic_url         varchar(256) comment '��ƷͼƬ��ַ',
   price                decimal(16,2) comment '����',
   num                  decimal(16,2) comment '����',
   amt                  decimal(16,2) comment '���',
   create_time          datetime comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table tb_order_item comment '���������';

/*==============================================================*/
/* Table: tb_order_shipping                                     */
/*==============================================================*/
create table tb_order_shipping
(
   id                   int not null auto_increment comment '����id',
   order_no             varchar(32) comment '������',
   recv_name            varchar(64) comment '�ջ�������',
   recv_phone           varchar(32) comment '�̶��绰',
   recv_mobile          varchar(32) comment '�ƶ��绰',
   recv_province        varchar(12) comment 'ʡ��',
   recv_city            varchar(16) comment '����',
   recv_district        varchar(32) comment '������',
   recv_address         varchar(256) comment '�ջ��˵�ַ',
   recv_zip             varchar(6) comment '��������',
   status               char(1) comment '�Ƿ���Ч Y:��ЧN:��Ч',
   create_time          datetime comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table tb_order_shipping comment '����������Ϣ��';

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   id                   int not null auto_increment comment '��������ID',
   user_no              VARCHAR(32) comment '�û����',
   user_name            VARCHAR(64) comment '�û�����',
   nick_name            VARCHAR(64) comment '�ǳ�',
   phone_num            VARCHAR(64) comment '�ֻ���',
   email_addr           VARCHAR(64) comment '����',
   login_pass           VARCHAR(256) comment '��¼����',
   status               CHAR(1) default 'Y' comment '״̬ Y:��Ч N:��Ч',
   memo                 VARCHAR(256) comment '��ע',
   create_time          datetime comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table tb_user comment '�û���';

