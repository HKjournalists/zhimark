/**商品计量单位代码*/
CREATE TABLE ims_eso_sale_unit (
  id   INT(32)     NOT NULL AUTO_INCREMENT,
  code VARCHAR(32) NOT NULL,
  name VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**国别代码*/
CREATE TABLE ims_eso_sale_country_code (
  id   INT(32)     NOT NULL AUTO_INCREMENT,
  code VARCHAR(32) NOT NULL,
  name VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**行邮税号*/
CREATE TABLE ims_eso_sale_tax_code (
  id      INT(32)     NOT NULL AUTO_INCREMENT,
  taxCode VARCHAR(32) NOT NULL,
  taxRate VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**货币代码*/
CREATE TABLE ims_eso_sale_currency (
  id   INT(32)     NOT NULL AUTO_INCREMENT,
  code VARCHAR(32) NOT NULL,
  name VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**业务编号历史*/
CREATE TABLE ims_eso_sale_business_no_history (
  id         INT(32)      NOT NULL AUTO_INCREMENT,
  businessNo VARCHAR(256) NOT NULL,
  createTime VARCHAR(64),
  updateTime VARCHAR(64),
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**订单发送历史*/
CREATE TABLE ims_eso_sale_order_sent_history (
  id         INT(32)      NOT NULL AUTO_INCREMENT,
  orderNo    VARCHAR(64)  NOT NULL,
  businessNo VARCHAR(256) NOT NULL,
  createTime VARCHAR(64),
  updateTime VARCHAR(64),
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**新增订单的物流单号字段*/
ALTER TABLE ims_eso_sale_order ADD COLUMN logisBillNo VARCHAR(128);
/**海外购中外运订单发送结果记录表*/
CREATE TABLE ims_eso_sale_sino_result_record (
  id             INT(32) NOT NULL AUTO_INCREMENT,
  business_no    VARCHAR(1024),
  chk_mark       VARCHAR(32),
  notice_time    VARCHAR(128),
  notice_content VARCHAR(1024),
  business_type  VARCHAR(128),
  way_bills      VARCHAR(256),
  create_time    VARCHAR(128),
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**-全国城市邮政编码表*/
CREATE TABLE ims_eso_sale_zip_code (
  id       INT(32) NOT NULL AUTO_INCREMENT,
  city     VARCHAR(128),
  province VARCHAR(128),
  zipCode  VARCHAR(128),
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**-推送给支付宝报关接口的请求参数*/
CREATE TABLE ims_eso_sale_alipay_customs_reqeust (
  id            INT(32) NOT NULL AUTO_INCREMENT,
  orderNo       VARCHAR(128),
  alipayTradeNo VARCHAR(128),
  outRequestNo  VARCHAR(128),
  amout         DOUBLE(32, 4),
  customsPlace  VARCHAR(128),
  createTime    VARCHAR(128),
  updateTime    VARCHAR(128),
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**-推送支付宝报关接口的返回结果*/
CREATE TABLE ims_eso_sale_alipay_customs_result (
  id                      INT(32) NOT NULL AUTO_INCREMENT,
  orderNo                 VARCHAR(128),
  isSuccess               VARCHAR(32),
  error                   VARCHAR(128),
  amout                   DOUBLE(32, 4),
  customsPlace            VARCHAR(128),
  alipayTradeNo           VARCHAR(128),
  alipayDeclareNo         VARCHAR(128),
  resultCode              VARCHAR(32),
  detailResultDescription VARCHAR(256),
  createTime              VARCHAR(128),
  updateTime              VARCHAR(128),
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**-返佣计划*/
CREATE TABLE ims_eso_sale_repayment_plan (
  id                        INT(32) NOT NULL AUTO_INCREMENT,
  orderId                   INT(32),
  orderNo                   VARCHAR(128),
  headCorpId                INT(32),
  subCorpId                 INT(32),
  retailerCorpId            INT(32),
  headCorpPrice             DOUBLE(64, 4),
  subCorpPriceFromHeadCorp  DOUBLE(64, 4),
  retailerPriceFromHeadCorp DOUBLE(64, 4),
  retailerPrice             DOUBLE(64, 4),
  headCorpProfit            DOUBLE(64, 4),
  subCorpProfit             DOUBLE(64, 4),
  retailerProfit            DOUBLE(64, 4),
  belongsToCorp             VARCHAR(1024),
  status                    VARCHAR(2),
  applyId                   INT(32),
  createdTime               DATETIME,
  updatedTime               DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**-返佣申请*/
CREATE TABLE ims_eso_sale_repayment_apply (
  id                  INT(32) NOT NULL AUTO_INCREMENT,
  applyNo             VARCHAR(128),
  retailerApplyAmount DOUBLE(64, 4),
  subCorpApplyAmount  DOUBLE(64, 4),
  subCorpId           INT(32),
  applicantId         INT(32),
  subCorpName         VARCHAR(1024),
  retailerName        VARCHAR(1024),
  status              VARCHAR(2),
  createdDate         DATETIME,
  updatedDate         DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**-返佣申请历史*/
CREATE TABLE ims_eso_sale_repayment_apply_history (
  id              INT(32) NOT NULL AUTO_INCREMENT,
  applyId         INT(32),
  repaymentPlanId INT(32),
  createdDate     DATETIME,
  updatedDate     DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**-短信模板*/
CREATE TABLE ims_eso_sale_sms_template (
  id              INT(32) NOT NULL AUTO_INCREMENT,
  templateName    VARCHAR(1024),
  templateContent VARCHAR(2048),
  smsType         VARCHAR(512),
  validMinutes    INT(32),
  intervalMinutes INT(32),
  maxSendCount    INT(32),
  createdDate     DATETIME,
  updatedDate     DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**-短信*/
CREATE TABLE ims_eso_sale_sms (
  id          INT(32) NOT NULL AUTO_INCREMENT,
  mobileNo    VARCHAR(64),
  templateId  INT(32),
  content     VARCHAR(2048),
  smsCode     VARCHAR(64),
  smsType     VARCHAR(256),
  expiredDate DATETIME,
  createdDate DATETIME,
  updatedDate DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**-省市代码(连连支付)*/
CREATE TABLE ims_eso_sale_area_code (
  id   INT(32) NOT NULL AUTO_INCREMENT,
  code VARCHAR(128),
  area VARCHAR(512),
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**数据脚本维护
mysql -u root -p123456 we7 --local-infile=1 -e 'load data local infile "/var/www/html/o2o-load-data/unit.txt"  into table ims_eso_sale_unit';
mysql -u root -p123456 we7 --local-infile=1 -e 'load data local infile "/var/www/html/o2o-load-data/taxCode.txt"  into table ims_eso_sale_tax_code';
mysql -u root -p123456 we7 --local-infile=1 -e 'load data local infile "/var/www/html/o2o-load-data/currency.txt"  into table ims_eso_sale_currency';
mysql -u root -p123456 we7 --local-infile=1 -e 'load data local infile "/var/www/html/o2o-load-data/country.txt"  into table ims_eso_sale_country_code';
mysql -u root -p123456 we7 --local-infile=1 -e 'load data local infile "/var/www/html/o2o-load-data/zipCode.txt"  into table ims_eso_sale_zip_code(city,province,zipCode)';
mysql -u root -p123456 we7 --local-infile=1 -e 'load data local infile "/var/www/html/o2o-load-data/areaCode.txt"  into table ims_eso_sale_area_code(code,area)';
*/
INSERT INTO ims_eso_sale_sms_template (templateName, templateContent, smsType, validMinutes, createdDate, updatedDate)
VALUES ('短信验证码', '您的短信验证码是:%s,%s分钟内有效【芝码客跨境服务】', 'VALIDATION_CODE', 10, NOW(), NOW());
INSERT INTO ims_eso_sale_sms_template (templateName, templateContent, smsType, validMinutes, createdDate, updatedDate)
VALUES ('密码重置或修改', '密码重置短信验证码是:%s,%s分钟内有效【芝码客跨境服务】', 'PASSWORD_RESET', 10, NOW(), NOW());
INSERT INTO ims_eso_sale_sms_template (templateName, templateContent, smsType, validMinutes, createdDate, updatedDate)
VALUES ('订单发货通知', '您的货物已发出,EMS单号是:%s【芝码客跨境服务】', 'DELIVERED_NOTIFICATION', 10, NOW(), NOW());

/**支付企业返回异步回执结果记录*/
CREATE TABLE ims_eso_sale_pay_channel_return (
  id               INT(32) NOT NULL AUTO_INCREMENT,
  payType          VARCHAR(256),
  status           VARCHAR(128),
  orderNo          VARCHAR(128),
  tradeNo          VARCHAR(128),
  errorDescription VARCHAR(256),
  createdDate      DATETIME,
  updatedDate      DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;

/**库存表*/
CREATE TABLE ims_eso_sale_inventory (
  id             INT(32) NOT NULL AUTO_INCREMENT,
  innerProductId INT(32),
  outerProductId INT(32),
  amount         INT(32),
  priority       INT(32),
  source         VARCHAR(128),
  valid          INT(32),
  isDelete       VARCHAR(4),
  createdDate    DATETIME,
  updatedDate    DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**物流编号*/
CREATE TABLE ims_eso_sale_order_delivery (
  id           INT(32) NOT NULL AUTO_INCREMENT,
  orderId      INT(32),
  deliveryNn   VARCHAR(128),
  deliveryName VARCHAR(256),
  createdDate  DATETIME,
  updatedDate  DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
/**费舍尔商品信息表*/
CREATE TABLE ims_eso_sale_fisher_product (
  id                     INT(32) NOT NULL AUTO_INCREMENT,
  productId              INT(32),
  productNo              VARCHAR(128),
  productName            VARCHAR(256),
  productSpec            VARCHAR(512),
  brandId                INT(32),
  supplierId             INT(32),
  supplierName           VARCHAR(256),
  taxRate                DOUBLE(32, 2),
  countryId              INT(32),
  countryName            VARCHAR(256),
  marketPrice            DOUBLE(64, 2),
  profit                 DOUBLE(64, 2),
  stock                  INT(32),
  boughtNum              INT(32),
  enabledNum             INT(32),
  exemptionPostage       INT(32),
  exemptionServiceCharge INT(32),
  baseNum                INT(32),
  basePrice              DOUBLE(64, 2),
  productBrief           VARCHAR(521),
  productIntroduction    VARCHAR(1024),
  createdDate            DATETIME,
  updatedDate            DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;

ALTER TABLE ims_eso_sale_goods_fz ADD COLUMN fgsj VARCHAR(64);
ALTER TABLE ims_eso_sale_goods_fz ADD COLUMN jxsj VARCHAR(64);
ALTER TABLE ims_eso_sale_address ADD COLUMN identityNo VARCHAR(32);
ALTER TABLE ims_eso_sale_order_goods ADD COLUMN outerProductId VARCHAR(255);
#地址里面添加身份证信息

/**公告*/
CREATE TABLE ims_eso_sale_announcement (
  id          INT(32) NOT NULL AUTO_INCREMENT,
  title       VARCHAR(1024),
  content     VARCHAR(2048),
  createdDate DATETIME,
  updatedDate DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;

/**报价单*/
CREATE TABLE ims_eso_sale_price_sheet (
  id          INT(32) NOT NULL AUTO_INCREMENT,
  title       VARCHAR(1024),
  path        VARCHAR(2048),
  isValid     TINYINT(1) DEFAULT 0,
  createdDate DATETIME,
  updatedDate DATETIME,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;