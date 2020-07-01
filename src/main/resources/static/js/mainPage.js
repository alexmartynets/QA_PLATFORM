jQuery(function ($) {

    $.ajax({
        url: 'api/question',
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

    function getTags(list) {
        let tags ="";
        for (let i = 0; i < list.length; i++){
            tags += ('<button type="button" class="btn btn-primary btn-sm mr-1">'+ list[i] +'</button>')
        }
        return tags;
    }

    function addRow(data) {

        let newRow = "";
        newRow += ('<li class="list-group-item">');
        newRow += ('<div class="container">');
        newRow += ('<div class="row">');
        newRow += ('<div class="col-sm-5">')
        newRow += ('<a href = "#">')
        newRow += ('<button type="button" class="btn btn-outline-secondary mr-1">Голосов '+ data.countValuable +'</button>');
        if(data.countAnswer > 0 && data.helpful === true){
            newRow += ('<button type="button" class="btn btn-success">Ответов ' + data.countAnswer +'</button>');
        } else if(data.countAnswer > 0 && data.helpful === false){
            newRow += ('<button type="button" class="btn btn-outline-success">Ответов ' + data.countAnswer +'</button>');
        } else {
            newRow += ('<button type="button" class="btn btn-outline-secondary">Ответов ' + data.countAnswer +'</button>');
        }
        newRow += ('<button type="button" class="btn btn-outline-secondary ml-1">Показов ' + data.viewCount +' </button>');
        //newRow += "&nbsp&nbsp&nbsp  ";
        newRow += ('</div>');
        newRow += ('<div class="col pl-0">');
        newRow += ('<h4>' + data.title + '</h4>');
        newRow += (getTags(data.tags));
        newRow += ('<a class="mr-1">'+ data.persistDateTime+'</a>');
        newRow += ('<a href ="#" class="mr-1"> '+ data.userId +'</a>');
        newRow += ('<a>'+ data.reputationCount+'</a>');
        newRow += ('</div>');
        newRow += ('</a>');
        newRow += ('</li>');

        $("#getQuestions").append(newRow);
    }

    // My block
    getListOfTags();

    $('#v-pills-questions-tab').on('click', function () {
        window.location = "/questions";
    })

    $('#v-pills-unanswered-tab').on('click', function () {
        window.location = "/unanswered";
    })

    $("#recentTags").on("click", "#tagsHref", function (event) {
        let mainTagId = $(this).attr('value');

        // getQuestionsByTag(mainTagId);
    });
});

function getListOfTags() {
    $.ajax({
        url: '/api/tags',
        type: 'GET',
        dataType: 'json',
        success: function (listOfTags) {
            let tagData = '';
            $.each(listOfTags, function (i, tag) {
                tagData += `<li class="post-tag"><a id="tagsHref" value="${tag.id}"  href="/questions/tagged/${tag.id}">${tag.name}</a></li>`;
            });

            $('#recentTags').html(tagData);
        },
        error: function () {
            alert("TagsError");
        }
    })
}

function getQuestionsByTag(mainTagId) {
    alert("getQuestionsByTag - вопросы по тегу");
    $.ajax({
        url: '/api/questions/tagged/' + mainTagId,
        type: 'GET',
        dataType: 'json',
        success: function (listOfQuestion) {
            $.each(listOfQuestion, function (i, q) {
                $('#getQuestionsByTag').append(fillQuestionBlock(q));
            });
        },
        error: function () {
            alert("getQuestionsByTagError");
        }
    })

    $.ajax({
        url: '/api/tags/relatedTags/' + mainTagId,
        type: 'GET',
        dataType: 'json',
        success: function (listOfRelatedTags) {
            let tagData = '';
            $.each(listOfRelatedTags, function (i, tag) {
                tagData += `<li class="post-tag btn btn-light"><a id="relatedHref" type="button" href="/questions/tagged/${tag.id}">${tag.name}</a></li>`;
            });

            $('#recentTagsHeader').html('Связанные Теги');
            $('#recentTags').html(tagData);
        },
        error: function () {
            alert("TagsError");
        }
    })
}