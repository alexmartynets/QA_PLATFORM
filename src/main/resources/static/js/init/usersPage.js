jQuery(document).ready(function ($) {

    // скрыть кнопки
    $(".hides").hide();

    let data = new DataUsersPage();
    let service = new DataUsersService();
    // количество карточек на странице
    let numberMedia = 5;
    let weeks;
    let currentPage = 1;
    let url;

    // map url для запроса
    let mapUrl = new Map([
        ["new", "http://localhost:5557/api/user/new?count="],
        ["reputation", "http://localhost:5557/api/user/reputation?count="],
        ["voice", "http://localhost:5557/api/user/voice?count="],
        ["editor", "http://localhost:5557/api/user/editor?count="],
        ["role", "http://localhost:5557/api/user/role?role=MODERATOR"]
    ]);

    // todo
    let attr_search = $('#button-users').attr("data-search");
    url = mapUrl.get(attr_search);
    console.log("url при загрузке страницы");
    console.log(url);
    // todo
    weeks = $('#all').attr("data-weeks");
    console.log("weeks при загрузке страницы");
    console.log(weeks);

    // button search-users
    $('.search').click(function () {
        $('.search').removeClass("active");

        let text = $(this).text();
        if (text === 'Новые участники') {
            $(".shows").hide();
            $(".hides").show();
            // $('#new').toggleClass("colors");

            attr_search = $(this).attr("data-search");
            url = mapUrl.get(attr_search);

            weeks = $('#new').attr("data-weeks");

            // url для запроса
            console.log("блок search-users при нажатии кнопки блок if");
            console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        } else {
            $('.hides').hide();
            $('.shows').show();

            attr_search = $(this).attr("data-search");
            url = mapUrl.get(attr_search);

            weeks = $('#all').attr("data-weeks");

            // url для запроса
            console.log("блок search-users при нажатии кнопки блок else");
            console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        }

        // url для запроса
        console.log("блок search-users при нажатии кнопки");
        console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        // получаем даннные для текущей страницы
        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        service.showUsers(data, dataMap);
        service.showPagination(data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("active");
    });

    // a search-time
    $('.search-time').click(function () {
        $('.search-time').removeClass("colors");

        weeks = $(this).attr("data-weeks");

        console.log("блок search-time weeks при нажатии кнопки");
        console.log(weeks);

        // url для запроса
        console.log("url для получения данных в блоков search-time");
        console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        // получаем даннные для текущей страницы
        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        service.showUsers(data, dataMap);
        service.showPagination(data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("colors");
    });


    // получаем даннные для 1 страницы
    let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
    console.log("url для получения данных для страницы старт после блоков");
    console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

    service.showUsers(data, dataMap);
    service.showPagination(data, dataMap, numberMedia, currentPage);

    /*блок кода для динамического изменения данных*/
    $("body").on("click", ".page-link", function () {
        let currentPage = $(this).text();

        if (currentPage === '...') {
            return;
        }

        // получаем даннные для текущей страницы
        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        console.log("url для получения данных для страницы пагинация после блоков");
        console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        service.showUsers(data, dataMap);
        service.showPagination(data, dataMap, numberMedia, currentPage);
    });


//====================================================================================================================//

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