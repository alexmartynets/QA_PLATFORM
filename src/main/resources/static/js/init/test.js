// jQuery(document).ready(function ($) {
//
//     let data = new DataUsersPage();
//     let service = new DataUsersService();
//     // количество карточек на странице
//     let numberMedia = 20;
//     let weeks = 12;
//
//     let url_list = "http://localhost:5557/api/user/" + numberMedia + "/page/";
//     let currentPage = 1;
//
//     // получаем даннные для 1 страницы
//     let dataMap = data.getListUsers(url_list + currentPage + "/" + weeks);
//
//     service.showUsers(data, dataMap);
//     service.showPagination(data, dataMap, numberMedia, currentPage);
//
//     /*блок кода для динамического изменения данных*/
// $("body").on("click", ".page-link", function () {
//     let currentPage = $(this).text();
//
//     if (currentPage === '...') {
//         return;
//     }
//
//     // получаем даннные для текущей страницы
//     let dataMap = data.getListUsers(url_list + currentPage + "/" + weeks);
//
//     service.showUsers(data, dataMap);
//     service.showPagination(data, dataMap, numberMedia, currentPage);
// });
//
// // active button search-users
// $('.search').click( function() {
//     $('.search').removeClass("active");
//
//     let text = $(this).text();
//     console.log(text);
//
//     $(this).toggleClass("active");
//
// });
//
// // colors a search-time users
// $('.search-time').click( function() {
//     $('.search-time').removeClass("colors");
//
//     let text = $(this).text();
//     console.log(text);
//
//     $(this).toggleClass("colors");
//
// });
//
//
// // обработка search на странице users
// let input = document.querySelector("#search");
// input.addEventListener("input", function (e) {
//     let name = e.target.value;
//     $("#pagination").show();
//
//     let currentPage = 1;
//     let url_search = "http://localhost:5557/api/user/" + numberMedia + "/page/" + currentPage + "/name?name=" + name;
//
//     // получаем даннные для 1 страницы
//     let dataMap = data.getListUsers(url_search);
//
//     if (dataMap.get("list").length === 1) {
//         location.assign("http://localhost:5557/profile");
//         return;
//     }
//
//     if (dataMap.get("list").length === 0) {
//         let message = document.createElement("div");
//         message.textContent = "Участник с таким именем не наден";
//         $("#users").html($(message));
//         $("#pagination").hide();
//     } else {
//         // если пользователи найдены
//         service.showPagination(data, dataMap, numberMedia, currentPage);
//         service.showUsers(data, dataMap);
//     }
//
//     /*блок кода для динамического изменения данных для search*/
//     $("body").on("click", ".page-link", function () {
//         let currentPage = $(this).text();
//
//         if (currentPage === '...') {
//             return;
//         }
//
//         let url_search = "http://localhost:5557/api/user/" + numberMedia + "/page/" + currentPage + "/name?name=" + name;
//         // получаем даннные для текущей страницы
//         let dataMap = data.getListUsers(url_search);
//
//         service.showUsers(data, dataMap);
//         service.showPagination(data, dataMap, numberMedia, currentPage);
//     });
// });
//
// });
