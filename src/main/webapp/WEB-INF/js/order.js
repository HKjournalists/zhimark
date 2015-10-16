$(function () {
    $(".order-close").on("click", function () {
        var orderNo = $(this).attr("order-no");
        var status = "CLOSE";
        var remark = "管理员操作订单置为关闭";
        $.post("../addons/eso_sale/c/setOrderStatus.php", {
                "orderNo": orderNo,
                "status": status,
                "remark": remark
            },
            function (data) {
                showResultDialog(data);
            },
            "json");
    });

    $(".order-fail").on("click", function () {
        var orderNo = $(this).attr("order-no");
        var status = "FAIL";
        var remark = "管理员操作订单置为失败";
        $.post("../addons/eso_sale/c/setOrderStatus.php", {
                "orderNo": orderNo,
                "status": status,
                "remark": remark
            },
            function (data) {
                showResultDialog(data);
            },
            "json");
    });

    $(".order-finish").on("click", function () {
        var orderNo = $(this).attr("order-no");
        var status = "DONE";
        var remark = "管理员操作订单置为完成";
        $.post("../addons/eso_sale/c/setOrderStatus.php", {
                "orderNo": orderNo,
                "status": status,
                "remark": remark
            },
            function (data) {
                showResultDialog(data);
            },
            "json");
    });
});

function showResultDialog(result) {
    if (result) {
        $("#order-operate-success").dialog({
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
        $("#order-operate-fail").dialog({
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
}