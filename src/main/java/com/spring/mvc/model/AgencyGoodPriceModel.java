package com.spring.mvc.model;

/**
 * Created by liluoqi on 15/6/2.
 */
public class AgencyGoodPriceModel extends BaseModel {
    private String bid;
    private String fgsj;
    private String jxsj;
    private String lsj;
    private String fz;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getFgsj() {
        return fgsj;
    }

    public void setFgsj(String fgsj) {
        this.fgsj = fgsj;
    }

    public String getJxsj() {
        return jxsj;
    }

    public void setJxsj(String jxsj) {
        this.jxsj = jxsj;
    }

    public String getLsj() {
        return lsj;
    }

    public void setLsj(String lsj) {
        this.lsj = lsj;
    }

    public String getFz() {
        return fz;
    }

    public void setFz(String fz) {
        this.fz = fz;
    }
}
