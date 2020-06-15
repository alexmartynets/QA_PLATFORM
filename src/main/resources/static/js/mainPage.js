jQuery(document).ready(function ($) {
    const list_element = document.getElementById('list_questions');
    const pagination_element = document.getElementById('pagination');

    let current_page = 1;
    let rows = 3;

    getButtons();

    function getButtons() {
        $.ajax({
            url: '/'+ rows + '/page/' + current_page,
            type: 'GET',
            contentType: "application/json",
            dataType: "json",
            success: function (pair) {
                SetupPagination(pair, pagination_element);
                DisplayList(list_element, current_page);
            }
        })
    }

    function SetupPagination(pair, wrapper) {
        wrapper.innerHTML = "";
        let number = Object.values(pair)[1];
        let page_count = Math.ceil(number /rows);
        for (let i = 1; i < page_count + 1; i++) {
            let btn = PaginationButton(i);
            wrapper.appendChild(btn);
        }
    }

    function PaginationButton (page) {
        let button = document.createElement('button');
        button.innerText = page;

        if (current_page === page) button.classList.add('active');

        button.addEventListener('click', function () {
            current_page = page;
            DisplayList(list_element, current_page);

            let current_btn = document.querySelector('.pagenumbers button.active');
            current_btn.classList.remove('active');

            button.classList.add('active');
        });

        return button;
    }

    function DisplayList (wrapper, page) {
        wrapper.innerHTML = "";
        $.ajax({
            url: '/'+ rows + '/page/' + page,
            type: 'GET',
            contentType: "application/json",
            dataType: "json",
            success: function (pair) {
                let paginatedItems = Object.values(pair)[0];
                for (let i = 0; i < paginatedItems.length; i++) {
                    let item = paginatedItems[i];
                    addRow(item);
                }
            }
        })
    }

    function getTags(list) {
        var tags ="";
        for (var i = 0; i < list.length; i++){
            tags += ('<button type="button" class="btn btn-primary btn-sm mr-1">'+ list[i].name +'</button>')
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
        newRow += ('<button type="button" class="btn btn-outline-secondary mr-1 btn-square">Голосов '+ data.countValuable +'</button>');
        if(data.countAnswer > 0 && data.helpful === true){
            newRow += ('<button type="button" class="btn btn-success btn-square">Ответов ' + data.countAnswer +'</button>');
        } else if(data.countAnswer > 0 && data.helpful === false){
            newRow += ('<button type="button" class="btn btn-outline-success btn-square">Ответов ' + data.countAnswer +'</button>');
        } else {
            newRow += ('<button type="button" class="btn btn-outline-secondary btn-square">Ответов ' + data.countAnswer +'</button>');
        }
        newRow += ('<button type="button" class="btn btn-outline-secondary ml-1 btn-square">Показов ' + data.viewCount +' </button>');
        newRow += ('</div>');
        newRow += ('<div class="col pl-0">');
        newRow += ('<h4>' + data.title + '</h4>');
        newRow += (getTags(data.tags));
        newRow += ('<a class="mr-5">'+ data.persistDateTime +'</a>');
        newRow += ('<a href ="#" class="mr-1"> '+ data.userId +'</a>');
        newRow += ('<a>'+ data.reputationCount+'</a>');
        newRow += ('</div>');
        newRow += ('</a>');
        newRow += ('</li>');

        $("#list_questions").append(newRow);
    }
})