package com.spring.mvc.model;

import org.springframework.core.annotation.Order;

/**
 * Created by liluoqi on 15/5/14.
 */
public enum OrderStatus {

    CLOSE(-1, "关闭"),
    REMAIN_PAY(0, "待支付"),
    PAID(1, "已支付"),
    DELIVERED(2, "已发货"),
    DONE(3, "已完成"),
    FAIL(4, "失败");

    private int code;
    private String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescriptionByCode(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status.getDescription();
            }
        }
        return null;
    }

    public static OrderStatus getStatusByCode(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    public static String getDescriptionByStatus(OrderStatus status) {
        return status.getDescription();
    }

    public static OrderStatus getStatusByName(String statusName) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.name().equals(statusName)) {
                return status;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        System.out.println(DELIVERED.getCode());
    }
}
