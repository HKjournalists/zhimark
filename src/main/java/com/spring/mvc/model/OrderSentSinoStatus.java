package com.spring.mvc.model;

/**发送中外运订单状态
 * Created by liluoqi on 15/5/14.
 */
public enum  OrderSentSinoStatus {

    SENT(1,"已发送给中外运");

    private int code;
    private String description;

    OrderSentSinoStatus(int code, String description){
        this.code=code;
        this.description=description;
    }
}
