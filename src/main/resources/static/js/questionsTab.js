$(document).ready(function () {

    getQuestionsSortedByVotes();

    // getListOfTags is a method from mainPage.js
    getListOfTags();

    $('#btnWatch').click(function () {
        let userTagsDto = {};
        userTagsDto.WatchTagName = $('#watch').val();
        $.cookie('WatchTagsCookie', userTagsDto.WatchTagName);
        let watchCookie = userTagsDto.WatchTagName = $('#watch').val();
        $.cookie(watchCookie+"W", userTagsDto.WatchTagName);
    });

    $('#btnIgnore').click(function () {
        let userTagsDto = {};
        userTagsDto.IgnoreTagName = $('#ignore').val();
        $.cookie('IgnoreTagsCookie', userTagsDto.IgnoreTagName);
        let ignoreCookie = userTagsDto.IgnoreTagName = $('#ignore').val();
        $.cookie(ignoreCookie+"I", userTagsDto.IgnoreTagName);
    });
})

function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

function getQuestionsSortedByVotes() {
    let tableBodyWatchTag = $('#tblWatchTag tbody');
    tableBodyWatchTag.empty();
    let tableBodyIgnoreTag = $('#tblIgnoreTag tbody');
    tableBodyIgnoreTag.empty();
    let cookies =  document.cookie.split(';');
    for(let i = 0; i < cookies.length; i++) {
        let fullCookie = cookies[i];
        let NameAndTag = fullCookie.split("=");
        let nameTag = NameAndTag[1];
        if (NameAndTag[0].endsWith("W")){
            $.cookie('WatchTagsCookie', nameTag);
            tableBodyWatchTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${nameTag}<span class="badge badge-pill badge-light ml-2" style="background-color: #d1e5f1" title="Удалить метку" onclick="$.removeCookie(${NameAndTag[0]}); window.location.reload();">X</span></div></button><br>`);
        }
        if (NameAndTag[0].endsWith("I")){
            tableBodyIgnoreTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${nameTag}<span class="badge badge-pill badge-light ml-2" style="background-color: #d1e5f1" title="Удалить метку" onclick="$.removeCookie(${NameAndTag[0]}); window.location.reload();">X</span></div></button><br>`);
            $.cookie('IgnoreTagsCookie', nameTag);
        }

    }



    let watchCookies = $.cookie('WatchTagsCookie');
    let ignoreCookies = $.cookie('IgnoreTagsCookie');
    if (watchCookies === undefined) {
        $.cookie('WatchTagsCookie', null);
    }
    if (ignoreCookies === undefined) {
        $.cookie('IgnoreTagsCookie', null);
    }
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
        $.ajax({
            url: '/api/user/question/watchAndIgnoreTag',
            type: 'POST',
            headers: {
                "Content-Type": "application/json",
                'WatchTagName': $.cookie('WatchTagsCookie'),
                'IgnoreTagsName': $.cookie('IgnoreTagsCookie')
            },
            success: function (listOfQuestion) {
              /*  if (watchCookies  !== undefined && watchCookies !== null) {
                    let tableBodyWatchTag = $('#tblWatchTag tbody');
                    tableBodyWatchTag.empty();
                    tableBodyWatchTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${watchCookies}<span class="badge badge-pill badge-light ml-2" style="background-color: #d1e5f1" title="Удалить метку" onclick="$.removeCookie('WatchTagsCookie'); window.location.reload();">X</span></div></button>`);
                }*/
              /*  if (ignoreCookies !== undefined && ignoreCookies !== null) {
                    let tableBodyIgnoreTag = $('#tblIgnoreTag tbody');
                    tableBodyIgnoreTag.empty();
                    tableBodyIgnoreTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${ignoreCookies}<span class="badge badge-pill badge-light ml-2" style="background-color: #d1e5f1" title="Удалить метку" onclick="$.removeCookie('IgnoreTagsCookie'); window.location.reload();">X</span></div></button>`);

                }*/
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
            '</div>'
        return questionData;
    } else if (q.watchTag === true) {
        let questionData =
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
            '</div>'
        return questionData;
    } else if (q.watchTag === true && q.ignoreTag === true) {
        let questionData =
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
            '</div>'
        return questionData;
    } else {
        let questionData =
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
            '</div>'
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