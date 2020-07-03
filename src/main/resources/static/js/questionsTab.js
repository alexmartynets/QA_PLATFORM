$(document).ready(function () {

    getQuestionsSortedByVotes();

    // getListOfTags is a method from mainPage.js
    getListOfTags();

    $('#btnWatch').click(function () {
        let tags = {};
        tags.name = $('#watch').val();
        $.cookie('tagsCookie',tags.name);
        addWatchTag(tags);
    });
})

function addWatchTag(tags) {
    $.ajax({
        url: '/api/user/question/watchTag',
        type: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        data: JSON.stringify(tags),
        success: function (data) {
           let a = data;
        }
    })
}

function getQuestionsSortedByVotes() {
    let watchCookies = $.cookie('tagsCookie');
    if (watchCookies === null){
    $.ajax({
        url: '/api/user/question/questions',
        type: 'GET',
        dataType: 'json',
        success: function (listOfQuestion) {
            $.each(listOfQuestion, function (i, q) {
                $('#getQuestionsQ').append(fillQuestionBlock(q));
            });
        }
    })
    } else if ( watchCookies != null ){
        let tags = {};
        tags.name = watchCookies;
        $.ajax({
            url: '/api/user/question/watchTag',
            type: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            data: JSON.stringify(tags),
            success: function (listOfQuestion) {
                $.each(listOfQuestion, function (i, q) {
                    $('#getQuestionsQ').append(fillQuestionBlock(q));
                });
            }
        })
    }
}

function fillQuestionBlock(q) {
    let questionData =
        '<li class="list-group-item">' +
            '<div class="question-block container mt-4">' +
                '<div class="voice-block">' +
                    '<div class="voices voice-item">' +
                        '<p class="points">' + q.countValuable + '</p>' +
                        '<span class="smallHeader">Голосов</span>' +
                    '</div>' +

                    '<div class="answers voice-item">' +
                        '<p class="points">' + q.countAnswer + '</p>' +
                        '<span class="smallHeader">Ответов</span>' +
                    '</div>' +
                    '<div class="voice-result">' +
                        '<p><span class="viewCount">' + q.viewCount + ' показов</span></p>' +
                    '</div>' +
                '</div>' +

                '<div class="text-block">' +
                    '<a id="qTitle" href="/question/' + q.id + '"> <h3 class="question-title mt-3">' + q.title + '</h3></a>' +
                    '<p class="question-body">' + q.description + '</p>' +
                    '<div class="question-foot">' +
                        '<div class="tags-block">' +
                            getTags(q.tags) +
                        '</div>' +
                    '</div>' + //question-foot
                 '</div>'  + //text-block
                '<div class="user-info">' +
                    // '<p class="timeOfQuestion">задан <span>' + q.persistDateTime - new Date($.now()) + '</span> минут назад</p>' +
                    '<p class="user-name">' + q.userDto.fullName + '</p>' +
                '</div>' +
            '</div>' +
        '</li>'

    return questionData;
}

function getTags(list) {
    let tags ="";
    for (let i = 0; i < list.length; i++){
        tags += ('<button type="button" class="btn btn-primary btn-sm mr-1">'+ list[i].name +'</button>')
    }
    return tags;
}