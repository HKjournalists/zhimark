package com.spring.mvc.model;

import java.util.Date;

/**
 * Created by liluoqi on 15/9/20.
 */
public class AnnouncementModel extends BaseModel {
    private String title;
    private String content;
    private Date createdDate;
    private Date updatedDate;

    public AnnouncementModel() {
    }

    public AnnouncementModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
