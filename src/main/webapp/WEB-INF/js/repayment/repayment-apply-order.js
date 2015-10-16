$(function () {
    loadAppliedRepaymentPlans(1);
});

function getApplyId() {
    var queryString = $(".apply-param").val();
    var queryArray = queryString.split("&");
    for (var index = 0; index < queryArray.length; index++) {
        if (queryArray[index].indexOf("applyId") >= 0) {
            return queryArray[index].split("=")[1];
        }
    }
    return "";
}

function loadAppliedRepaymentPlans(currentPage) {
    $(".show_repayment_plans").html("没有返佣金计划");
    $(".pagination").html("");
    var isHeadCorp = $(".role").val() == "1";
    var isSubCorp = $(".role").val() == "2";
    var isRetailer = $(".role").val() == "3";
    $.getJSON("http://121.40.113.130:8080/gateway/service/repayment-plan/query-applied-plans?callback=?",
        {"applyId": getApplyId(), "currentPage": currentPage}, function (data) {
            if (data.totalCount > 0) {
                var paginationInfo = {
                    "totalCount": data.totalCount,
                    "totalPage": data.totalPage,
                    "currentPage": data.currentPage,
                    "prePage": data.prePage,
                    "nextPage": data.nextPage
                };
                var mergedHtml = TrimPath.parseTemplate(orderListContent).process(
                    {
                        "repaymentPlans": data.data,
                        "isHeadCorp": isHeadCorp,
                        "isSubCorp": isSubCorp,
                        "isRetailer": isRetailer
                    });
                $(".show_repayment_plans").html(mergedHtml);
                pagination.pagination(paginationInfo, $(".pagination"), loadAppliedRepaymentPlans);
            }
        });
}

var orderListContent = "{for repaymentPlan in repaymentPlans}" +
    "<tr>" +
    "<th>${repaymentPlan.createdTime}</th>" +
    "<th>${repaymentPlan.orderNo}</th>" +
    "{if isHeadCorp}" +
    "<th>${repaymentPlan.subCorpProfit}</th>" +
    "<th>${repaymentPlan.retailerProfit}</th>" +
    "{/if}" +
    "{if isSubCorp}" +
    "<th>${repaymentPlan.subCorpProfit}</th>" +
    "{/if}" +
    "{if isRetailer}" +
    "<th>${repaymentPlan.retailerProfit}</th>" +
    "{/if}" +
    "<th>${repaymentPlan.belongsToCorp}</th>" +
    "<th>${repaymentPlan.status}</th>" +
    "<th>" +
    "<a href='./index.php?i=9&c=entry&op=detail&id=${repaymentPlan.orderId}&do=c_order&m=eso_sale'" +
    " class='btn btn-link linkNoPadding' target='_blank'>订单详情</a></th>" +
    "</tr>" +
    "{/for}"