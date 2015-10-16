package com.spring.mvc.model.serviceResult;

/**
 * Created by liluoqi on 15/5/4.
 */
public class JKFResultDetail {
    private String resultInfo;

    public JKFResultDetail() {

    }

    public JKFResultDetail(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }
}
