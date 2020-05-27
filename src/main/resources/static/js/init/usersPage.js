jQuery(document).ready(function ($) {

    let data = new DataUsersPagination();
    // количество карточек на странице
    let numberMedia = 5;
    // URL запроса page
    let url_list = "http://localhost:5557/api/user/" + numberMedia + "/page/";

    // получаем даннные для 1 страницы
    let dadaMap = data.getListUsers(url_list + 1);

    let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
    let listButtonPagination = data.generateRangePagination(1, countOfPages);
    let listLi = data.mapperMediaPagination(listButtonPagination);
    $("#pagination").html($(listLi));

    let listUsersForPage = dadaMap.get("list");
    let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
    $("#users").html($(listMediaUsers));

    /*блок кода для динамического изменения данных*/
    $("body").on("click", ".page-link", function () {
        let currentPage = $(this).text();

        // получаем даннные для текущей страницы
        let dadaMap = data.getListUsers(url_list + currentPage);

        let listUsersForPage = dadaMap.get("list");
        let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
        $("#users").html($(listMediaUsers));

        let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
        let listButtonPagination = data.generateRangePagination(currentPage, countOfPages);
        let listLi = data.mapperMediaPagination(listButtonPagination);
        $("#pagination").html($(listLi));
    });

//<---------------------------------------------------------------------------------------------->//

    // обработка search на странице users
    let input = document.querySelector("#search");
    input.addEventListener("input", function (e) {
        let name = e.target.value;
        $("#pagination").show();

        let url_search = "http://localhost:5557/api/user/name?name=" + name + "&count=" + numberMedia + "&page=" + 1;

        // получаем даннные для 1 страницы
        let dadaMap = data.getListUsers(url_search);

        if (dadaMap.get("list").length === 1){
            location.assign("http://localhost:5557/profile");
        }

        if (dadaMap.get("list").length === 0){
            let message = document.createElement("div");
            message.textContent = "Участник с таким именем не наден";
            $("#users").html($(message));
            $("#pagination").hide();
        }else {

            let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
            let listButtonPagination = data.generateRangePagination(1, countOfPages);
            let listLi = data.mapperMediaPagination(listButtonPagination);
            $("#pagination").html($(listLi));

            let listUsersForPage = dadaMap.get("list");
            let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
            $("#users").html($(listMediaUsers));
        }

        /*блок кода для динамического изменения данных для search*/
        $("body").on("click", ".page-link", function () {
            let currentPage = $(this).text();
            let url_search = "http://localhost:5557/api/user/name?name=" + name + "&count=" + numberMedia + "&page=" + currentPage;
            // получаем даннные для текущей страницы
            let dadaMap = data.getListUsers(url_search);

            let listUsersForPage = dadaMap.get("list");
            let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
            $("#users").html($(listMediaUsers));

            let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
            let listButtonPagination = data.generateRangePagination(currentPage, countOfPages);
            let listLi = data.mapperMediaPagination(listButtonPagination);
            $("#pagination").html($(listLi));
        });
    });

});
