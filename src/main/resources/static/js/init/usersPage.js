jQuery(document).ready(function ($) {

    let data = new DataUsersPage();
    let service = new DataUsersService();
    // количество карточек на странице
    let numberMedia = 20;
    let weeks = 4;

    //  http://localhost:5557/api/user/reputation?count=20&page=1&weeks=4
    let url_list = "http://localhost:5557/api/user/reputation?count=" + numberMedia + "&page=";
    let currentPage = 1;

    // получаем даннные для 1 страницы
    let dataMap = data.getListUsers(url_list + currentPage + "&weeks=" + weeks);

    service.showUsers(data, dataMap);
    service.showPagination(data, dataMap, numberMedia, currentPage);

    /*блок кода для динамического изменения данных*/
    $("body").on("click", ".page-link", function () {
        let currentPage = $(this).text();

        if (currentPage === '...') {
            return;
        }

        // получаем даннные для текущей страницы
        let dataMap = data.getListUsers(url_list + currentPage + "&weeks=" + weeks);

        service.showUsers(data, dataMap);
        service.showPagination(data, dataMap, numberMedia, currentPage);
    });

    // map url для запроса
    let mapUrl = new Map([
        ["new", "http://localhost:5557/api/user/new?count=" + numberMedia + "&page=" + currentPage + "&weeks=" + weeks],
        ["reputation", "http://localhost:5557/api/user/reputation?count=" + numberMedia + "&page=" + currentPage + "&weeks=" + weeks],
        ["voice", "http://localhost:5557/api/user/voice?count=" + numberMedia + "&page=" + currentPage + "&weeks=" + weeks],
        ["editor", "http://localhost:5557/api/user/editor?count=" + numberMedia + "&page=" + currentPage + "&weeks=" + weeks],
        ["role", "http://localhost:5557/api/user/role?role=MODERATOR"]
    ]);

    let text_attr_search;
    let text_attr_weeks;

    // active button search-users
    $('.search').click(function () {
        $('.search').removeClass("active");

        text_attr_search = $(this).attr("data-search");
        $(this).toggleClass("active");

    });

    // colors a search-time users
    $('.search-time').click(function () {
        $('.search-time').removeClass("colors");

        text_attr_weeks = $(this).attr("data-search-weeks");

        let url = mapUrl.get(text_attr_search);

        console.log(url);
        console.log(url + text_attr_weeks);

        $(this).toggleClass("colors");

    });


    // обработка search на странице users
    let input = document.querySelector("#search");
    input.addEventListener("input", function (e) {
        let name = e.target.value;
        $("#pagination").show();

        let currentPage = 1;

        let url_search = "http://localhost:5557/api/user/search?count=" + numberMedia + "&page=" + currentPage + "&name?name=" + name;

        // получаем даннные для 1 страницы
        let dataMap = data.getListUsers(url_search);

        if (dataMap.get("list").length === 1) {
            location.assign("http://localhost:5557/profile");
            return;
        }

        if (dataMap.get("list").length === 0) {
            let message = document.createElement("div");
            message.textContent = "Участник с таким именем не наден";
            $("#users").html($(message));
            $("#pagination").hide();
        } else {
            // если пользователи найдены
            service.showPagination(data, dataMap, numberMedia, currentPage);
            service.showUsers(data, dataMap);
        }

        /*блок кода для динамического изменения данных для search*/
        $("body").on("click", ".page-link", function () {
            let currentPage = $(this).text();

            if (currentPage === '...') {
                return;
            }
            let url_search = "http://localhost:5557/api/user/search?count=" + numberMedia + "&page=" + currentPage + "&name?name=" + name;
            // получаем даннные для текущей страницы
            let dataMap = data.getListUsers(url_search);

            service.showUsers(data, dataMap);
            service.showPagination(data, dataMap, numberMedia, currentPage);
        });
    });

});

// let url_new = "http://localhost:5557/api/user/new?count=" + numberMedia + "&page=" + currentPage + "&weeks=" + weeks;
//
// let url_reputation = "http://localhost:5557/api/user/reputation?count=" + numberMedia + "&page=" + currentPage + "&weeks=" + weeks;
//
// let url_voice = "http://localhost:5557/api/user/voice?count=" + numberMedia + "&page=" + currentPage + "&weeks=" + weeks;
//
// let url_editor = "http://localhost:5557/api/user/editor?count=" + numberMedia + "&page=" + currentPage + "&weeks=" + weeks;
//
// let url_role = "http://localhost:5557/api/user/role?role=" + role;