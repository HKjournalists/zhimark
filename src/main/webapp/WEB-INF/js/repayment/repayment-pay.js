$(function () {

    loadRepaymentApplies(1);

    $(".search").on("click", function () {
        loadRepaymentApplies(1);
    });

    $(".nochoice").on("click", function () {
        $(this).addClass("nochoiceBack").parent().siblings().children("div.nochoice").removeClass("nochoiceBack");
        loadRepaymentApplies(1);
    });

});

function loadRepaymentApplies(currentPage) {
    //clearPlanStatus();
    var subCorpId = $(".subCorpSelect").val();
    var retailerCorpId = $(".retailerCorpSelect").val();
    if (subCorpId == "0" || subCorpId == $(".headCorpId").val()) {
        if (retailerCorpId && retailerCorpId != "0") {
            showRepaymentApplies(getTimeSpanParam() + "&applicantId=" + retailerCorpId + "&currentPage=" + currentPage + "&status=1");
        } else {
            showRepaymentApplies(getTimeSpanParam() + "&currentPage=" + currentPage + "&status=1");
        }
    } else {
        if (retailerCorpId == "0") {
            showRepaymentApplies("&subCorpId=" + subCorpId + getTimeSpanParam() + "&currentPage=" + currentPage + "&status=1");
        } else {
            showRepaymentApplies("&subCorpId=" + subCorpId + "&applicantId=" + retailerCorpId + getTimeSpanParam() + "&currentPage=" + currentPage + "&status=1");
        }
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
            var mergedHtml = TrimPath.processDOMTemplate("repayment-applies-pay", {"repaymentApplies": repaymentApplies});
            $(".show_repayment_applies").html(mergedHtml);
            pagination.pagination(paginationInfo, $(".pagination"), loadRepaymentApplies);
            //$(".alipay-pay").on("click", function () {
            //
            //});
        }
    });
}

function getHeadCorpId() {
    return $(".headCorpId").val();
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