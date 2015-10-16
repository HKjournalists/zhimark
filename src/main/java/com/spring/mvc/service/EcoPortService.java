package com.spring.mvc.service;

import com.spring.mvc.model.OrderModel;
import com.spring.mvc.model.gson.ecoPort.*;
import com.spring.mvc.model.serviceResult.EcoPortResultMoModel;
import com.spring.mvc.proxy.EcoPortWebserviceClient.ReceivedDeclareService;
import com.spring.mvc.proxy.EcoPortWebserviceClient.ReceivedDeclareServiceImplServiceLocator;
import com.spring.mvc.utils.BusinessNoGenerator;
import com.spring.mvc.utils.Logger;
import com.thoughtworks.xstream.XStream;
import org.apache.axis.AxisFault;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**电子口岸的业务逻辑
 * Created by liluoqi on 15/5/1.
 */
@Service
public class EcoPortService {

    private Logger logger = Logger.getLogger(EcoPortService.class);

    public void receiveEcoPortDeclare(OrderModel order) {
        ReceivedDeclareService receivedDeclareService;
        try {
            receivedDeclareService = new ReceivedDeclareServiceImplServiceLocator().getReceivedDeclareServiceImplPort();
            XStream xStream = new XStream();
            xStream.autodetectAnnotations(true);
            String resultXmlString = receivedDeclareService.checkReceived(xStream.toXML(getEcoPortBusinessDataAsXmlString(BusinessNoGenerator.generateEcoBusinessNo(order.getOrdersn()))), "IMPORTORDER", "1");
            xStream.processAnnotations(EcoPortResultMoModel.class);
            handleInstantResultFromEcoPort(resultXmlString,"");
//            return (EcoPortResultMoModel)xStream.fromXML(resultXmlString);
        } catch (AxisFault axisFault) {
            logger.error(String.format("AxisFault error happens with message:%s,stackTrace is :%s", axisFault.getMessage(), axisFault.getStackTrace()));
        } catch (RemoteException e) {
            logger.error(String.format("电子口岸远程异常,信息:%s,错误堆栈:%s", e.getMessage(), e.getStackTrace()));
        } catch (ServiceException e) {
            logger.error(String.format("webService of eco port exception happens when invoke with message:%s and stackTrace is:%s", e.getMessage(),
                    e.getStackTrace()));
        }
//        return new EcoPortResultMoModel();
    }

    //todo read data from the database and return eco port format data
    private EcoPortMoGson getEcoPortBusinessDataAsXmlString(String businessNo) {
        EcoPortMoGson ecoPortMoGson = new EcoPortMoGson();
        EcoPortJKFGoodsPurchaserGson jkfGoodsPurchaserModel = new EcoPortJKFGoodsPurchaserGson("x", "c", "s", "r", "f", "t", "g");
        List<EcoPortJKFOrderDetailGson> jkfOrderDetailModels = new ArrayList<EcoPortJKFOrderDetailGson>();
        jkfOrderDetailModels.add(new EcoPortJKFOrderDetailGson());
        jkfOrderDetailModels.add(new EcoPortJKFOrderDetailGson());
        EcoPortJKFOrderDetailListGson ecoPortJKFOrderDetailListGson = new EcoPortJKFOrderDetailListGson(jkfOrderDetailModels);
        EcoPortJKFOrderImportHeadGson jkfOrderImportHeadModel = new EcoPortJKFOrderImportHeadGson();
        EcoPortJKFSignGson jkfSignModel = new EcoPortJKFSignGson("x", "2", "2", "4", "6");
        List<EcoPortOrderInfoGson> ecoPortOrderInfoGsons = new ArrayList<EcoPortOrderInfoGson>();
        ecoPortOrderInfoGsons.add(new EcoPortOrderInfoGson(jkfSignModel, jkfOrderImportHeadModel, ecoPortJKFOrderDetailListGson, jkfGoodsPurchaserModel));
        EcoPortOrderInfoListGson orderInfoListModel = new EcoPortOrderInfoListGson(ecoPortOrderInfoGsons);
        EcoPortBodyGson ecoPortBodyGson = new EcoPortBodyGson(orderInfoListModel);
        EcoPortHeadGson ecoPortHeadGson = new EcoPortHeadGson();
        ecoPortMoGson.setHead(ecoPortHeadGson);
        ecoPortMoGson.setBody(ecoPortBodyGson);
        return ecoPortMoGson;
    }

    public void handleAsyncResultFromEcoPort(){

    }

    private void handleInstantResultFromEcoPort(String xmlString,String businessNo) {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(EcoPortResultMoModel.class);
        EcoPortResultMoModel ecoPortResultMoModel = (EcoPortResultMoModel) xStream.fromXML(xmlString);
        if("1".equals(ecoPortResultMoModel.getBody().getList().getJkfResult().getChkMark())){
            //todo update send status success
            //通过businessNo进行关联处理
        }else {
            //todo no not update send status
        }
    }
}
