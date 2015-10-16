package com.spring.mvc.model.serviceResult;

import java.util.List;

/**
 * Created by liluoqi on 15/5/4.
 */
public class EcoPortResultBodyModel {
    private JKFListModel list;

    public EcoPortResultBodyModel() {

    }

    public EcoPortResultBodyModel(JKFListModel list) {
        this.list = list;
    }

    public JKFListModel getList() {
        return list;
    }

    public void setList(JKFListModel list) {
        this.list = list;
    }
}
