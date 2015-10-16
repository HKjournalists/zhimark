package com.spring.mvc.model;

/**
 * 组商品
 * Created by liluoqi on 15/6/16.
 */
public class GroupGoodModel extends BaseModel{
    private String gid;//商品id
    private String n;//每组包含商品数量
    private String je;//

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }
}
