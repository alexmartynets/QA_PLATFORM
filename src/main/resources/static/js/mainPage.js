jQuery(function ($) {

    $.ajax({
        url: '/api/user/question',
        type: 'GET',
        contentType : "application/json",
        dataType : "json",
        timeout : 100000,
        success: function (data) {
            data.forEach(function (element) {
                addRow(element);
            })
        },
    });
    function getButtons(tags){
        return tags.map(el=>{
            return `<button type="button" class="btn btn-primary btn-sm mr-1">${el.name}</button>`;
        });
    }

    function addRow(data) {

        var newRow = "";
        var d = new Date(data.persistDateTime);

        newRow += ('<li class="list-group-item">');
        newRow += ('<div class="container">');
        newRow += ('<div class="row">');
        newRow += ('<div class="col-sm-5">')
        newRow += ('<a href = "#">')
        newRow += ('<button type="button" class="btn btn-outline-secondary mr-1 btn-square">Голосов: '+ data.countValuable +'</button>');
        if(data.countAnswer > 0 && data.isHelpful === true){
            newRow += ('<button type="button" class="btn btn-success btn-square">Ответов: ' + data.countAnswer +'</button>');
        } else if(data.countAnswer > 0 && data.isHelpful === false){
            newRow += ('<button type="button" class="btn btn-outline-success btn-square">Ответов: ' + data.countAnswer +'</button>');
        } else {
            newRow += ('<button type="button" class="btn btn-outline-secondary btn-square">Ответов: ' + data.countAnswer +'</button>');
        }
        newRow += ('<button type="button" class="btn btn-outline-secondary ml-1 btn-square">Показов: ' + data.viewCount +' </button>');
        //newRow += "&nbsp&nbsp&nbsp  ";
        newRow += ('</div>');
        newRow += ('<div class="col pl-0">');
        newRow += ('<h4>' + data.title + '</h4>');
        newRow += (getButtons(data.tags).join(""));
        newRow += ('<a class="mr-1">'+ d.toDateString() +'</a>');
        newRow += ('<a href ="#" class="mr-1"> '+ data.username +'</a>');
        newRow += ('<a>'+ data.reputationCount+'</a>');
        newRow += ('</div>');
        newRow += ('</a>');
        newRow += ('</li>');

        $("#getQuestions").append(newRow);
    }

});