package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.model.RepaymentApplyStatus;
import com.spring.mvc.service.RepaymentApplyService;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import static com.spring.mvc.model.RepaymentApplyStatus.NEW;

/**
 * Created by liluoqi on 15/7/1.
 */
@Component
@Path("repayment-apply")
public class RepaymentApplyResource {

    private Logger logger = Logger.getLogger(RepaymentApplyResource.class);

    @Autowired
    private RepaymentApplyService repaymentApplyService;

    @GET
    @Path("create")
    @Produces("application/json")
    public String createNewRepaymentApply(@QueryParam("applyAmount") double applyAmount,
                                          @QueryParam("applicantId") int applicantId,
                                          @QueryParam("startTime") String startTime,
                                          @QueryParam("endTime") String endTime,
                                          @QueryParam("callback") String callback) {
        try {
            logger.info(String.format("返佣申请接口收到的请求参数,applyAmount:%s,applicantId:%s,startTime:%s,endTime:%s", applyAmount, applicantId, startTime, endTime));
            if (!StringUtils.isEmptyOrNull(callback)) {
                return String.format("%s(%s)", callback, repaymentApplyService.createRepaymentApply(applyAmount, applicantId, startTime, endTime));
            }
            return new Gson().toJson(repaymentApplyService.createRepaymentApply(applyAmount, applicantId, startTime, endTime));
        } catch (Exception e) {
            logger.error(String.format("返佣申请异常:%s", e.getMessage()), e);
            return StringUtils.emptyString();
        }
    }

    @GET
    @Path("query-applies")
    @Produces("application/json")
    public String queryApplies(@QueryParam("applicantId") String applicantId,
                               @QueryParam("subCorpId") String subCorpId,
                               @QueryParam("status") String status,
                               @QueryParam("currentPage") String currentPage,
                               @QueryParam("startTime") String startTime,
                               @QueryParam("endTime") String endTime,
                               @QueryParam("callback") String callback) {
        try {

            if (StringUtils.isEmptyOrNull(currentPage)) {
                currentPage = "1";
            }
            if (StringUtils.emptyString().equals(status)) {
                status = null;
            }
            //applicantId经销商ID
            if (!StringUtils.isEmptyOrNull(callback)) {
                return String.format("%s(%s)", callback, new Gson().toJson(repaymentApplyService.queryAppliesPagination(applicantId, subCorpId, status, Integer.valueOf(currentPage), startTime, endTime)));
            }
            return new Gson().toJson(repaymentApplyService.queryAppliesPagination(applicantId, subCorpId, status, Integer.valueOf(currentPage), startTime, endTime));
        } catch (Exception e) {
            return StringUtils.emptyString();
        }
    }

    @GET
    @Path("approve")
    @Produces("application/json")
    public String approve(@QueryParam("repaymentApplyId") String repaymentApplyId,
                          @QueryParam("callback") String callback) {
        boolean result = false;
        try {
            logger.info(String.format("收到佣金申请ID:%s支付通过参数", repaymentApplyId));
            if (!StringUtils.isEmptyOrNull(repaymentApplyId)) {
                result = repaymentApplyService.approveRepaymentApplyVerity(Integer.valueOf(repaymentApplyId));
            }
        } catch (NumberFormatException e) {
            logger.error(String.format("申请ID:%s转换为整型异常:%s", repaymentApplyId, e.getMessage()), e);
        }
        return StringUtils.isEmptyOrNull(callback) ? new Gson().toJson(result) : String.format("%s(%s)", callback, new Gson().toJson(result));
    }

    @GET
    @Path("reject")
    @Produces("application/json")
    public String reject(@QueryParam("repaymentApplyId") String repaymentApplyId,
                         @QueryParam("callback") String callback) {
        boolean result = false;
        try {
            logger.info(String.format("收到佣金申请ID:%s拒绝通过参数", repaymentApplyId));
            if (!StringUtils.isEmptyOrNull(repaymentApplyId)) {
                result = repaymentApplyService.declineRepaymentApplyVerity(Integer.valueOf(repaymentApplyId));
            }
        } catch (NumberFormatException e) {
            logger.error(String.format("申请ID:%s转换为整型异常:%s", repaymentApplyId, e.getMessage()), e);
        }
        return StringUtils.isEmptyOrNull(callback) ? new Gson().toJson(result) : String.format("%s(%s)", callback, new Gson().toJson(result));
    }
}
