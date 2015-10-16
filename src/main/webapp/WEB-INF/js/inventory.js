$(function () {
    $(".update").on("click", function () {
        $.post("localhost://8080/service/inventory/batch-update", {}, function (data) {
            if (data.result) {
                alert("更新库存成功");
            }
        }, "json");
    })
});
