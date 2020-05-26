jQuery(document).ready(function ($) {

    let data = new DataUsersPagination();
    // начальная страница
    let currentPage = 1;
    // количество карточек на странице
    let numberMedia = 5;

    /*блок кода для первой страницы*/
    let dadaMap = data.getListUsersForPagination(numberMedia, currentPage);

    let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
    let listUsersForPage = dadaMap.get("list");
    let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
    $("#users").html($(listMediaUsers));

    let listButtonPagination = data.generateRangePagination(currentPage, countOfPages);
    let listLi = data.mapperMediaPagination(listButtonPagination);
    $("#pagination").html($(listLi));

    /*блок кода для динамического изменения данных*/
    $("body").on("click", ".page-link", function () {
        let page = $(this).text();
        // console.log("страница:  " + page);

        let dadaMap = data.getListUsersForPagination(numberMedia, page);

        let listUsersForPage = dadaMap.get("list");
        let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
        $("#users").html($(listMediaUsers));

        let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
        let listButtonPagination = data.generateRangePagination(page, countOfPages);
        let listLi = data.mapperMediaPagination(listButtonPagination);
        $("#pagination").html($(listLi));
    });

});