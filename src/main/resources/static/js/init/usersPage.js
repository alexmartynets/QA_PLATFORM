jQuery(document).ready(function ($) {
    $('#month').toggleClass("colors");

    let media = new MediaFactory();
    let data = new DataUsersPage();
    let service = new DataUsersService();
    let numberMedia = 5; // количество карточек на странице по умолчанию 20
    let weeks;
    let currentPage = 1;
    let url;

    // todo получения данных для стартовой страницы с кнопки поиска "Репутация"
    let attr_search = $('#reputation').attr("data-search");
    url = service.getUrl(attr_search);

    // todo получения данных для стартовой страницы с кнопки сортировки "Месяц"
    weeks = $('#month').attr("data-weeks");

    // todo кнопки в блоке search-users "ПОИСК" получаем url для запроса
    $('.search').click(function () {
        $('.search').removeClass("active");

        $("#pagination").show();

        $('.sorting-time').removeClass("colors");
        $('#month').toggleClass("colors");

        $('#moderator').hide();
        $('#sorting-time').show();

        let text = $(this).text();

        if (text === 'Новые участники') {
            $('.sorting-time').removeClass("colors");
            $('#new').toggleClass("colors");

            $('.shows').hide();
            $('.hides').show();

            let attr_path = $('#new').attr("data-path");
            url = service.getUrl(attr_path);
            weeks = $('#new').attr("data-weeks");

        } else if (text === 'Модераторы') {
            attr_search = $(this).attr("data-search");
            url = service.getUrl(attr_search);

            // получаем даннные для страницы
            let dataMap = data.getListUsers(url);
            service.showUsers(media.getMediaList(attr_search, dataMap.get("list")));

            $("#pagination").hide();
            $('#sorting-time').hide();
            $('#moderator').show();
            $(this).toggleClass("active");

            return;

        } else {
            $('.hides').hide();
            $('.shows').show();

            attr_search = $(this).attr("data-search");
            url = service.getUrl(attr_search);
            weeks = $('#month').attr("data-weeks");
        }

        // получаем даннные для текущей страницы
        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        service.showUsers(media.getMediaList(attr_search, dataMap.get("list")));
        service.showPagination(media, data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("active");
    });

    // todo кнопки в блоке sorting-time "СОРТИРОВКА" получаем число недель для запроса
    $('.sorting-time').click(function () {
        $('.sorting-time').removeClass("colors");
        let text = $(this).text();
        weeks = $(this).attr("data-weeks");

        // todo получаем url запроса для "НОВЫЕ УЧАСТНИКИ"
        if (text === 'по рейтингу' || text === 'по дате') {

            let attr_path = $(this).attr("data-path");
            url = service.getUrl(attr_path);
        }

        // todo получаем число недель от даты создания приложения
        if (weeks === '-1') {
            weeks = service.getCountWeeksSinceCreation();
        }

        // получаем даннные для текущей страницы
        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        service.showUsers(media.getMediaList(attr_search, dataMap.get("list")));
        service.showPagination(media, data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("colors");
    });

    let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
    service.showUsers(media.getMediaList(attr_search, dataMap.get("list")));
    service.showPagination(media, data, dataMap, numberMedia, currentPage);

    // todo блок кода для динамического изменения данных на странице
    $("body").on("click", ".page-link", function () {
        let currentPage = $(this).text();

        if (currentPage === '...') {
            return;
        }

        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        service.showUsers(media.getMediaList(attr_search, dataMap.get("list")));
        service.showPagination(media, data, dataMap, numberMedia, currentPage);
    });


//====================================================================================================================//


    // обработка search на странице users
    let input = document.querySelector("#search");
    input.addEventListener("input", function (e) {
        let name = e.target.value;
        $('#moderator').hide();
        $("#pagination").show();

        let currentPage = 1;
        let url_search = service.getUrl("search");
        let dataMap = data.getListUsers(url_search + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);

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

        $('.sorting-time').click(function () {
            $('.sorting-time').removeClass("colors");

            let text = $(this).text();
            // console.log("text с кнопки поиска блока sorting-time SEARCH");
            // console.log(text);

            weeks = $(this).attr("data-weeks");
            // console.log("weeks с кнопки поиска блока sorting-time SEARCH");
            // console.log(weeks);

            // todo получаем число недель от даты создания приложения
            if (weeks === '-1') {
                weeks = service.getCountWeeksSinceCreation();
                // console.log("блок sorting-time количество weeks при нажатии кнопки Все SEARCH");
                // console.log(weeks);
            }

            // получаем даннные для текущей страницы
            let dataMap = data.getListUsers(url_search + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);
            service.showUsers(data, dataMap);
            service.showPagination(data, dataMap, numberMedia, currentPage);

            // todo получаем даннные для текущей страницы
            // console.log("url_search для получения данных блок sorting-time SEARCH");
            // console.log(url_search + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);

            $(this).toggleClass("colors");
        });

        // блок кода для динамического изменения данных для search
        $("body").on("click", ".page-link", function () {
            let currentPage = $(this).text();

            if (currentPage === '...') {
                return;
            }
            let url_search = service.getUrl("search");
            let dataMap = data.getListUsers(url_search + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);
            service.showUsers(data, dataMap);
            service.showPagination(data, dataMap, numberMedia, currentPage);

            // todo получаем даннные для текущей страницы
            // console.log("url_search для получения данных пагинация блок sorting-time SEARCH");
            // console.log(url_search + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);
        });
        // document.getElementById("search").value = "";
    });
});