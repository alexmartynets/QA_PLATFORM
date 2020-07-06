$(document).ready(function () {

    getQuestionsSortedByVotes();

    // getListOfTags is a method from mainPage.js
    getListOfTags();

    $('#btnWatch').click(function () {
        let userTagsDto = {};
        userTagsDto.WatchTagName = $('#watch').val();
        $.cookie('WatchTagsCookie', userTagsDto.WatchTagName);

    });

    $('#btnIgnore').click(function () {
        let userTagsDto = {};
        userTagsDto.IgnoreTagName = $('#ignore').val();
        $.cookie('IgnoreTagsCookie', userTagsDto.IgnoreTagName);

    });
})

function getQuestionsSortedByVotes() {
    let watchCookies = $.cookie('WatchTagsCookie');
    let ignoreCookies = $.cookie('IgnoreTagsCookie');
    if (watchCookies === undefined && ignoreCookies === undefined) {
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
    } else {
        if ( watchCookies !== undefined ) {
            let tableBodyWatchTag = $('#tblWatchTag tbody');
            tableBodyWatchTag.empty();
            tableBodyWatchTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${watchCookies}</div></button>`);
        }
       if ( ignoreCookies !== undefined ){
        let tableBodyIgnoreTag = $('#tblIgnoreTag tbody');
        tableBodyIgnoreTag.empty();
        tableBodyIgnoreTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${ignoreCookies}</div></button>`);
       }
        if ( watchCookies === undefined ){
            $.cookie('WatchTagsCookie', "null");
        }
        if ( ignoreCookies === undefined ){
            $.cookie('IgnoreTagsCookie', "null");
        }
        $.ajax({
            url: '/api/user/question/watchAndIgnoreTag',
            type: 'POST',
            headers: {
                "Content-Type": "application/json",
                'WatchTagName': $.cookie('WatchTagsCookie'),
                'IgnoreTagsName': $.cookie('IgnoreTagsCookie')
            },
            success: function (listOfQuestion) {
                $.each(listOfQuestion, function (i, q) {
                    $('#getQuestionsQ').append(fillQuestionBlock(q));
                });
            }
        })
    }
}

function fillQuestionBlock(q) {

    if (q.ignoreTag === true) {
        let questionData =
            '<li class="list-group-item">' +
            '<div class="question-block container mt-4" style="background-color: #fdf7e3">' +
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
            '</div>' + //text-block
            '<div class="user-info">' +
            // '<p class="timeOfQuestion">задан <span>' + q.persistDateTime - new Date($.now()) + '</span> минут назад</p>' +
            '<p class="user-name">' + q.userDto.fullName + '</p>' +
            '</div>' +
            '</div>' +
            '</li>'
        return questionData;
    } else if (q.watchTag === true) {
        let questionData =
            '<li class="list-group-item">' +
            '<div class="question-block container mt-4" style="background-color: #fdf7e3">' +
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
            '</div>' + //text-block
            '<div class="user-info">' +
            // '<p class="timeOfQuestion">задан <span>' + q.persistDateTime - new Date($.now()) + '</span> минут назад</p>' +
            '<p class="user-name">' + q.userDto.fullName + '</p>' +
            '</div>' +
            '</div>' +
            '</li>'
        return questionData;
    } else if (q.watchTag === true && q.ignoreTag === true) {
        let questionData =
            '<li class="list-group-item">' +
            '<div class="question-block container mt-4" style="background-color: #fdf7e3">' +
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
            '</div>' + //text-block
            '<div class="user-info">' +
            // '<p class="timeOfQuestion">задан <span>' + q.persistDateTime - new Date($.now()) + '</span> минут назад</p>' +
            '<p class="user-name">' + q.userDto.fullName + '</p>' +
            '</div>' +
            '</div>' +
            '</li>'
        return questionData;
    } else {
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
            '</div>' + //text-block
            '<div class="user-info">' +
            // '<p class="timeOfQuestion">задан <span>' + q.persistDateTime - new Date($.now()) + '</span> минут назад</p>' +
            '<p class="user-name">' + q.userDto.fullName + '</p>' +
            '</div>' +
            '</div>' +
            '</li>'
        return questionData;
    }
}

function getTags(list) {
    let tags = "";
    for (let i = 0; i < list.length; i++) {
        tags += ('<button type="button" class="btn btn-primary btn-sm mr-1">' + list[i].name + '</button>')
    }
    return tags;
}