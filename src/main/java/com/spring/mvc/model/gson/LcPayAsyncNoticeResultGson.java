package com.spring.mvc.model.gson;

/**
 * Created by liluoqi on 15/8/3.
 */
public class LcPayAsyncNoticeResultGson {
    private String merchant_id;
    private String merchant_orderno;
    private String merchant_trans_date;
    private String oid_billno;
    private String pay_status;
    private String pay_create_date;
    private String pay_finish_date;
    private String date_acct;
    private String trans_amt;
    private String trans_cur;
    private String sign;
    private String user_name;
    private String telephone;
    private String idno;

    public String getMerchant_id() {
        return merchant_id;
    }

    public String getMerchant_orderno() {
        return merchant_orderno;
    }

    public String getMerchant_trans_date() {
        return merchant_trans_date;
    }

    public String getOid_billno() {
        return oid_billno;
    }

    public String getPay_status() {
        return pay_status;
    }

    public String getSign() {
        return sign;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getIdno() {
        return idno;
    }

    public String getPay_create_date() {
        return pay_create_date;
    }

    public String getPay_finish_date() {
        return pay_finish_date;
    }

    public String getDate_acct() {
        return date_acct;
    }

    public String getTrans_amt() {
        return trans_amt;
    }

    public String getTrans_cur() {
        return trans_cur;
    }

    public void setOid_billno(String oid_billno) {
        this.oid_billno = oid_billno;
    }
}
