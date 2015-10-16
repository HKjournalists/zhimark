package com.spring.mvc.model.serviceResult;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by liluoqi on 15/5/4.
 */
public class JKFResultListModel {
    @XStreamImplicit(itemFieldName = "jkfResultDetail")
    private List<JKFResultDetail> jkfResultDetail;

    public JKFResultListModel() {

    }

    public JKFResultListModel(List<JKFResultDetail> jkfResultDetail) {
        this.jkfResultDetail = jkfResultDetail;
    }
}
