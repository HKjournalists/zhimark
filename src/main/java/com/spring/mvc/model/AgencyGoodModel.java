package com.spring.mvc.model;

/**
 * Created by liluoqi on 15/5/29.
 */
public class AgencyGoodModel extends BaseModel{
    private int uniacid;
    private int gid;
    private int mid;
    private int dl;
    private String addtime;
    private String dlj;
    private String fgsdj;
    private String lsj;

    public int getUniacid() {
        return uniacid;
    }

    public void setUniacid(int uniacid) {
        this.uniacid = uniacid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getDl() {
        return dl;
    }

    public void setDl(int dl) {
        this.dl = dl;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getDlj() {
        return dlj;
    }

    public void setDlj(String dlj) {
        this.dlj = dlj;
    }

    public String getFgsdj() {
        return fgsdj;
    }

    public void setFgsdj(String fgsdj) {
        this.fgsdj = fgsdj;
    }

    public String getLsj() {
        return lsj;
    }

    public void setLsj(String lsj) {
        this.lsj = lsj;
    }
}
