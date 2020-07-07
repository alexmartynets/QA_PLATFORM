const section = document.querySelector('section');
const numOfPageTags = 1;
var currentPageNum = 1;
var numOfTags;

$(document).ready(function () {
    let requestURL = 'http://localhost:5557/api/user/tag/?pageSize=' + numOfPageTags + '&pageNumber=1';
    let request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function () {
        let tags = request.response;
        numOfTags = tags['key']; //всего записей
        printPageNumbers(numOfTags);
        getPageFun(1);
    }
});

function printPageNumbers(numOfTags) {
    var numOfPages = Math.ceil(numOfTags / numOfPageTags); //кол-во страниц

    var paginator = document.querySelector(".paginator");
    var page = "";
    var paginNumArr = paginationFun(currentPageNum, numOfPages);
    for (var i = 0; i < paginNumArr.length; i++) {
        if (paginNumArr[i] == '...') {
            page += '...    ';
        } else {
            page += "<span data-page=" + (paginNumArr[i] - 1) * numOfPageTags + "  id=\"page"
                + (paginNumArr[i]) + "\">" + (paginNumArr[i]) + "</span>";
        }
    }
    paginator.innerHTML = page;
}

function pagination(event) {
    var main_page = document.getElementById("page" + currentPageNum);
    main_page.classList.add("paginator_active");
    var e = event || window.event;
    var target = e.target;
    var id = target.id;

    currentPageNum = parseInt(id.match(/\d+/));

    if (target.tagName.toLowerCase() != "span") return;

    main_page.classList.remove("paginator_active");
    main_page = document.getElementById(id);
    main_page.classList.add("paginator_active");
    printPageNumbers(numOfTags);
    getPageFun(currentPageNum);
}

function getPageFun(pageNumber) {
    let requestURL = 'http://localhost:5557/api/user/tag/?pageSize=' + numOfPageTags + '&pageNumber=' + pageNumber;
    let request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function () {
        let tags = request.response;
        showTags2(tags);
    }

    function showTags2(jsonObj) {

        console.log(jsonObj);
        let valueArray = jsonObj['value'];

        $("#tags-list > section").empty();

        section.setAttribute("class", "grid-layout");
        for (let i = 0; i < valueArray.length; i++) {
            sCard = document.createElement('div');
            sCard.setAttribute("class", "s-card js-tag-cell grid fd-column");
            let myGrid = document.createElement('div');
            myGrid.setAttribute("class", "grid jc-space-between ai-center mb12");
            let gridCell = document.createElement('div');
            gridCell.setAttribute("class", "grid--cell");
            let myA = document.createElement('a');
            myA.setAttribute("href", "#");
            let myPara1 = document.createElement('p');
            let myPara2 = document.createElement('p');
            let myList = document.createElement('ul');

            myA.textContent = valueArray[i].name;
            myPara1.textContent = valueArray[i].description;
            myPara2.textContent = 'Количество вопросов: ' + valueArray[i].questionCount;

            sCard.appendChild(gridCell);
            sCard.appendChild(myA);
            sCard.appendChild(myPara1);
            sCard.appendChild(myPara2);
            sCard.appendChild(myList);
            section.appendChild(sCard);
            var main_page = document.getElementById("page" + pageNumber);
            main_page.classList.add("paginator_active");
        }
    }
}

function paginationFun(c, m) {
    var current = c,
        last = m,
        delta = 2,
        left = current - delta,
        right = current + delta + 1,
        range = [],
        rangeWithDots = [],
        l;

    for (let i = 1; i <= last; i++) {
        if (i == 1 || i == last || i >= left && i < right) {
            range.push(i);
        }
    }

    for (let i of range) {
        if (l) {
            if (i - l === 2) {
                rangeWithDots.push(l + 1);
            } else if (i - l !== 1) {
                rangeWithDots.push('...');
            }
        }
        rangeWithDots.push(i);
        l = i;
    }

    return rangeWithDots;
}