$(function () {

    loadAllData(1);

    $(".search").on("click", function () {
        loadAllData(1);
    });

    $(".nochoice").on("click", function () {
        $(this).addClass("nochoiceBack").parent().siblings().children("div.nochoice").removeClass("nochoiceBack");
        loadAllData(1);
    });

    $(".clear-a").on("click", function () {
        $(this).parent().addClass("selected-th").siblings().removeClass("selected-th").siblings("th[plan-status=" + $(this).parent().attr("plan-status") + "]").addClass("selected-th");
        $(".selected-plan-status").val($(this).parent().attr("plan-status"));
        loadRepaymentPlans(1);
    });

});

function profitGeneralUrl() {
    var profitGeneralUrl = "";
    if (isHeadCorp()) {
        profitGeneralUrl = "http://121.40.113.130:8080/gateway/service/repayment-plan/profit-general?headCorpId=" + $(".headCorpId").val();
        return profitGeneralUrl;
    }

    if (isSubCorp()) {
        profitGeneralUrl = "http://121.40.113.130:8080/gateway/service/repayment-plan/profit-general?subCorpId=" + $(".subCorpId").val();
        return profitGeneralUrl;
    }

    if (isRetailer()) {
        profitGeneralUrl = "http://121.40.113.130:8080/gateway/service/repayment-plan/profit-general?retailerCorpId=" + $(".retailerCorpId").val();
        return profitGeneralUrl;
    }
}

function repaymentPlansUrl() {
    var repaymentPlansUrl = "";
    if (isHeadCorp()) {
        repaymentPlansUrl = "http://121.40.113.130:8080/gateway/service/repayment-plan/query-plans?headCorpId=" + $(".headCorpId").val();
        return repaymentPlansUrl;
    }

    if (isSubCorp()) {
        repaymentPlansUrl = "http://121.40.113.130:8080/gateway/service/repayment-plan/query-plans?subCorpId=" + $(".subCorpId").val();
        return repaymentPlansUrl;
    }

    if (isRetailer()) {
        repaymentPlansUrl = "http://121.40.113.130:8080/gateway/service/repayment-plan/query-plans?retailerCorpId=" + $(".retailerCorpId").val();
        return repaymentPlansUrl;
    }
}

function isHeadCorp() {
    return $(".headCorpId").length > 0;
}

function isSubCorp() {
    return $(".headCorpId").length == 0 && $(".subCorpId").length > 0;
}

function isRetailer() {
    return $(".headCorpId").length == 0 && $(".subCorpId").length == 0;
}

function showProfitGeneral(params) {
    $(".apply-div").remove();
    var url;
    if (params) {
        url = profitGeneralUrl() + params + "&callback=?";
    } else {
        url = profitGeneralUrl() + "&callback=?";
    }
    $.getJSON(url, function (data) {
        $("th.settledAmount").html(data.settledProfit);
        $("th.processingAmount").html(data.processingProfit);
        $("div.unsettledAmount").html(data.unsettledProfit);
        $("th.waitingAmount").html(data.waitingProfit);
        $("th.settledCount").html(data.settledOrderCount);
        $("th.processingCount").html(data.processingOrderCount);
        $("th.unsettledCount").html(data.unsettledOrderCount);
        $("th.waitingCount").html(data.waitingOrderCount);
        $("th.unsettledAmount").attr("amount", data.unsettledProfit);
        if (isRetailer()) {
            displayApplyRepayment(data.unsettledProfit);
        }
    });
}

function showRepaymentPlans(params) {
    var url;
    if (params) {
        url = repaymentPlansUrl() + params + "&callback=?";
    } else {
        url = repaymentPlansUrl() + "&callback=?";
    }
    $.getJSON(url, function (data) {
        var repaymentPlans = data.data;
        var paginationInfo = {
            "totalCount": data.totalCount,
            "totalPage": data.totalPage,
            "currentPage": data.currentPage,
            "prePage": data.prePage,
            "nextPage": data.nextPage
        };
        $(".show_repayment_plans").html("没有对应条件的佣金记录!");
        $(".pagination").html("");
        if (data.totalCount > 0) {
            var mergedHtml = TrimPath.processDOMTemplate("repayment-plans", {"repaymentPlans": repaymentPlans});
            $(".show_repayment_plans").html(mergedHtml);
            pagination.pagination(paginationInfo, $(".pagination"), loadRepaymentPlans);
        }
    });
}


function loadProfit() {
    var subCorpId = $(".subCorpSelect").val();
    var retailerCorpId = $(".retailerCorpSelect").val();
    if (isHeadCorp()) {
        if (subCorpId == "0" || subCorpId == $(".headCorpId").val()) {
            if (retailerCorpId && retailerCorpId != "0") {
                showProfitGeneral("&retailerCorpId=" + retailerCorpId + getTimeSpanParam());
            } else {
                showProfitGeneral(getTimeSpanParam());
            }
        } else {
            if (retailerCorpId == "0") {
                showProfitGeneral("&subCorpId=" + subCorpId + getTimeSpanParam());
            } else {
                showProfitGeneral("&subCorpId=" + subCorpId + "&retailerCorpId=" + retailerCorpId + getTimeSpanParam());
            }
        }
    }
    if (isSubCorp()) {
        if (retailerCorpId == "0") {
            showProfitGeneral(getTimeSpanParam());
        } else {
            showProfitGeneral("&retailerCorpId=" + retailerCorpId + getTimeSpanParam());
        }
    }
    if (isRetailer()) {
        showProfitGeneral(getTimeSpanParam());
    }
}

function loadRepaymentPlans(currentPage) {
    var subCorpId = $(".subCorpSelect").val();
    var retailerCorpId = $(".retailerCorpSelect").val();
    var status = $(".selected-plan-status").val();
    if (isHeadCorp()) {
        if (subCorpId == "0") {
            showRepaymentPlans(getTimeSpanParam() + "&currentPage=" + currentPage + "&status=" + status);
        } else {
            if (retailerCorpId == "0") {
                showRepaymentPlans("&subCorpId=" + subCorpId + getTimeSpanParam() + "&currentPage=" + currentPage + "&status=" + status);
            } else {
                showRepaymentPlans("&subCorpId=" + subCorpId + "&retailerCorpId=" + retailerCorpId + getTimeSpanParam() + "&currentPage=" + currentPage + "&status=" + status);
            }
        }
    }
    if (isSubCorp()) {
        if (retailerCorpId == "0") {
            showRepaymentPlans(getTimeSpanParam() + "&currentPage=" + currentPage + "&status=" + status);
        } else {
            showRepaymentPlans("&retailerCorpId = " + retailerCorpId + getTimeSpanParam() + "&currentPage=" + currentPage + "&status=" + status);
        }
    }
    if (isRetailer()) {
        showRepaymentPlans(getTimeSpanParam() + "&currentPage=" + currentPage + "&status=" + status);
    }
}

function loadAllData(currentPage) {
    clearPlanStatus();
    loadProfit();
    loadRepaymentPlans(currentPage);
}

function getTimeSpanParam() {
    var timeSpanParam = "";
    if ($(".nochoiceBack").length > 0) {
        var property = $(".nochoiceBack").attr("time-span");
        if (property == "all") {
            timeSpanParam = "";
        } else {
            timeSpanParam = "&startTime=" + DateUtils.addDate(new Date(), parseInt("-" + property) + 1);
        }
    }
    return timeSpanParam;
}

function clearPlanStatus() {
    $(".clear-a").parent().removeClass("selected-th").siblings().removeClass("selected-th");
    $(".selected-plan-status").val("");
}

function displayApplyRepayment(unsettledProfit) {
    if (unsettledProfit && unsettledProfit > 0) {
        $("th.unsettledAmount").append("<div class='apply-div' style='float: right;'><a href='javascript:void(0);' class='btn btn-default buttonBorder apply-settle'>申请结算</a></div>");
        $(".apply-settle").on("click", function () {
            var applyAmount = $(this).parent().parent().attr("amount");
            var applicantId = $(".retailerCorpId").val();
            var params = {"applyAmount": applyAmount, "applicantId": applicantId};
            $.getJSON("http://121.40.113.130:8080/gateway/service/repayment-apply/create?callback=?" + getTimeSpanParam(),
                params, function (data) {
                    if (data) {
                        $("#apply-repayment-success").dialog({
                            resizable: false,
                            height: 140,
                            modal: true,
                            buttons: {
                                "确定": function () {
                                    $(this).dialog("close");
                                    window.location.reload();
                                }
                            }
                        });
                    } else {
                        $("#apply-repayment-fail").dialog({
                            resizable: false,
                            height: 140,
                            modal: true,
                            buttons: {
                                "确定": function () {
                                    $(this).dialog("close");
                                }
                            }
                        });
                    }
                });
        });
    }
}