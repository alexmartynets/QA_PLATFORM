class DataUsersPage {

    constructor() {
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
                    alert('The server does not answer...');
                    console.log('Error - ' + xhr.status + ': ' + xhr.statusText + error);
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

    showPagination(media, data, dataMap, numberMedia, currentPage) {
        let countOfPages = data.getNumberOfPages(dataMap.get("count"), numberMedia);
        let listButtonPagination = data.generateRangePagination(currentPage, countOfPages);
        let listLi = media.mapperMediaPagination(listButtonPagination);
        $("#pagination").html($(listLi));
    }

    showUsers(listUsers) {
        $("#users").html($(listUsers));
    }

    getUrl(key) {
        let mapUrl = new Map([
            ["reputation", "http://localhost:5557/api/user/reputation?count="],
            ["voice", "http://localhost:5557/api/user/voice?count="],
            ["editor", "http://localhost:5557/api/user/editor?count="],
            ["moderator", "http://localhost:5557/api/user/moderator"],
            ["new", "http://localhost:5557/api/user/new?count="],
            ["new-reputation", "http://localhost:5557/api/user/new/reputation?count="],
            ["search", "http://localhost:5557/api/user/search?count="]
        ]);
        return mapUrl.get(key);
    }

    getCountWeeksDateCreation() {
        let today = new Date();
        let dateCreation = new Date('2018-01-01');
        return Math.ceil((today - dateCreation) / 604800000);
    }
}

class MediaFactory {
    constructor() {
    }

    getMediaList(key, list) {
        if (key === "reputation") return this.mapperMediaUsers(list);
        if (key === "voice") return this.mapperMediaVoice(list);
        if (key === "editor") return this.mapperMediaEditor(list);
        if (key === "moderator") return this.mapperMediaModerator(list);
        if (key === "new") return this.mapperMediaNewUsers(list);
        if (key === "new-reputation") return this.mapperMediaNewUsersReputation(list);
        if (key === "search") return this.mapperMediaUsers(list);
    }

    mapperMediaModerator(list) {
        let listMedia = [];

        for (let i = 0; i < list.length; i++) {

            const img = document.createElement("img");
            img.src = "//placehold.it/48x48?text=" + list[i].id;
            img.className = "align-self-start mr-3";
            img.alt = "...";

            const divs = document.createElement("div");
            divs.className = "media-body";

            const name = document.createElement("a");
            name.href = "/profile";
            name.className = "mt-0 user-name";
            name.innerText = list[i].fullName;
            name.style.fontWeight= "800";
            name.style.color = "#343a40";

            const city = document.createElement("div");
            city.className = "user-location";
            city.innerText = list[i].city;
            city.style.fontSize= "12px";

            const ratings = document.createElement("div");
            ratings.className = "user-rating";
            ratings.innerText = list[i].reputationCount;
            ratings.style.fontSize= "12px";

            const year = document.createElement("div");
            year.className = "user-year";
            year.innerText = "выбран " + list[i].dateAppointedModerator.substr(0, 4);
            year.style.opacity = "0.4";
            year.style.fontWeight = "900";

            divs.appendChild(name);
            divs.appendChild(city);
            divs.appendChild(ratings);
            divs.appendChild(year);

            const media = document.createElement("div");
            media.className = "media user-info";
            media.appendChild(img);
            media.appendChild(divs);

            listMedia.push(media);
        }
        // console.log(listMedia);
        return listMedia;
    }

    mapperMediaEditor(list) {
        let listMedia = [];

        for (let i = 0; i < list.length; i++) {

            const img = document.createElement("img");
            img.src = "//placehold.it/48x48?text=" + list[i].id;
            img.className = "align-self-start mr-3";
            img.alt = "...";

            const divs = document.createElement("div");
            divs.className = "media-body";

            const name = document.createElement("a");
            name.href = "/profile";
            name.className = "mt-0 user-name";
            name.innerText = list[i].fullName;
            name.style.fontWeight= "800";
            name.style.color = "#343a40";

            const city = document.createElement("div");
            city.className = "user-location";
            city.innerText = list[i].city;
            city.style.fontSize= "12px";

            const ratings = document.createElement("div");
            ratings.className = "user-rating";
            ratings.innerText = list[i].reputationCount;
            ratings.style.fontSize= "12px";

            const editor = document.createElement("div");
            editor.className = "user-editor";
            editor.innerText = list[i].countChanges + " правок";
            editor.style.opacity = "0.4";
            editor.style.fontWeight = "900";
            editor.style.fontSize= "12px";

            divs.appendChild(name);

            divs.appendChild(city);
            divs.appendChild(ratings);
            divs.appendChild(editor);

            const media = document.createElement("div");
            media.className = "media user-info";
            media.appendChild(img);
            media.appendChild(divs);

            listMedia.push(media);
        }
        // console.log(listMedia);
        return listMedia;
    }

    mapperMediaVoice(list) {
        let listMedia = [];

        for (let i = 0; i < list.length; i++) {

            const img = document.createElement("img");
            img.src = "//placehold.it/48x48?text=" + list[i].id;
            img.className = "align-self-start mr-3";
            img.alt = "...";

            const divs = document.createElement("div");
            divs.className = "media-body";

            const name = document.createElement("a");
            name.href = "/profile";
            name.className = "mt-0 user-name";
            name.innerText = list[i].fullName;
            name.style.fontWeight= "800";
            name.style.color = "#343a40";

            const city = document.createElement("div");
            city.className = "user-location";
            city.innerText = list[i].city;
            city.style.fontSize= "12px";

            const ratings = document.createElement("div");
            ratings.className = "user-rating";
            ratings.innerText = list[i].reputationCount;
            ratings.style.fontSize= "12px";

            const voice = document.createElement("div");
            voice.className = "user-voice";
            voice.innerText = list[i].countVoice + " голосов";
            voice.style.opacity = "0.4";
            voice.style.fontWeight= "900";
            voice.style.fontSize= "12px";

            divs.appendChild(name);
            divs.appendChild(city);
            divs.appendChild(ratings);
            divs.appendChild(voice);

            const media = document.createElement("div");
            media.className = "media user-info";
            media.appendChild(img);
            media.appendChild(divs);

            listMedia.push(media);
        }
        // console.log(listMedia);
        return listMedia;
    }

    mapperMediaNewUsers(list) {
        let listMedia = [];

        for (let i = 0; i < list.length; i++) {

            const img = document.createElement("img");
            img.src = "//placehold.it/48x48?text=" + list[i].id;
            img.className = "align-self-start mr-3";
            img.alt = "...";

            const divs = document.createElement("div");
            divs.className = "media-body";

            const name = document.createElement("a");
            name.href = "/profile";
            name.className = "mt-0 user-name";
            name.innerText = list[i].fullName;
            name.style.fontWeight= "800";
            name.style.color = "#343a40";

            // todo даты создания
            let today = new Date();
            let data = list[i].persistDateTime.substr(0, 10);
            let dateCreation = new Date(data);
            let days = Math.ceil((today - dateCreation) / 86400000);

            const ratings = document.createElement("div");
            ratings.className = "user-rating";
            ratings.innerText = list[i].reputationCount + " за " + days;
            ratings.style.opacity = "0.4";
            ratings.style.fontWeight= "900";
            ratings.style.fontSize= "12px";

            divs.appendChild(name);
            divs.appendChild(ratings);

            const media = document.createElement("div");
            media.className = "media user-info";
            media.appendChild(img);
            media.appendChild(divs);

            listMedia.push(media);
        }
        // console.log(listMedia);
        return listMedia;
    }

    mapperMediaNewUsersReputation(list) {
        let listMedia = [];

        for (let i = 0; i < list.length; i++) {

            const img = document.createElement("img");
            img.src = "//placehold.it/48x48?text=" + list[i].id;
            img.className = "align-self-start mr-3";
            img.alt = "...";

            const divs = document.createElement("div");
            divs.className = "media-body";

            const name = document.createElement("a");
            name.href = "/profile";
            name.className = "mt-0 user-name";
            name.innerText = list[i].fullName;
            name.style.fontWeight= "800";
            name.style.color = "#343a40";

            const city = document.createElement("div");
            city.className = "user-location";
            city.innerText = list[i].city;
            city.style.fontSize= "12px";

            // расчет времяни от даты создания
            let today = new Date();
            let data = list[i].persistDateTime.substr(0, 10);
            let dateCreation = new Date(data);
            let days = Math.ceil((today - dateCreation) / 86400000);

            const ratings = document.createElement("div");
            ratings.className = "user-rating";
            ratings.innerText = list[i].reputationCount + " за " + days + " дней";
            ratings.style.opacity = "0.4";
            ratings.style.fontWeight= "900";
            ratings.style.fontSize= "12px";

            const tags = document.createElement("a");
            tags.href = "/tags/" + list[i].about;
            tags.className = "user-tags";
            tags.innerText = list[i].about;

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

    mapperMediaUsers(list) {
        let listMedia = [];

        for (let i = 0; i < list.length; i++) {

            const img = document.createElement("img");
            img.src = "//placehold.it/48x48?text=" + list[i].id;
            img.className = "align-self-start mr-3";
            img.alt = "...";

            const divs = document.createElement("div");
            divs.className = "media-body";

            const name = document.createElement("a");
            name.href = "/profile";
            name.className = "mt-0 user-name";
            name.innerText = list[i].fullName;
            name.style.fontWeight= "800";
            name.style.color = "#343a40";

            const city = document.createElement("div");
            city.className = "user-location";
            city.innerText = list[i].city;
            city.style.fontSize= "12px";

            const ratings = document.createElement("div");
            ratings.className = "user-rating";
            ratings.innerText = list[i].reputationCount;
            ratings.style.opacity = "0.4";
            ratings.style.fontWeight= "900";
            ratings.style.fontSize= "12px";

            const tags = document.createElement("a");
            tags.href = "/tags/" + list[i].about;
            tags.className = "user-tags";
            tags.innerText = list[i].about;

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
}




