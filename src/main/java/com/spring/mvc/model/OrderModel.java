package com.spring.mvc.model;

/**
 * Created by liluoqi on 15/5/5.
 */
public class OrderModel extends BaseModel {
    private int uniacid;
    private int uid;
    private String from_user;
    private int shareid;
    private String ordersn;
    private String price;
    private int status;
    private int sendType;
    private int paytype;
    private String transid;
    private int goodstype;
    private String remark;
    private int addressid;
    private String expresscom;
    private String expresssn;
    private String express;
    private double goodsprice;
    private double dispatchprice;
    private int dispatch;
    private int createtime;
    private String yj;
    private String xys;
    private String jyh;//支付宝交易号
    private String ddh;
    private String logisBillNo;

    public int getUniacid() {
        return uniacid;
    }

    public void setUniacid(int uniacid) {
        this.uniacid = uniacid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public int getShareid() {
        return shareid;
    }

    public void setShareid(int shareid) {
        this.shareid = shareid;
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

    public int getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(int goodstype) {
        this.goodstype = goodstype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public String getExpresscom() {
        return expresscom;
    }

    public void setExpresscom(String expresscom) {
        this.expresscom = expresscom;
    }

    public String getExpresssn() {
        return expresssn;
    }

    public void setExpresssn(String expresssn) {
        this.expresssn = expresssn;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public double getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(double goodsprice) {
        this.goodsprice = goodsprice;
    }

    public double getDispatchprice() {
        return dispatchprice;
    }

    public void setDispatchprice(double dispatchprice) {
        this.dispatchprice = dispatchprice;
    }

    public int getDispatch() {
        return dispatch;
    }

    public void setDispatch(int dispatch) {
        this.dispatch = dispatch;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public String getYj() {
        return yj;
    }

    public void setYj(String yj) {
        this.yj = yj;
    }

    public String getXys() {
        return xys;
    }

    public String getJyh() {
        return jyh;
    }

    public void setJyh(String jyh) {
        this.jyh = jyh;
    }

    public String getDdh() {
        return ddh;
    }

    public void setDdh(String ddh) {
        this.ddh = ddh;
    }

    public void setXys(String xys) {
        this.xys = xys;
    }

    public String getLogisBillNo() {
        return logisBillNo;
    }

    public void setLogisBillNo(String logisBillNo) {
        this.logisBillNo = logisBillNo;
    }
}
