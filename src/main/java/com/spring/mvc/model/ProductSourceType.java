package com.spring.mvc.model;

import com.spring.mvc.utils.StringUtils;

/**
 * Created by liluoqi on 15/9/5.
 */
public enum ProductSourceType {

    HAI_WAI_GOU(0, "海外购"),
    FISHER(1, "费舍尔"),
    HAI_HUAN(2,"海欢");

    private int code;
    private String description;

    ProductSourceType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ProductSourceType getProductSourceTypeByCode(int code) {
        for (ProductSourceType productSourceType : ProductSourceType.values()) {
            if (productSourceType.getCode() == code) {
                return productSourceType;
            }
        }
        return null;
    }

    public static ProductSourceType getProductSourceTypeByDescription(String description) {
        for (ProductSourceType productSourceType : ProductSourceType.values()) {
            if (productSourceType.getDescription().equals(description)) {
                return productSourceType;
            }
        }
        return null;
    }

    public static String getProductSourceNameByCode(int code) {
        ProductSourceType productSourceType = getProductSourceTypeByCode(code);
        return productSourceType != null ? productSourceType.name() : StringUtils.emptyString();
    }

    public static String getProductSourceNameByDescription(String description) {
        ProductSourceType productSourceType = getProductSourceTypeByDescription(description);
        return productSourceType != null ? productSourceType.name() : StringUtils.emptyString();
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
