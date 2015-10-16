package com.spring.mvc.model;

import com.spring.mvc.utils.DateUtils;

import java.util.Date;

/**
 * Created by liluoqi on 15/5/21.
 */
public class SinoResultRecordModel extends BaseModel {
    private String business_no;
    private String chk_mark;
    private String notice_time;
    private String notice_content;
    private String business_type;
    private String way_bills;
    private String create_time;

    public SinoResultRecordModel(String business_no, String chk_mark, String notice_time,
                                 String notice_content, String business_type, String way_bills) {
        this.business_no = business_no;
        this.chk_mark = chk_mark;
        this.notice_time = notice_time;
        this.notice_content = notice_content;
        this.business_type = business_type;
        this.way_bills = way_bills;
        this.create_time= DateUtils.formatDateToSeconds(new Date());
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

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getWay_bills() {
        return way_bills;
    }

    public void setWay_bills(String way_bills) {
        this.way_bills = way_bills;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
