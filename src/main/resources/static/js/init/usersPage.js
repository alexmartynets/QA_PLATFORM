jQuery(document).ready(function ($) {
    $('#month').toggleClass("colors");


    let media = new MediaFactory();
    let data = new DataUsersPage();
    let service = new DataUsersService();
    let numberMedia = 5; // количество карточек на странице по умолчанию 20
    let weeks;
    let currentPage = 1;
    let url;

    // получаем mediaList для страницы
    let attr_search = $('#reputation').attr("data-search");
    // получаем url для запроса
    url = service.getUrl(attr_search);
    //получаем периуд для запроса
    weeks = $('#month').attr("data-weeks");

    // обработка кнопок в блоке "ПОИСК" по участникам
    $('.search').click(function () {
        $('.search').removeClass("active");

        $('#find').val('');

        $("#pagination").show();
        $('.sorting-time').removeClass("colors");
        $('#month').toggleClass("colors");
        $('#moderator').hide();
        $('#sorting-time').show();

        let values = $(this).val();
        // console.log(values);
        if (values === 'new') {
            $('.sorting-time').removeClass("colors");
            $('#new').toggleClass("colors");

            $("#sorting-time-search").hide();
            $("#pagination-search").hide();

            $('.shows').hide();
            $('.shows-hides').show();

            attr_search = $(this).attr("data-search");
            let attr_path = $('#new').attr("data-path");
            url = service.getUrl(attr_path);
            weeks = $('#new').attr("data-weeks");

        } else if (values === 'moderator') {
            attr_search = $(this).attr("data-search");
            url = service.getUrl(attr_search);
            let dataMap = data.getListUsers(url);
            let list = media.getMediaList(attr_search, dataMap.get("list"));
            service.showUsers(list);

            $("#sorting-time-search").hide();
            $("#pagination-search").hide();

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
        // данные для страницы
        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        let list = media.getMediaList(attr_search, dataMap.get("list"));
        service.showUsers(list);
        service.showPagination(media, data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("active");
    });

    // обработка кнопок в блоке "СОРТИРОВКА" по времени
    $('.sorting-time').click(function () {
        $('.sorting-time').removeClass("colors");
        let text = $(this).text();
        weeks = $(this).attr("data-weeks");
        // console.log(text);
        // console.log(weeks);
        if (text === 'по рейтингу' || text === 'по дате') {
            let attr_path = $(this).attr("data-path");
            url = service.getUrl(attr_path);
            attr_search = $(this).attr("data-search");
        }

        // получаем число недель от даты создания приложения
        if (weeks === '-1') {
            weeks = service.getCountWeeksDateCreation();
        }

        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        let list = media.getMediaList(attr_search, dataMap.get("list"));
        service.showUsers(list);
        service.showPagination(media, data, dataMap, numberMedia, currentPage);

        $(this).toggleClass("colors");
    });

    let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
    let list = media.getMediaList(attr_search, dataMap.get("list"));
    service.showUsers(list);
    service.showPagination(media, data, dataMap, numberMedia, currentPage);

    // блок кода для динамического изменения данных на странице
    $("#pagination").on("click", ".page-link", function () {
        let currentPage = $(this).text();
        // console.log(currentPage);
        if (currentPage === '...') {
            return;
        }

        let dataMap = data.getListUsers(url + numberMedia + "&page=" + currentPage + "&weeks=" + weeks);
        let list = media.getMediaList(attr_search, dataMap.get("list"));
        service.showUsers(list);
        service.showPagination(media, data, dataMap, numberMedia, currentPage);
    });


//====================================================================================================================//


    // обработка search на странице users
    let input = document.querySelector("#find");
    input.addEventListener("input", function (e) {
        let name = e.target.value;
        // console.log(name);
        $('#moderator').hide();
        $("#pagination").hide();
        $("#sorting-time").hide();

        $("#sorting-time-search").show();
        $("#pagination-search").show();

        $('.sorting').removeClass("colors-search");
        $('#month-search').toggleClass("colors-search");

        let currentPage = 1;
        let url_find = service.getUrl("find");
        let weeks = $('#month-search').attr("data-weeks-search");
        let dataMap = data.getListUsers(url_find + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);

        if (dataMap.get("list").length === 1) {
            location.assign("http://localhost:5557/profile");
            return;
        }

        if (dataMap.get("list").length === 0) {
            let message = document.createElement("div");
            message.textContent = "Участник с таким именем не наден";
            $("#users").html($(message));
            $("#pagination-search").hide();
            return;
        }

        $('.sorting').click(function () {
            $('.sorting').removeClass("colors-search");
            weeks = $(this).attr("data-weeks-search");
            // console.log(weeks);

            if (weeks === '-1') {
                weeks = service.getCountWeeksDateCreation();
            }

            dataMap = data.getListUsers(url_find + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);
            let list = media.getMediaList("reputation", dataMap.get("list"));
            service.showUsers(list);
            service.showPaginationSearch(media, data, dataMap, numberMedia, currentPage);

            $(this).toggleClass("colors-search");
        });

        dataMap = data.getListUsers(url_find + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);
        let list = media.getMediaList("reputation", dataMap.get("list"));
        service.showUsers(list);
        service.showPaginationSearch(media, data, dataMap, numberMedia, currentPage);

        // блок кода для динамического изменения данных для search
        $("#search-pagination").on("click", ".page-link", function () {
            let currentPage = $(this).text();
            // console.log(currentPage);
            if (currentPage === '...') {
                return;
            }

            let dataMap = data.getListUsers(url_find + numberMedia + "&page=" + currentPage + "&weeks=" + weeks + "&name=" + name);
            let list = media.getMediaList("reputation", dataMap.get("list"));
            service.showUsers(list);
            service.showPaginationSearch(media, data, dataMap, numberMedia, currentPage);
        });
    });
});
