package com.spring.mvc.model.serviceResult;

import com.spring.mvc.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liluoqi on 15/4/29.
 */
public class SinoChinaResultModel {
    private List<SinoChinaServiceResultModel> service_result;

    private SinoChinaResultModel() {

    }

    private SinoChinaResultModel(List<SinoChinaServiceResultModel> service_result) {
        this.service_result = service_result;
    }

    public List<SinoChinaServiceResultModel> getService_result() {
        return service_result;
    }

    public void setService_result(List<SinoChinaServiceResultModel> service_result) {
        this.service_result = service_result;
    }

    public static SinoChinaResultModel getDefaultErrorInstance() {
        List<SinoChinaServiceResultModel> serviceResult = new ArrayList<SinoChinaServiceResultModel>();
        serviceResult.add(new SinoChinaServiceResultModel("2", DateUtils.formatDateToSeconds(new Date()), String.format("发送数据到中外运失败")));
        return new SinoChinaResultModel(serviceResult);
    }

    public static SinoChinaResultModel buildSinoChinaResultModel(List<SinoChinaServiceResultModel> service_result){
        return new SinoChinaResultModel(service_result);
    }
}
