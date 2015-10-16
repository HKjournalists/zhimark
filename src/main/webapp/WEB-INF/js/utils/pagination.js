var pagination = function () {
    function pagination(paginationInfo, targetDom, callBack) {
        var totalCount = paginationInfo.totalCount;
        var totalPage = paginationInfo.totalPage;
        var currentPage = paginationInfo.currentPage;
        var prePage = paginationInfo.prePage;
        var nextPage = paginationInfo.nextPage;
        var pageNumbers = [];
        if (totalPage <= 10) {
            for (var index = 1; index <= totalPage; index++) {
                pageNumbers.push(index);
            }
        } else {
            if (currentPage < 7) {
                for (var index = 1; index <= 10; index++) {
                    pageNumbers.push(index);
                }
            } else {
                var scalar = currentPage + 6;
                var startPage = scalar > totalPage ? totalPage - 9 : currentPage - 3;
                var endPage = scalar > totalPage ? totalPage : scalar;
                for (var index = startPage; index <= endPage; index++) {
                    pageNumbers.push(index);
                }
            }
        }
        var paginationData = {"pageNumbers": pageNumbers, "paginationInfo": paginationInfo};

        var mergedHtml = TrimPath.parseTemplate(paginationBar).process(paginationData);
        $(targetDom).html(mergedHtml);
        if (callBack) {
            $(".pageNumber").on("click", function () {
                callBack($(this).attr("pageNumber"));
            });
            $(".firstPage").on("click", function () {
                callBack(1);
            });
            $(".lastPage").on("click", function () {
                callBack(totalPage);
            });
            $(".prePage").on("click", function () {
                callBack(prePage);
            });
            $(".nextPage").on("click", function () {
                callBack(nextPage);
            });
        }
    }

    return {pagination: pagination};
}();

var paginationBar = "<div>" +
    "<ul class='pagination pagination-centered'>" +
    "<li class=''><a class='firstPage' href='javascript:void(0);'>首页</a></li>" +
    "<li class=''><a class='prePage' href='javascript:void(0);'>上一页</a></li>" +
    "{for pageNumber in pageNumbers}" +
    "{if pageNumber==paginationInfo.currentPage} <li class='active'>" +
    "<a href='javascript:void(0);' class='pageNumber' pageNumber='${pageNumber}'>${pageNumber}</a>" +
    "</li>" +
    "{else}<li class=''><a href='javascript:void(0);' class='pageNumber' pageNumber='${pageNumber}'>${pageNumber}</a>" +
    "{/if}{/for}" +
    "<li class=''><a class='nextPage' href='javascript:void(0);'>下一页</a></li>" +
    "<li class=''><a class='lastPage' href='javascript:void(0);'>尾页</a></li></ul>" +
    "</div>" + "总共:${paginationInfo.totalCount}条记录,共${paginationInfo.totalPage}页,每页10条";