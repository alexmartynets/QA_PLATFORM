class DataUsersPage {

    constructor() {
    }

    /*    "key": [
        {
            "id": 8,
            "reputationCount": 35,
            "voiceCount": 14,
            "userId": 8,
            "fullName": "Роман Игоривич Смышляев",
            "aboutUser": "about user7",
            "cityUser": "SPB",
            "imageUser": null
        }
    ],
    "value": 1*/

    mapperMediaUsers(listUsers) {
        let listMedia = [];

        for (let i = 0; i < listUsers.length; i++) {
            let user = listUsers[i];

            const img = document.createElement("img");
            img.src = "//placehold.it/48x48?text=" + user.id;
            img.className = "align-self-start mr-3";
            img.alt = "...";

            const divs = document.createElement("div");
            divs.className = "media-body";

            const name = document.createElement("a");
            name.href = "/profile";
            name.className = "mt-0 user-name";
            name.innerText = user.fullName;

            const city = document.createElement("div");
            city.className = "user-location";
            city.innerText = user.city;

            const ratings = document.createElement("div");
            ratings.className = "user-rating";
            ratings.innerText = user.reputationCount;

            const tags = document.createElement("a");
            tags.href = "/tags/" + user.about;
            tags.className = "user-tags";
            tags.innerText = user.about;

            divs.appendChild(name);
            divs.appendChild(city);
            divs.appendChild(ratings);
            divs.appendChild(tags);

            const media = document.createElement("div");
            media.className = "media user-info";
            media.appendChild(img);
            media.appendChild(divs);

            listMedia.push(media);
        }
        // console.log(listMedia);
        return listMedia;
    }

    mapperMediaPagination(listPagination) {
        let listMediaPagination = [];

        for (let i = 0; i < listPagination.length; i++) {
            let li = document.createElement("li");
            li.className = "page-item";
            let a = document.createElement("a");
            a.className = "page-link";
            a.href = "#";
            a.innerText = listPagination[i];
            li.appendChild(a);
            listMediaPagination.push(li);
        }
        // console.log(listMediaPagination);
        return listMediaPagination;
    }

    getNumberOfPages(countOnUsers, numberItemsOnPage) {
        return Math.ceil(countOnUsers / numberItemsOnPage);
    }

    // геренатор pagination
    generateRangePagination(currentPage, countOfPages) {
        let pagesToShow = 3,
            page = currentPage - pagesToShow > 0 ? currentPage - pagesToShow : 1,
            first = 0,
            listPagination = [];

        for (let i = 0; i < pagesToShow * 2 && page < countOfPages; i++) {
            listPagination.push(page);
            page++;
        }
        if (pagesToShow + 2 < currentPage) {
            listPagination.unshift("...");
            listPagination.unshift(1); //add first page
        }

        if (countOfPages > 1) {
            if (countOfPages - pagesToShow > currentPage) {
                listPagination.push("...");
            }
            listPagination.push(countOfPages); //add last page
        }
        // console.log(listPagination);
        return listPagination;
    }

    getListUsers(url) {
        let map = new Map();
        $.ajax({
            type: "GET",
            url: url,
            xhrFields: {withCredentials: true},
            cache: false,
            async: false,
            success: function (data) {
                // console.log(data);
                for (let field in data) {
                    map.set("list", data.key);
                    map.set("count", data.value);
                }
            },
            error: function (xhr, status, error) {
                if (xhr.status === 404) {
                    alert('list User not found...');
                } else {
                    alert('Error - ' + xhr.status + ': ' + xhr.statusText + error);
                }
            }
        });
        // console.log(map);
        return map;
    }

}

class DataUsersService {

    constructor() {
    }

    showPagination(data, dataMap, numberMedia, currentPage) {
        let countOfPages = data.getNumberOfPages(dataMap.get("count"), numberMedia);
        let listButtonPagination = data.generateRangePagination(currentPage, countOfPages);
        let listLi = data.mapperMediaPagination(listButtonPagination);
        $("#pagination").html($(listLi));
    }

    showUsers(data, dataMap) {
        let listUsersForPage = dataMap.get("list");
        let listMediaUsers = data.mapperMediaUsers(listUsersForPage);
        $("#users").html($(listMediaUsers));
    }
}




