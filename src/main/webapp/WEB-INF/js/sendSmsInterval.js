var countdown;
function setCountDown(val,interval) {
    if (countdown == 0) {
        $(val).removeAttr("disabled");
        $("#btn").html("免费获取验证码");
        clearInterval(interval);
    } else {
        $(val).attr("disabled", true);
        $("#btn").html("重新发送(" + countdown + ")");
        countdown--;
    }
}
function sendSms(val) {
    var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
    var mobileNo = $("#mobile").val();
    if ($(val).attr("disabled") != "disabled") {
        if (reg.test(mobileNo)) {
            $.post("../addons/eso_sale/c/msg.php",
                {
                    mobileNo: mobileNo,
                    url: "http://121.40.113.130:8080/gateway/service/sms/send-validation",
                    post: "yes"
                },
                function (data, status) {
                    var jsonResult = eval('(' + data + ')');
                    alert(jsonResult.errorMessage);
                });
            countdown = 59;
            var interval = setInterval(function () {
                setCountDown(val,interval)
            }, 1000);
        } else {
            alert("手机号码不正确");
        }
    }
}