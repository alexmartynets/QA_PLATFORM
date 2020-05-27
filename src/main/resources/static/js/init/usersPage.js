jQuery(document).ready(function ($) {

    let data = new DataUsersPagination();
    // количество карточек на странице
    let numberMedia = 5;
    // URL запроса page
    let url_list = "http://localhost:5557/api/user/" + numberMedia + "/page/";

    // получаем даннные
    let dadaMap = data.getListUsers(url_list + 1);
    //  пагинация для 1 страницы
    let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
    let listButtonPagination = data.generateRangePagination(1, countOfPages);
    let listLi = data.mapperMediaPagination(listButtonPagination);
    $("#pagination").html($(listLi));

    //  создаем media carts с пользователями
    let listUsersForPage = dadaMap.get("list");
    let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
    $("#users").html($(listMediaUsers));

    /*блок кода для динамического изменения данных*/
    $("body").on("click", ".page-link", function () {
        let currentPage = $(this).text();

        // получаем даннные
        let dadaMap = data.getListUsers(url_list + currentPage);

        //  создаем media carts с пользователями
        let listUsersForPage = dadaMap.get("list");
        let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
        $("#users").html($(listMediaUsers));

        //  пагинация
        let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
        let listButtonPagination = data.generateRangePagination(currentPage, countOfPages);
        let listLi = data.mapperMediaPagination(listButtonPagination);
        $("#pagination").html($(listLi));
    });

//<---------------------------------------------------------------------------------------------->//

    // обработка search users
    let input = document.querySelector("#search");
    input.addEventListener("input", function (e) {
        let name = e.target.value;
        // console.log(name);
        $("#pagination").show();
        let currentPage = 1;
        let url_search = "http://localhost:5557/api/user/name?name=" + name + "&count=" + numberMedia + "&page=" + currentPage;

        // получаем даннные
        let dadaMap = data.getListUsers(url_search);
        // console.log(dadaMap);

        if (dadaMap.get("list").length === 1){
            location.assign("http://localhost:5557/profile");
        }

        if (dadaMap.get("list").length === 0){
            let message = document.createElement("div");
            message.textContent = "Участник с таким именем не наден";
            $("#users").html($(message));
            $("#pagination").hide();
        }else {

            //  пагинация для 1 страницы
            let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
            let listButtonPagination = data.generateRangePagination(1, countOfPages);
            let listLi = data.mapperMediaPagination(listButtonPagination);
            $("#pagination").html($(listLi));

            //  создаем media carts с пользователями
            let listUsersForPage = dadaMap.get("list");
            let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
            $("#users").html($(listMediaUsers));
        }

        /*блок кода для динамического изменения данных для search*/
        $("body").on("click", ".page-link", function () {
            let currentPage = $(this).text();
            // console.log("В обрабочике страница:  " + currentPage);
            let url_search = "http://localhost:5557/api/user/name?name=" + name + "&count=" + numberMedia + "&page=" + currentPage;
            // console.log("URL запроса:  " + url_search);
            let dadaMap = data.getListUsers(url_search);
            // console.log(dadaMap);

            //  создаем media carts с пользователями
            let listUsersForPage = dadaMap.get("list");
            let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
            $("#users").html($(listMediaUsers));

            //  пагинация
            let countOfPages = data.getNumberOfPages(dadaMap.get("count"), numberMedia);
            let listButtonPagination = data.generateRangePagination(currentPage, countOfPages);
            let listLi = data.mapperMediaPagination(listButtonPagination);
            $("#pagination").html($(listLi));
        });
    });

});
