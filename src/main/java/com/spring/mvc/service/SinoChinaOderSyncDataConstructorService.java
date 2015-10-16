package com.spring.mvc.service;

import com.spring.mvc.component.Properties;
import com.spring.mvc.model.*;
import com.spring.mvc.model.gson.sinoChina.SinoChinaOrderGson;
import com.spring.mvc.model.gson.sinoChina.SinoChinaOrderHeaderGson;
import com.spring.mvc.model.gson.sinoChina.SinoGoodsPurchaserGson;
import com.spring.mvc.repository.*;
import com.spring.mvc.utils.DateUtils;
import com.spring.mvc.utils.MathUtils;
import com.spring.mvc.utils.StringUtils;
import com.spring.mvc.utils.WeightUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.spring.mvc.helper.ConstantsHelper.DEALED_GROSS_WEIGHT_ADDITION;
import static com.spring.mvc.model.PayType.getPayTypeFromPayTypeCode;

/**
 * Created by liluoqi on 15/7/27.
 */
@Service
public class SinoChinaOderSyncDataConstructorService {


    @Autowired
    private Properties properties;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private LogisRepository logisRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private OrderGoodRepository orderGoodRepository;
    @Autowired
    private GoodUnitRepository goodUnitRepository;
    @Autowired
    private ZipRepository zipRepository;
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private AgencyGoodPriceRepository agencyGoodPriceRepository;
    @Autowired
    private GroupGoodRepository groupGoodRepository;

    public SinoChinaOrderHeaderGson constructOrderHeader(OrderModel order) {
        CompanyModel company = companyRepository.getCompany();
        AddressModel address = addressRepository.getAddressById(order.getAddressid());
        LogisModel logis = logisRepository.getLogisById(1);//目前只有一个
        SinoChinaOrderHeaderGson sinoChinaOrderHeaderGson = new SinoChinaOrderHeaderGson();
        sinoChinaOrderHeaderGson.setCompanyCode(company.getCompanycode());
        sinoChinaOrderHeaderGson.setCompanyName(company.getCompanyname());
        sinoChinaOrderHeaderGson.setConsignee(address.getRealname().trim());
        sinoChinaOrderHeaderGson.setConsigneeAddress(address.getAddress());
//        sinoChinaOrderHeaderGson.setConsigneeEmail(StringUtils.emptyString());//非必输字段
        sinoChinaOrderHeaderGson.setConsigneeTel(address.getMobile());
        sinoChinaOrderHeaderGson.setConsigneeProvince(address.getProvince());
        sinoChinaOrderHeaderGson.setConsigneeCity(address.getCity());
        sinoChinaOrderHeaderGson.setConsigneeCounty(address.getArea());
        sinoChinaOrderHeaderGson.setCurrCode("142");//人民币的代码142
        sinoChinaOrderHeaderGson.seteCommerceCode(company.geteCommerceCode());
        sinoChinaOrderHeaderGson.seteCommerceName(company.geteCommerceName());
        sinoChinaOrderHeaderGson.setFeeAmount(0);//运费,包邮模式是0
        sinoChinaOrderHeaderGson.setIeFlag("I");//for temporary time use I
        sinoChinaOrderHeaderGson.setLogisCompanyCode(logis.getLogisCompanyCode() != null ? logis.getLogisCompanyCode() : properties.getSinoLogisCode());
        sinoChinaOrderHeaderGson.setLogisCompanyName(logis.getLogisCompanyName() != null ? logis.getLogisCompanyName() : properties.getSinoLogisName());
        sinoChinaOrderHeaderGson.setOrderGoodsAmount(Double.valueOf(order.getPrice()));
        sinoChinaOrderHeaderGson.setOrderNo(order.getOrdersn());
        sinoChinaOrderHeaderGson.setOrderTaxAmount(StringUtils.isEmptyOrNull(order.getXys()) ? 0 : Double.valueOf(order.getXys()));
        sinoChinaOrderHeaderGson.setOrderTotalAmount(Double.valueOf(order.getPrice()));
        String payEnterpriseCode;
        if (PayType.ALIPAY.equals(getPayTypeFromPayTypeCode(order.getPaytype()))) {
            payEnterpriseCode = properties.getAlipayEnterpriseCode();
        } else if (PayType.LIAN_LIAN_PAY.equals(getPayTypeFromPayTypeCode(order.getPaytype()))) {
            payEnterpriseCode = properties.getLcEnterpriseCode();
        } else {
            payEnterpriseCode = PayType.UNCERTAIN_PAY_TYPE.getDesc();
        }
        sinoChinaOrderHeaderGson.setPayCompanyCode(payEnterpriseCode);
        sinoChinaOrderHeaderGson.setPayNumber(order.getJyh());
//        StringUtils.emptyString().equals(String.valueOf(order.getPaytype())) ? "03" : String.format("0%s", order.getPaytype())
        sinoChinaOrderHeaderGson.setPayType("02");
        sinoChinaOrderHeaderGson.setPostMode("3");//发货方式必填
        sinoChinaOrderHeaderGson.setPurchaserId(String.valueOf(order.getUid()));
        sinoChinaOrderHeaderGson.setSenderCountry(logis.getGb() != null ? countryRepository.getCountryByName(logis.getGb()).getCode() : "142");
        sinoChinaOrderHeaderGson.setSenderName(logis.getName() != null ? logis.getName() : company.getCompanyname());
        sinoChinaOrderHeaderGson.setTotalAmount(Double.valueOf(order.getPrice()));
        List<OrderGoodModel> orderGoodModels = orderGoodRepository.getOrderGoodsByOrderId(order.getId());
        int totalCount = 0;
        for (OrderGoodModel orderGood : orderGoodModels) {
            totalCount = totalCount + orderGood.getTotal() * Integer.valueOf(orderGood.getGn());
        }
        sinoChinaOrderHeaderGson.setTotalCount(totalCount);
        sinoChinaOrderHeaderGson.setTradeTime(DateUtils.formatDateToSeconds(DateUtils.formatUnixTimestampToDate(order.getCreatetime())));
        ZipModel zipModel = zipRepository.getMostLikelyZipByCity(address.getCity(), address.getProvince());
        sinoChinaOrderHeaderGson.setZipCode(zipModel == null ? StringUtils.emptyString() : zipModel.getZipCode());//非必填
//        sinoChinaOrderHeaderGson.setWayBills("");//非必填
        sinoChinaOrderHeaderGson.setRate("1");//现在只使用人民币所以默认都是1
        sinoChinaOrderHeaderGson.setBonded(1);//1为保税模式
        sinoChinaOrderHeaderGson.setBillNoType("EMS");//非必填
        sinoChinaOrderHeaderGson.setUserProtocol(properties.getUserProtocol());
        return sinoChinaOrderHeaderGson;
    }

    public List<SinoChinaOrderGson> constructOrderDetailList(OrderModel order) {
        List<SinoChinaOrderGson> orderList = new ArrayList<SinoChinaOrderGson>();
        Map<OrderGoodModel, GoodModel> goodIdsAndGoodInfoRelation = getGoodsIdAndGoodInfoContainInOrder(orderGoodRepository.getOrderGoodsByOrderId(order.getId()));
        Set<OrderGoodModel> goodIdSet = goodIdsAndGoodInfoRelation.keySet();
        int index = 1;
        for (OrderGoodModel orderGood : goodIdSet) {
            GoodModel good = goodIdsAndGoodInfoRelation.get(orderGood);
            SinoChinaOrderGson sinoChinaOrderGson = new SinoChinaOrderGson();
            sinoChinaOrderGson.setCodeTs(good.getParcelTaxCode());//关联到税率字典表
            sinoChinaOrderGson.setGoodsCount(orderGood.getTotal() * Integer.valueOf(orderGood.getGn()));
            sinoChinaOrderGson.setGoodsModel("无");
            sinoChinaOrderGson.setGoodsName(good.getTitle());
            sinoChinaOrderGson.setGoodsOrder(index);
            if (StringUtils.emptyString().equals(good.getDw())) {
                sinoChinaOrderGson.setGoodsUnit(StringUtils.emptyString());
            } else {
                GoodUnitModel goodUnitModel = goodUnitRepository.getGoodUnitByUnitId(Integer.valueOf(good.getDw()));
                sinoChinaOrderGson.setGoodsUnit(goodUnitModel == null ? StringUtils.emptyString() : goodUnitModel.getCode());
            }
            sinoChinaOrderGson.setGrossWeight(MathUtils.add(WeightUtils.formatGramToKiloGramDouble(good.getWeight()), DEALED_GROSS_WEIGHT_ADDITION));
            sinoChinaOrderGson.setNetWeight(WeightUtils.formatGramToKiloGramDouble(good.getWeight()));
            CountryModel country = countryRepository.getCountryById(Integer.valueOf(good.getGuojia()));
            sinoChinaOrderGson.setOriginCountry(country != null ? country.getCode() : "300");//300代表欧洲
            //计算单个零售价
//            AgencyGoodPriceModel agencyGoodPrice = agencyGoodPriceRepository.getAgencyGoodPriceModelById(Integer.valueOf(orderGood.getLid()));
//            GroupGoodModel groupGood = groupGoodRepository.getGroupGoodById(Integer.valueOf(agencyGoodPrice.getFz()));
//            sinoChinaOrderGson.setUnitPrice(MathUtils.divide(agencyGoodPrice.getLsj(), groupGood.getN()));
            sinoChinaOrderGson.setUnitPrice(MathUtils.divide(orderGood.getPrice(), orderGood.getGn()));
            sinoChinaOrderGson.setGoodsItemNo(good.getGoodItemNo());//物料号
            orderList.add(sinoChinaOrderGson);
            index++;
        }
        return orderList;
    }

    public SinoGoodsPurchaserGson constructGoodsPurchaser(OrderModel order) {
//        McMemberModel purchaser = mcMemberRepository.getMcMemberByUid(order.getUid());
        AddressModel address = addressRepository.getAddressById(order.getAddressid());
        SinoGoodsPurchaserGson sinoGoodsPurchaserGson = new SinoGoodsPurchaserGson();
        sinoGoodsPurchaserGson.setId(String.valueOf(order.getUid()));
        sinoGoodsPurchaserGson.setName(address.getRealname().trim());
        sinoGoodsPurchaserGson.setEmail("9999");//必填,写死是"9999"
//        sinoGoodsPurchaserGson.setAddress(address.getAddress());//非必填
//        sinoGoodsPurchaserGson.setPaperType("1");//非必填
//        sinoGoodsPurchaserGson.setPaperNumber("xxxxx");//非必填
        sinoGoodsPurchaserGson.setTelNumber(address.getMobile());
        return sinoGoodsPurchaserGson;
    }

    private Map<OrderGoodModel, GoodModel> getGoodsIdAndGoodInfoContainInOrder(List<OrderGoodModel> orderGoods) {
        Map<OrderGoodModel, GoodModel> relations = new HashMap<OrderGoodModel, GoodModel>();
        for (OrderGoodModel orderGood : orderGoods) {
            relations.put(orderGood, goodRepository.getGoodByGoodId(orderGood.getGoodsid()));
        }
        return relations;
    }


    private Map<OrderGoodModel, Double> getOrderGoodUnitPrice(List<OrderGoodModel> orderGoods) {
        Map<OrderGoodModel, Double> relations = new HashMap<OrderGoodModel, Double>();
        for (OrderGoodModel orderGood : orderGoods) {
            AgencyGoodPriceModel agencyGoodPrice = agencyGoodPriceRepository.getAgencyGoodPriceModelById(Integer.valueOf(orderGood.getLid()));
            GroupGoodModel groupGood = groupGoodRepository.getGroupGoodById(Integer.valueOf(agencyGoodPrice.getFz()));
            relations.put(orderGood, MathUtils.divide(agencyGoodPrice.getLsj(), groupGood.getN()));
        }
        return relations;
    }

}
