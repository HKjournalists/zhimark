var imageRecycleShow = function () {
    function moving(images, secondImages, length) {
        $(images).each(
            function () {
                animation($(this), length);
            }
        );
        $(secondImages).each(
            function () {
                animation($(this), length);
            }
        );
    }

    function animation(dom, length) {
        $(dom).animate({"left": length}, 4000, function () {
            var img = $("<img src=" + $(dom).attr("src") + " class=" + $(dom).attr("class") + " />");
            $(dom).parent().append(img);
            $(dom).remove();
            animation($(img),length);
        });
    }

    function show(length) {
        var images = $("img.recycle");
        $(images).each(function () {
            $(this).parent().append("<img src=" + $(this).attr("src") + " class='temp-append' />");
        });
        moving(images, $("img.temp-append"), length);
    }

    return {recycle: show}
}();