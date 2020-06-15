jQuery(document).ready(function ($) {
    $('#month').toggleClass("colors");

    let media = new MediaFactory();
    let data = new DataUsersPage();
    let service = new DataUsersService();
    let numberMedia = 5; // количество карточек на странице по умолчанию 20
    let weeks;
    let currentPage = 1;
    let url;

    let attr_search = $('#reputation').attr("data-search");
    url = service.getUrl(attr_search);
    weeks = $('#month').attr("data-weeks");

    // обработка кнопок в блоке "ПОИСК" получаем url для запроса
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

            attr_search = $(this).attr("data-search");
            let attr_path = $('#new').attr("data-path");
            url = service.getUrl(attr_path);
            weeks = $('#new').attr("data-weeks");

        } else if (text === 'Модераторы') {
            attr_search = $(this).attr("data-search");
            url = service.getUrl(attr_search);
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

        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        service.showUsers(media.getMediaList(attr_search, dataMap.get("list")));
        service.showPagination(media, data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("active");
    });

    // обработка кнопок в блоке "СОРТИРОВКА" получаем число недель для запроса
    $('.sorting-time').click(function () {
        $('.sorting-time').removeClass("colors");
        let text = $(this).text();
        weeks = $(this).attr("data-weeks");

        if (text === 'по рейтингу' || text === 'по дате') {
            let attr_path = $(this).attr("data-path");
            url = service.getUrl(attr_path);
            attr_search = $(this).attr("data-search");
        }

        // получаем число недель от даты создания приложения
        if (weeks === '-1') {
            weeks = service.getCountWeeksSinceCreation();
        }

        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        service.showUsers(media.getMediaList(attr_search, dataMap.get("list")));
        service.showPagination(media, data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("colors");
    });

    let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
    service.showUsers(media.getMediaList(attr_search, dataMap.get("list")));
    service.showPagination(media, data, dataMap, numberMedia, currentPage);

    // блок кода для динамического изменения данных на странице
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

            service.showUsers(media.getMediaList("reputation", dataMap.get("list")));
            service.showPagination(media, data, dataMap, numberMedia, currentPage);
        }

        $('.sorting-time').click(function () {
            $('.sorting-time').removeClass("colors");

            weeks = $(this).attr("data-weeks");

            if (weeks === '-1') {
                weeks = service.getCountWeeksSinceCreation();
            }

            let dataMap = data.getListUsers(url_search + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);
            service.showUsers(media.getMediaList("reputation", dataMap.get("list")));
            service.showPagination(media, data, dataMap, numberMedia, currentPage);

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
            service.showUsers(media.getMediaList("reputation", dataMap.get("list")));
            service.showPagination(media, data, dataMap, numberMedia, currentPage);
        });
    });
});