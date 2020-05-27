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

    // обработка search users
    let input = document.querySelector("#search");
    input.addEventListener("input", function (e) {
        let res = e.target.value;
        console.log(res);
    });

});

// добавить URL в имя пользователя для перехода на другую страницу
// если поле пустое выводим всех, не чистим поле + пагинация для найденых
// ели не нашли надпись нет пользователей
// $("#creat")[0].reset();
// document.getElementById("textInput").value = "";