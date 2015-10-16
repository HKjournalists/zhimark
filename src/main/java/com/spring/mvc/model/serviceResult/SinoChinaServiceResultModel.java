package com.spring.mvc.model.serviceResult;

/**
 * Created by liluoqi on 15/4/29.
 */
public class SinoChinaServiceResultModel {
    private String business_no;
    private String chk_mark;
    private String notice_time;
    private String notice_content;
    private String business_type;
    private String way_bills;//运单号,实时调用返回，异步回执不在返回

    public SinoChinaServiceResultModel() {

    }

    public SinoChinaServiceResultModel(String chk_mark, String notice_time, String notice_content) {
        this.chk_mark = chk_mark;
        this.notice_time = notice_time;
        this.notice_content = notice_content;
        this.business_type = "ORDER";
    }

    public String getBusiness_no() {
        return business_no;
    }

    public void setBusiness_no(String business_no) {
        this.business_no = business_no;
    }

    public String getChk_mark() {
        return chk_mark;
    }

    public void setChk_mark(String chk_mark) {
        this.chk_mark = chk_mark;
    }

    public String getNotice_time() {
        return notice_time;
    }

    public void setNotice_time(String notice_time) {
        this.notice_time = notice_time;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getWay_bills() {
        return way_bills;
    }

    public void setWay_bills(String way_bills) {
        this.way_bills = way_bills;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }
}
