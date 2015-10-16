package com.spring.mvc.proxy.sinoChina;

import com.spring.mvc.component.Properties;
import com.spring.mvc.helper.ConstantsHelper;
import com.spring.mvc.model.gson.sinoChina.SinoChinaLogisBillInfoGson;
import com.spring.mvc.model.serviceResult.SinoChinaResultModel;
import com.spring.mvc.utils.HttpClientUtils;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.MD5Utils;
import com.spring.mvc.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 中外运（海外购）的服务代理接口
 * Created by liluoqi on 15/7/27.
 */
@Service
public class SinoChinaProxy {

    @Autowired
    private Properties properties;
    private Logger logger = Logger.getLogger(SinoChinaProxy.class);

    public <T> T declareOrderToHaiWaiGou(Map map, Class<T> clazz) throws Exception{
        try {
            return (T) HttpClientUtils.postForm(properties.getSendDataInfoToSinoChinaUrl(), map, clazz);
        }catch (Exception e){
            logger.error(String.format("调用海外购报关接口报关失败:%s",e.getMessage()));
            throw e;
        }
    }

    public SinoChinaLogisBillInfoGson invokeLogisInfoInterfaceFromSinoChina(String logisBillNo) {
        try {
            logger.info(String.format("invoke http post to get logis no:%s track info from sino china", logisBillNo));
            Map<String, String> map = MapUtils.convertStringToMap("chnl_code", ConstantsHelper.EMS_CODE, "mailno", "null", "order_id", "null",
                    "merchant_id", properties.getSinoChinaMerchantId(), "waybill_id", logisBillNo);
            map.put("sign", getMd5Text(map, properties.getSinoChinaMd5Key()));
            map.remove("mailno");
            map.remove("order_id");
            return HttpClientUtils.postJson(properties.getSinoChinaLogisTrackUrl(), map, SinoChinaLogisBillInfoGson.class);
        } catch (Exception e) {
            logger.error(String.format("error to invoke http post to get logis no:%s track info from sino china", logisBillNo), e);
            return null;
        }
    }


    private String getMd5Text(Map<String, String> map, String md5Key) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : MapUtils.getSortedKeyString(map, MapUtils.SortType.ASC)) {
            stringBuilder.append(String.format("%s=%s&", key, map.get(key)));
        }
        return MD5Utils.string2MD5(stringBuilder.append(md5Key).toString());
    }

    public static void main(String[] args) {
        Map<String, String> map = MapUtils.convertStringToMap("chnl_code", "000004", "mail", "null", "merchant_id", "2015072717334604531778", "waybill_id", "5161311256803");
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : MapUtils.getSortedKeyString(map, MapUtils.SortType.ASC)) {
            stringBuilder.append(String.format("%s=%s&", key, map.get(key)));
        }
        System.out.println(MD5Utils.string2MD5(stringBuilder.append("Edf55Q").toString()));
    }
}
