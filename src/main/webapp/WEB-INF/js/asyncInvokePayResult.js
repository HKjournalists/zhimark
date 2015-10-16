var isPaid = false;
$(function () {
    var countDown = 600;//3分钟后就自动跳转认为支付了

    $(".buyButton").on("click", function () {
        var intervalId = setInterval(function () {
            queryPayResult(intervalId);
        }, 1000);
    });

    function queryPayResult(interval) {
        var orderId = $(".orderId").val();
        //只有当order创建后并返回到前台input,才间隔查询是否支付
        if (orderId != null && orderId != "") {
            if (countDown > 0) {
                if (!isPaid) {
                    $.getJSON("../addons/eso_sale/template/zdj/queryPayResult.php", {
                            "orderId": orderId,
                            "payType": getPayTypeCode()
                        },
                        function (data) {
                            if (data.result) {
                                showPaySuccess(interval);
                                isPaid = data.result;
                            }
                        });
                }
            } else {
                closePayQrImage(interval);
            }
            countDown--;
        }
    }

    function getPayTypeCode() {
        var payType;
        var payTypeValue = $("input[name=pay]:checked").val();
        if (payTypeValue == "alipayjw") {
            payType = "4";//stands for alipay
        } else if (payTypeValue == "llzf") {
            payType = "11";//stands for lian_lian pay
        } else {
            payType = "2";//stands for uncertain pay channel
        }
        return payType;
    }
});

function closePayQrImage(interval) {
    clearInterval(interval);
    $("#tipnr").html("<p align='center'>查询支付结果超时</p>");
    //window.location.href = $(".colorWhite").attr("href");
}

function showPaySuccess(interval) {
    clearInterval(interval);
    $("#tipnr").html("<p align='center'>支付成功</p>");
    //window.location.href = $(".colorWhite").attr("href");
}





