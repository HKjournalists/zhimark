package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.model.gson.PaginationGson;
import com.spring.mvc.service.RepaymentPlanService;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by liluoqi on 15/6/26.
 */
@Component
@Path("repayment-plan")
public class RepaymentPlanResource {

    private Logger logger = Logger.getLogger(RepaymentPlanResource.class);
    @Autowired
    private RepaymentPlanService repaymentPlanService;

    /**
     * @param currentPage
     * @param headCorpId
     * @param subCorpId
     * @param retailerCorpId
     * @param status
     * @param startTime
     * @param endTime
     * @param callBack       支持跨院Ajax访问,前台访问时可以用callback=?
     * @return json or jsonp格式的数据
     */
    @GET
    @Path("query-plans/")
    @Produces("application/json")
    public String queryRepaymentPlans(@QueryParam("currentPage") String currentPage,
                                      @QueryParam("headCorpId") String headCorpId,
                                      @QueryParam("subCorpId") String subCorpId,
                                      @QueryParam("retailerCorpId") String retailerCorpId,
                                      @QueryParam("status") String status,
                                      @QueryParam("startTime") String startTime,
                                      @QueryParam("endTime") String endTime,
                                      @QueryParam("callback") String callBack) {
        try {
            logger.info(String.format("receive params from outer sys are:currentPage:%s,headCorpId:%s" +
                            "subCorpId:%s,retailerCorpId:%s,status:%s,startTime:%s,endTime:%s", currentPage, headCorpId,
                    subCorpId, retailerCorpId, status, startTime, endTime));
            if (StringUtils.isEmptyOrNull(currentPage)) {
                currentPage = "1";
            }

            if (StringUtils.emptyString().equals(status)) {
                status = null;
            }
            PaginationGson paginationGson = repaymentPlanService.queryPlansByConditions(currentPage,
                    headCorpId, subCorpId, retailerCorpId, status, startTime, endTime);
            if (!StringUtils.isEmptyOrNull(callBack)) {
                return String.format("%s(%s)", callBack, new Gson().toJson(paginationGson));
            }
            return new Gson().toJson(paginationGson);
        } catch (Exception e) {
            logger.error(String.format("查询返佣计划报错:%s", e.getMessage()), e);
            return new Gson().toJson(new PaginationGson());
        }
    }


    /**
     * @param headCorpId
     * @param subCorpId
     * @param retailerCorpId
     * @param startTime
     * @param endTime
     * @param callBack       支持跨院Ajax访问,前台访问时可以用callback=?
     * @return json or jsonp格式的数据
     */
    @GET
    @Path("profit-general")
    @Produces("application/json")
    public String getProfit(@QueryParam("headCorpId") String headCorpId,
                            @QueryParam("subCorpId") String subCorpId,
                            @QueryParam("retailerCorpId") String retailerCorpId,
                            @QueryParam("startTime") String startTime,
                            @QueryParam("endTime") String endTime,
                            @QueryParam("callback") String callBack) {
        try {
            logger.info(String.format("receive params from outer sys are:headCorpId:%s" +
                            "subCorpId:%s,retailerCorpId:%s,startTime:%s,endTime:%s", headCorpId,
                    subCorpId, retailerCorpId, startTime, endTime));
            if (!StringUtils.isEmptyOrNull(callBack)) {
                return String.format("%s(%s)", callBack, new Gson().toJson(repaymentPlanService.getProfitGeneral(headCorpId, subCorpId, retailerCorpId, startTime, endTime)));
            }
            return new Gson().toJson(repaymentPlanService.getProfitGeneral(headCorpId, subCorpId, retailerCorpId, startTime, endTime));
        } catch (Exception e) {
            logger.error(String.format("获取利润信息异常:%s", e.getMessage()), e);
        }
        return new Gson().toJson(StringUtils.emptyString());
    }

    @GET
    @Path("query-applied-plans")
    @Produces("application/json")
    public String queryAppliedPlans(@QueryParam("applyId") String applyId,
                                    @QueryParam("currentPage") String currentPage,
                                    @QueryParam("callback") String callback) {
        try {
            if (StringUtils.isEmptyOrNull(currentPage)) {
                currentPage = "1";
            }
            logger.info(String.format("根据佣金申请ID:%s获取所有的返佣计划", applyId));
            PaginationGson paginationGson = repaymentPlanService.queryAppliedPlansPagination(applyId, currentPage);
            return StringUtils.isEmptyOrNull(callback) ? new Gson().toJson(paginationGson) : String.format("%s(%s)", callback, new Gson().toJson(paginationGson));
        } catch (Exception e) {
            logger.error(String.format("根据佣金申请ID:%s获返佣金计划异常:%s", applyId, e.getMessage()), e);
        }
        return StringUtils.isEmptyOrNull(callback) ? new Gson().toJson(new PaginationGson()) : String.format("%s(%s)", callback, new Gson().toJson(new PaginationGson()));
    }
}
