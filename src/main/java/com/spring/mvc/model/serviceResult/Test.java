package com.spring.mvc.model.serviceResult;

import com.google.gson.Gson;
import com.spring.mvc.model.OrderStatus;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liluoqi on 15/5/4.
 */
public class Test {
    public static void main(String[] args) {
//        List<JKFResultDetail> jkfResultDetails = new ArrayList<JKFResultDetail>();
//        jkfResultDetails.add(new JKFResultDetail("xx"));
//        jkfResultDetails.add(new JKFResultDetail("ccc"));
//        JKFResultListModel jkfResultListModel = new JKFResultListModel(jkfResultDetails);
//        JKFResultModel jkfResultModel = new JKFResultModel("xxx", "rrr", "gg", "e", "d", "t", "u");
//        jkfResultModel.setResultList(jkfResultListModel);
//        JKFListModel jkfListModel = new JKFListModel(jkfResultModel);
//        EcoPortResultBodyModel ecoPortResultBodyModel = new EcoPortResultBodyModel(jkfListModel);
//        EcoPortResultHeadModel ecoPortResultHeadModel = new EcoPortResultHeadModel("sfasd");
//        EcoPortResultMoModel ecoPortResultMoModel = new EcoPortResultMoModel();
//        ecoPortResultMoModel.setHead(ecoPortResultHeadModel);
//        ecoPortResultMoModel.setBody(ecoPortResultBodyModel);
//        XStream xStream = new XStream();
//        xStream.autodetectAnnotations(true);
////        System.out.println(xStream.toXML(ecoPortResultMoModel));
//
//        AsyncJKFGoodsDeclare asyncJKFGoodsDeclare = new AsyncJKFGoodsDeclare("CFAK00022900100057",
//                "51", "人工放行", "2014-09-29 13:31:20");
//        AsyncJKFSignModel asyncJKFSignModel = new AsyncJKFSignModel("WC14060601", "LP057");
//        EcoPortAsyncResultBodyModel ecoPortAsyncResultBodyModel = new EcoPortAsyncResultBodyModel(asyncJKFSignModel, asyncJKFGoodsDeclare);
//        EcoPortAsyncResultHeadModel ecoPortAsyncResultHeadModel = new EcoPortAsyncResultHeadModel("PERSONAL_GOODS_DECLAR");
//        EcoPortAsyncResultMoModel ecoPortAsyncResultMoModel=new EcoPortAsyncResultMoModel(ecoPortAsyncResultHeadModel,ecoPortAsyncResultBodyModel);
//        System.out.println(xStream.toXML(ecoPortAsyncResultMoModel));
//        System.out.println((new Date(1138614504)));
        String jsonString="{\"service_result\":[{\"business_no\":\"JKF_XGYX201505201553521790603186_9bccec6c-1ccc-41ed-bb82-bc67ebda0079_20150520155352_278\",\"chk_mark\":\"2\",\"notice_time\":\"2015-05-20 15:53:52\",\"notice_content\":\"处理异常\",\"business_type\":\"ORDER\",\"way_bills\":\"\"}]}";
//        System.out.println((new Gson().fromJson(jsonString,SinoChinaResultModel.class)).getService_result().get(0).getBusiness_no());
        System.out.println(new Gson().toJson(SinoChinaResultModel.getDefaultErrorInstance()));
    }
}
