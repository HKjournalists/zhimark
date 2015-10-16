package com.spring.mvc.model.gson;

import com.spring.mvc.model.PriceSheetModel;
import com.spring.mvc.utils.DateUtils;

/**
 * Created by liluoqi on 15/9/21.
 */
public class PriceSheetGson {
    private int id;
    private String title;
    private String path;
    private boolean isValid;
    private String createdDate;
    private String updatedDate;

    public PriceSheetGson() {
    }

    public PriceSheetGson(PriceSheetModel priceSheetModel) {
        this.id = priceSheetModel.getId();
        this.title = priceSheetModel.getTitle();
        this.path = priceSheetModel.getPath();
        this.isValid = priceSheetModel.isValid();
        this.createdDate = DateUtils.formatDateToString(priceSheetModel.getCreatedDate());
        this.updatedDate = DateUtils.formatDateToString(priceSheetModel.getUpdatedDate());
    }
}
