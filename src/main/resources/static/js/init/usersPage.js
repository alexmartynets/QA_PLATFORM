jQuery(document).ready(function ($) {

    let data = new DataUsersPage();
    let service = new DataUsersService();
    // количество карточек на странице по умолчанию 20
    let numberMedia = 5;
    let weeks;
    let currentPage = 1;
    let url;

    // map url для запроса todo можно обяденить с mapUrlNewUsers и сделать метод для получения
    let mapUrlAllUsers = new Map([
        ["reputation", "http://localhost:5557/api/user/reputation?count="],
        ["voice", "http://localhost:5557/api/user/voice?count="],
        ["editor", "http://localhost:5557/api/user/editor?count="],
        ["moderator", "http://localhost:5557/api/user/moderator"]
    ]);

    let mapUrlNewUsers = new Map([
        ["new", "http://localhost:5557/api/user/new?count="],
        ["reputation", "http://localhost:5557/api/user/new/reputation?count="]
    ]);

    // todo получения данных для стартовой страницы с кнопки поиска "репутация"
    let attr_search = $('#reputation').attr("data-search");
    url = mapUrlAllUsers.get(attr_search);
    console.log("url при загрузке страницы по кнопке репутация");
    console.log(url);

    $('#month').toggleClass("colors");

    // todo получения данных для стартовой страницы с кнопки сортировки "месяц"
    weeks = $('#month').attr("data-weeks");
    console.log("weeks при загрузке страницы по кнопке месяц");
    console.log(weeks);

    // todo кнопки в блоке search-users получаем url для запроса
    $('.search').click(function () {
        $('.search').removeClass("active");

        $('.sorting-time').removeClass("colors");
        $('#month').toggleClass("colors");

        $('#moderator').hide();
        $('#sorting-time').show();

        let text = $(this).text();

        console.log("text с кнопки поиска блока search-users");
        console.log(text);

        if (text === 'Новые участники') {

            $('.sorting-time').removeClass("colors");
            $('#new').toggleClass("colors");

            $('.shows').hide();
            $('.hides').show();

            let attr_path = $('#new').attr("data-path");
            url = mapUrlNewUsers.get(attr_path);

            weeks = $('#new').attr("data-weeks");

            // url для запроса
            console.log("data-path из sorting-time в блок search-users при нажатии кнопки Новые в блоке if");
            console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        } else if (text === 'Модераторы') {

            attr_search = $(this).attr("data-search");
            url = mapUrlAllUsers.get(attr_search);

            // url для запроса
            console.log("блок search-users при нажатии кнопки поиск Модераторы в блоке else if");
            console.log(url);

            // // получаем даннные для страницы
            // let dataMap = data.getListUsers(url);
            // service.showUsers(data, dataMap);

            $('#sorting-time').hide();
            $('#moderator').show();
            $(this).toggleClass("active");

            return;

        } else {

            $('.hides').hide();
            $('.shows').show();

            attr_search = $(this).attr("data-search");
            url = mapUrlAllUsers.get(attr_search);

            weeks = $('#month').attr("data-weeks");

            // url для запроса
            console.log("блок search-users при нажатии кнопки поиск в блоке else");
            console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        }

        // url для запроса
        console.log("блок search-users при нажатии кнопки search");
        console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        // // получаем даннные для текущей страницы
        // let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        // service.showUsers(data, dataMap);
        // service.showPagination(data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("active");
    });

    // todo кнопки в блоке sorting-time получаем число недель для запроса
    $('.sorting-time').click(function () {
        $('.sorting-time').removeClass("colors");

        let text = $(this).text();
        console.log("text с кнопки поиска блока sorting-time");
        console.log(text);

        // todo получаем url запроса для новых пользователей при сортировке
        if (text === 'по рейтингу' || text === 'по дате') {
            let attr_path = $(this).attr("data-path");
            console.log("data-path при нажатии кнопки сортировки в блоке sorting-time");
            console.log(attr_path);

            url = mapUrlNewUsers.get(attr_path);
            // url для запроса
            console.log("url для получения данных в блоков sorting-time при обработке кнопки Новые пользователи");
            console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        }

        weeks = $(this).attr("data-weeks");

        // todo получаем число недель от даты создания
        if (weeks === '-1'){
            // todo сделать ввиде метода
            let today = new Date();
            let dateCreation = new Date('2018-01-01');
            console.log("разница во времяни");
            console.log(today-dateCreation);
            let r = (today-dateCreation)/604800000;
            console.log(Math.ceil(r));
        }

        console.log("блок sorting-time количество weeks при нажатии кнопки sorting");
        console.log(weeks);

        // url для запроса
        console.log("url для получения данных в конце блока sorting-time");
        console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        // // получаем даннные для текущей страницы
        // let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        // service.showUsers(data, dataMap);
        // service.showPagination(data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("colors");
    });


    // todo получаем даннные для стартовой страницы по комбинации кнопок репутация + месяц
    console.log("url для получения данных для страницы старт после блоков");
    console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

    // let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
    // service.showUsers(data, dataMap);
    // service.showPagination(data, dataMap, numberMedia, currentPage);

    /*блок кода для динамического изменения данных*/
    $("body").on("click", ".page-link", function () {
        let currentPage = $(this).text();

        if (currentPage === '...') {
            return;
        }

        // получаем даннные для текущей страницы
        console.log("url для получения данных для страницы пагинация после блоков");
        console.log(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);

        // let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        // service.showUsers(data, dataMap);
        // service.showPagination(data, dataMap, numberMedia, currentPage);
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