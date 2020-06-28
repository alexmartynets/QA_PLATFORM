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
    let questionData = '';

    questionData += ('<li class="list-group-item">');
    questionData += ('<div class="question-block container mt-4">');
    questionData += ('<div class="voice-block">');
    questionData += ('<div class="voices voice-item">');
    questionData += ('<p class="points">' + q.countValuable + '</p>');
    questionData += ('<span class="smallHeader">Голосов</span>');
    questionData += ('</div>');
    questionData += ('<div class="answers voice-item">');
    questionData += ('<p class="points">' + q.countAnswer + '</p>');
    questionData += ('<span class="smallHeader">Ответов</span>');
    questionData += ('</div>');
    questionData += ('<div class="voice-result">');
    questionData += ('<p ><span class="viewCount">' + q.viewCount + ' показов</span></p>');
    questionData += ('</div>');
    questionData += ('</div>');
    questionData += ('<div class="text-block">');
    questionData += ('<h3 class="question-title mt-3">' + q.title + '</h3>');
    questionData += ('<p class="question-body">' + q.description + '</p>');
    questionData += ('<div class="question-foot">');
    questionData += ('<div class="tags-block">');
    questionData += (getTags(q.tags));
    questionData += ('</div>');
    questionData += ('<div class="user-info">');
    // questionData += ('<p class="timeOfQuestion">задан <span>' + q.persistDateTime - new Date($.now()) + '</span> минут назад</p>');
    questionData += ('<p class="user-name">' + q.userDto.fullName + '</p>');
    questionData += ('</div>');
    questionData += ('</div>'); //question-foot
    questionData += ('</div>'); //text-block
    questionData += ('</div>');
    questionData += ('</li>');

    return questionData;
}

function getTags(list) {
    let tags ="";
    for (let i = 0; i < list.length; i++){
        tags += ('<button type="button" class="btn btn-primary btn-sm mr-1">'+ list[i].name +'</button>')
    }
    return tags;
}