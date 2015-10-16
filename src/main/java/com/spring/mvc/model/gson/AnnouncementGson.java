package com.spring.mvc.model.gson;

import com.spring.mvc.model.AnnouncementModel;
import com.spring.mvc.utils.DateUtils;

import java.util.Date;

/**
 * Created by liluoqi on 15/9/21.
 */
public class AnnouncementGson {
    private int id;
    private String title;
    private String content;
    private String createdDate;
    private String updatedDate;

    public AnnouncementGson() {
    }

    public AnnouncementGson(AnnouncementModel announcement) {
        this.id = announcement.getId();
        this.title = announcement.getTitle();
        this.content = announcement.getContent();
        this.createdDate = DateUtils.formatDateToString(announcement.getCreatedDate());
        this.updatedDate = DateUtils.formatDateToString(announcement.getUpdatedDate());
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.formatDateToString(new Date()));
    }
}
