package com.spring.mvc.model.gson.ecoPort;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liluoqi on 15/5/1.
 */
public class Test {
    public static  void main(String[] args){
        EcoPortMoGson ecoPortMoGson =new EcoPortMoGson();
        EcoPortJKFGoodsPurchaserGson jkfGoodsPurchaserModel=new EcoPortJKFGoodsPurchaserGson("x","c","s","r","f","t","g");
        List<EcoPortJKFOrderDetailGson> jkfOrderDetailModels=new ArrayList<EcoPortJKFOrderDetailGson>();
        jkfOrderDetailModels.add(new EcoPortJKFOrderDetailGson());
        jkfOrderDetailModels.add(new EcoPortJKFOrderDetailGson());
        EcoPortJKFOrderDetailListGson ecoPortJKFOrderDetailListGson =new EcoPortJKFOrderDetailListGson(jkfOrderDetailModels);
        EcoPortJKFOrderImportHeadGson jkfOrderImportHeadModel=new EcoPortJKFOrderImportHeadGson();
        EcoPortJKFSignGson jkfSignModel=new EcoPortJKFSignGson("x","2","2","4","6");
        List<EcoPortOrderInfoGson> ecoPortOrderInfoGsons =new ArrayList<EcoPortOrderInfoGson>();
        ecoPortOrderInfoGsons.add(new EcoPortOrderInfoGson(jkfSignModel,jkfOrderImportHeadModel, ecoPortJKFOrderDetailListGson,jkfGoodsPurchaserModel));
        EcoPortOrderInfoListGson orderInfoListModel=new EcoPortOrderInfoListGson(ecoPortOrderInfoGsons);
        EcoPortBodyGson ecoPortBodyGson =new EcoPortBodyGson(orderInfoListModel);
        EcoPortHeadGson ecoPortHeadGson =new EcoPortHeadGson();
        ecoPortMoGson.setHead(ecoPortHeadGson);
        ecoPortMoGson.setBody(ecoPortBodyGson);
        XStream xStream=new XStream();
        xStream.autodetectAnnotations(true);
        System.out.println(xStream.toXML(ecoPortMoGson));
    }
}
