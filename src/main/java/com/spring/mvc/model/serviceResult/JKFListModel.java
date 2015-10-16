package com.spring.mvc.model.serviceResult;

/**
 * Created by liluoqi on 15/5/4.
 */
public class JKFListModel {
    private JKFResultModel jkfResult;

    public JKFListModel() {

    }

    public JKFListModel(JKFResultModel jkfResult) {
        this.jkfResult = jkfResult;
    }

    public JKFResultModel getJkfResult() {
        return jkfResult;
    }

    public void setJkfResult(JKFResultModel jkfResult) {
        this.jkfResult = jkfResult;
    }
}
