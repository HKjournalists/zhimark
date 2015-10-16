package com.spring.mvc.model.gson.fisher;

import com.spring.mvc.model.gson.fisher.base.FisherResultDataGson;

/**
 * 获取易宝的token gson对象
 * Created by liluoqi on 15/9/11.
 */
public class FisherAccessTokenGson extends FisherResultDataGson {
    private String member_id;
    private String token;
    private String expire;

    public String getMember_id() {
        return member_id;
    }

    public String getToken() {
        return token;
    }

    public String getExpire() {
        return expire;
    }
}
