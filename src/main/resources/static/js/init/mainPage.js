jQuery(document).ready(function ($) {
    getAjax(items_number, current_page, list_element, pagination_element);
})

const list_element = document.getElementById('list_questions');
const pagination_element = document.getElementById('pagination');

let current_page = 1;
let items_number = 3;

function setupPagination(pair, wrapper) {
    wrapper.innerHTML = "";
    let number = Object.values(pair)[1];
    let page_count = Math.ceil(number /items_number);
    for (let i = 1; i < page_count + 1; i++) {
        let btn = paginationButton(i);
        wrapper.appendChild(btn);
    }
}

function paginationButton (page) {
    let button = document.createElement('button');
    button.innerText = page;

    if (current_page === page) button.classList.add('active');

    button.addEventListener('click', function () {
        current_page = page;
        displayList(list_element, current_page, items_number);

        let current_btn = document.querySelector('.pagenumbers button.active');
        current_btn.classList.remove('active');

        button.classList.add('active');
    });

    return button;
}

function getTags(list) {
    var tags ="";
    for (var i = 0; i < list.length; i++){
        tags += (`<button type="button" class="btn btn-primary btn-sm mr-1">${list[i].name}</button>`)
    }
    return tags;
}

function addRow(data) {

    var newRow = "";
    newRow += ('<li class="list-group-item">');
    newRow += ('<div class="container">');
    newRow += ('<div class="row">');
    newRow += ('<div class="col-sm-5">')
    newRow += ('<a href = "#">')
    newRow += (`<button type="button" class="btn btn-outline-secondary mr-1 btn-square">Голосов ${data.countValuable}</button>`);
    if(data.countAnswer > 0 && data.helpful === true){
        newRow += ('<button type="button" class="btn btn-success btn-square">Ответов ' + data.countAnswer +'</button>');
    } else if(data.countAnswer > 0 && data.helpful === false){
        newRow += (`<button type="button" class="btn btn-outline-success btn-square">Ответов ${data.countAnswer}</button>`);
    } else {
        newRow += (`<button type="button" class="btn btn-outline-secondary btn-square">Ответов ${data.countAnswer}</button>`);
    }
    newRow += (`<button type="button" class="btn btn-outline-secondary ml-1 btn-square">Показов ${data.viewCount}</button>`);
    newRow += ('</div>');
    newRow += ('<div class="col pl-0">');
    newRow += (`<h4>${data.title}</h4>`);
    newRow += (getTags(data.tags));
    newRow += (`<a class="mr-5">${data.persistDateTime}</a>`);
    newRow += (`<a href ="#" class="mr-1"> ${data.userId}</a>`);
    newRow += (`<a>${data.reputationCount}</a>`);
    newRow += ('</div>');
    newRow += ('</a>');
    newRow += ('</li>');

    $("#list_questions").append(newRow);
}

/*    function counting_time(time) {
        let now = new Date();
        let month = (now.getMonth() + 1 - time.getMonth());
        let day = (now.getDate() - time.getDayOfMonth());
        let year = (now.getFullYear() - time.getYear());
        return month + "/" + day + "/" + year;
    }*/