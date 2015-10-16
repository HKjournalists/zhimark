$(function () {
    var handlerId = "handlerId";
    var destination = "queue://queue";
    var amq = org.activemq.Amq;
    amq.removeListener(handlerId);
    amq.init(
        {
            uri: 'http://localhost:8080/amq',
            logging: true,
            timeout: 20,
            clientId: (new Date()).getTime().toString()
        }
    );

    var msg = "<msg type='common'>"
        + "<id>msg1</id>"
        + "<content>This is test content</content>"
        + "</msg>";

    amq.sendMessage(destination, msg);

    $(".send").click(function () {
        $.getJSON("http://localhost:8080/test/send-message?message=hello", function (data) {
            console.log(data)
        });
    });

    var myHandler =
    {
        rcvMessage: function (message) {
            $(".show").html("<h1>" + message + "</h1>");
            //console.log(message);
        }
    };
    amq.addListener(handlerId, destination, myHandler.rcvMessage);
});




