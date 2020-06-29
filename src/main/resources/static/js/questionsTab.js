$(document).ready(function () {
    getQuestionsSortedByVotes();

    getListOfTags();
})

function getQuestionsSortedByVotes() {
    $.ajax({
        url: '/api/questions',
        type: 'GET',
        dataType: 'json',
        success: function (listOfQuestion) {
            $.each(listOfQuestion, function (i, q) {
                console.log(q);
                $('#getQuestions').append(fillQuestionBlock(q));
            });
        }
    })
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
                        '<p ><span class="viewCount">' + q.viewCount + ' показов</span></p>' +
                    '</div>' +
                '</div>' +

                '<div class="text-block">' +
                    '<h3 class="question-title mt-3">' + q.title + '</h3>' +
                    '<p class="question-body">' + q.description + '</p>' +
                    '<div class="question-foot">' +
                        '<div class="tags-block">' +
                            getTags(q.tags) +
                        '</div>' +
                        '<div class="user-info">' +
// questionData += ('<p class="timeOfQuestion">задан <span>' + q.persistDateTime - new Date($.now()) + '</span> минут назад</p>');
                            '<p class="user-name">' + q.userDto.fullName + '</p>' +
                        '</div>' +
                    '</div>' + //question-foot
                 '</div>'  + //text-block
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