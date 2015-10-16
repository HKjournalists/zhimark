package com.spring.mvc.model;

import java.util.Date;

/**
 * Created by liluoqi on 15/9/21.
 */
public class PriceSheetModel extends BaseModel {
    private String title;
    private String path;
    private boolean isValid;
    private Date createdDate;
    private Date updatedDate;

    public PriceSheetModel() {
    }

    public PriceSheetModel(String title, String path) {
        this.title = title;
        this.path = path;
        this.isValid = true;
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public boolean isValid() {
        return isValid;
    }
}
