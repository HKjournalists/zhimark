$(function () {

    loadRepaymentApplies(1);

    $(".search").on("click", function () {
        loadRepaymentApplies(1);
    });

    $(".nochoice").on("click", function () {
        $(this).addClass("nochoiceBack").parent().siblings().children("div.nochoice").removeClass("nochoiceBack");
        loadRepaymentApplies(1);
    });

    $(".pay").on("click",function(){

    });

});

function loadRepaymentApplies(currentPage) {
    //clearPlanStatus();
    var subCorpId = $(".subCorpSelect").val();
    var retailerCorpId = $(".retailerCorpSelect").val();
    if (isHeadCorp()) {
        if (subCorpId == "0" || subCorpId == $(".headCorpId").val()) {
            if (retailerCorpId && retailerCorpId != "0") {
                showRepaymentApplies(getTimeSpanParam() + "&applicantId=" + retailerCorpId + "&currentPage=" + currentPage);
            } else {
                showRepaymentApplies(getTimeSpanParam() + "&currentPage=" + currentPage);
            }
        } else {
            if (retailerCorpId == "0") {
                showRepaymentApplies("&subCorpId=" + subCorpId + getTimeSpanParam() + "&currentPage=" + currentPage);
            } else {
                showRepaymentApplies("&subCorpId=" + subCorpId + "&applicantId=" + retailerCorpId + getTimeSpanParam() + "&currentPage=" + currentPage);
            }
        }
    }
    if (isSubCorp()) {
        var subCorpId = $(".subCorpId").val();
        var retailerCorpId = $(".retailerCorpSelect").val();
        if (retailerCorpId && retailerCorpId != "0") {
            showRepaymentApplies(getTimeSpanParam() + "&applicantId=" + retailerCorpId + "&currentPage=" + currentPage);
        } else {
            showRepaymentApplies(getTimeSpanParam() + "&subCorpId=" + subCorpId + "&currentPage=" + currentPage);
        }
    }

    if (isRetailerCorp()) {
        var retailerCorpId = $(".retailerCorpId").val();
        showRepaymentApplies(getTimeSpanParam() + "&applicantId=" + retailerCorpId + "&currentPage=" + currentPage);
    }

}

function showRepaymentApplies(params) {
    var url = "http://121.40.113.130:8080/gateway/service/repayment-apply/query-applies?callback=?";
    if (params) {
        url = url + params
    }
    $.getJSON(url, function (data) {
        var repaymentApplies = data.data;
        var paginationInfo = {
            "totalCount": data.totalCount,
            "totalPage": data.totalPage,
            "currentPage": data.currentPage,
            "prePage": data.prePage,
            "nextPage": data.nextPage
        };
        $(".show_repayment_applies").html("没有对应条件的佣金申请记录!");
        $(".pagination").html("");
        if (data.totalCount > 0) {
            var isHeadCorp = $(".role").val() == "1";
            var isSubCorp = $(".role").val() == "2";
            var isRetailer = $(".role").val() == "3";
            var mergedHtml = TrimPath.parseTemplate(repaymentAppliesContent).process({
                "repaymentApplies": repaymentApplies,
                "isHeadCorp": isHeadCorp,
                "isSubCorp": isSubCorp,
                "isRetailer": isRetailer
            });
            $(".show_repayment_applies").html(mergedHtml);
            pagination.pagination(paginationInfo, $(".pagination"), loadRepaymentApplies);
            //$("th.operation").append("")
            $(".pay-do").on("click", function () {
                var currentApplyDom = $(this);
                $("#pay-confirm").dialog({
                    resizable: false,
                    height: 140,
                    modal: true,
                    buttons: {
                        "支付完成": function () {
                            $.getJSON("http://121.40.113.130:8080/gateway/service/repayment-apply/approve?callback=?",
                                {"repaymentApplyId": $(currentApplyDom).attr("apply-id")}, function (data) {
                                    //if (data) {
                                    //    alert("支付成功");
                                    //    window.location.reload();
                                    //} else {
                                    //    alert("系统异常确认支付失败");
                                    //}
                                });
                            $(this).dialog("close");
                            window.location.reload();
                        },
                        "未能支付": function () {
                            $(this).dialog("close");
                        }
                    }
                });


            });

            $(".reject").on("click", function () {
                var currentApplyDom = $(this);
                $("#reject-repayment").dialog({
                    resizable: false,
                    height: 140,
                    modal: true,
                    buttons: {
                        "确认退回": function () {
                            $.getJSON("http://121.40.113.130:8080/gateway/service/repayment-apply/reject?callback=?",
                                {"repaymentApplyId": $(currentApplyDom).attr("apply-id")}, function (data) {
                                    //if (data) {
                                    //    alert("打回成功");
                                    //    window.location.reload();
                                    //} else {
                                    //    alert("系统异常,审批失败");
                                    //}
                                });
                            $(this).dialog("close");
                            window.location.reload();
                        },
                        "取消退回": function () {
                            $(this).dialog("close");
                        }
                    }
                });
            });
        }
    });
}

function isHeadCorp() {
    return $(".role").val() == "1";
}

function isSubCorp() {
    return $(".role").val() == "2";
}

function isRetailerCorp() {
    return $(".role").val() == "3";
}

function getTimeSpanParam() {
    var timeSpanParam = "";
    if ($(".nochoiceBack").length > 0) {
        var property = $(".nochoiceBack").attr("time-span");
        if (property == "all") {
            timeSpanParam = "";
        } else {
            timeSpanParam = "&startTime=" + DateUtils.addDate(new Date(), parseInt("-" + property));
        }
    }
    return timeSpanParam;
}

function clearPlanStatus() {
    $(".clear-a").parent().removeClass("selected-th").siblings().removeClass("selected-th");
    //$(".selected-plan-status").val("");
}

var repaymentAppliesContent = "{if isHeadCorp }{for repaymentApply in repaymentApplies}" +
    "<tr><th>${repaymentApply.createdDate}</th><th>${repaymentApply.applyNo}</th>" +
    "{if isHeadCorp}<th>${repaymentApply.subCorpApplyAmount}</th><th>${repaymentApply.retailerApplyAmount}</th>{/if}" +
    "{if isSubCorp}<th>${repaymentApply.subCorpApplyAmount}</th>{/if}{if isRetailer}<th>${repaymentApply.retailerApplyAmount}</th>{/if}" +
    "<th>${repaymentApply.subCorpName}</th>" +
    "<th>${repaymentApply.retailerName}</th>" +
    "<th>${repaymentApply.status}</th>" +
    "<th>" + "{if repaymentApply.status=='已申请'}" +
    "<a href='http://www.alipay.com' target='_blank' class='btn btn-link pay-do linkNoPadding pay' apply-id='${repaymentApply.id}'>支付</a>" +
    "<a style='margin-left: 10px;' href='javascript:void(0);' class='btn btn-link reject linkNoPadding' apply-id='${repaymentApply.id}'>拒绝</a>{/if}" +
    "<a href='./index.php?i=9&c=entry&do=c_goods_yjdd&m=eso_sale&applyId=${repaymentApply.id}' target='_blank' " +
    "class='btn btn-link repayment-order-detail linkNoPadding' style='margin-left: 10px;' apply-id='${repaymentApply.id}'>佣金订单</a>" +
    "</th>" +
    "</tr>" +
    "{/for}{else}{for repaymentApply in repaymentApplies}" +
    "<tr><th>${repaymentApply.createdDate}</th><th>${repaymentApply.applyNo}</th>" +
    "{if isHeadCorp}<th>${repaymentApply.subCorpApplyAmount}</th><th>${repaymentApply.retailerApplyAmount}</th>{/if}" +
    "{if isSubCorp}<th>${repaymentApply.subCorpApplyAmount}</th>{/if}{if isRetailer}<th>${repaymentApply.retailerApplyAmount}</th>{/if}" +
    "<th>${repaymentApply.subCorpName}</th><th>${repaymentApply.retailerName}</th><th>${repaymentApply.status}</th><th>" +
    "<a href='./index.php?i=9&c=entry&do=c_goods_yjdd&m=eso_sale&applyId=${repaymentApply.id}' target='_blank' " +
    "class='btn btn-link repayment-order-detail linkNoPadding'' apply-id='${repaymentApply.id}'>佣金订单</a></th></tr>{/for}{/if}";