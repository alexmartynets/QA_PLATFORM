$(document).ready(function () {

    getQuestionsSortedByVotes();
    getListOfTags();

    $('#btnWatch').click(function () {
        let userTagsDto = {};
        userTagsDto.WatchTagName = $('#watch').val();
        let watchCookie = userTagsDto.WatchTagName;
        $.cookie(watchCookie + "W", userTagsDto.WatchTagName);
    });

    $('#btnIgnore').click(function () {
        let userTagsDto = {};
        userTagsDto.IgnoreTagName = $('#ignore').val();
        let ignoreCookie = userTagsDto.IgnoreTagName;
        $.cookie(ignoreCookie + "I", userTagsDto.IgnoreTagName);
    });
})


function HeaderCookie() {
    let result = "";
    let arr = [];
    let cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        let fullCookie = cookies[i];
        let NameAndTag = fullCookie.split("=");
        let nameTag = NameAndTag[1];
        if (NameAndTag[0].endsWith("W")) {
            result = nameTag + "W";
        } else if (NameAndTag[0].endsWith("I")) {
            result = nameTag + "I";
        }
        arr.push(result);
    }
    return arr;
}

function getQuestionsSortedByVotes() {
    let tableBodyWatchTag = $('#tblWatchTag tbody');
    tableBodyWatchTag.empty();
    let tableBodyIgnoreTag = $('#tblIgnoreTag tbody');
    tableBodyIgnoreTag.empty();
    let cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        let fullCookie = cookies[i];
        let NameAndTag = fullCookie.split("=");
        let nameTag = NameAndTag[1];
        if (NameAndTag[0].endsWith("W")) {
            if (NameAndTag[0].startsWith(" ")) {
                NameAndTag[0].substring(1);
                let x = NameAndTag[0].substring(1);
                tableBodyWatchTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${nameTag}<span class="badge badge-pill badge-light ml-2" style="background-color: #d1e5f1" title="Удалить метку" onclick="$.removeCookie('${x}'); window.location.reload();">X</span></div></button><br>`);
            } else {
                tableBodyWatchTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${nameTag}<span class="badge badge-pill badge-light ml-2" style="background-color: #d1e5f1" title="Удалить метку" onclick="$.removeCookie('${NameAndTag[0]}'); window.location.reload();">X</span></div></button><br>`);
            }
        }
        if (NameAndTag[0].endsWith("I")) {
            if (NameAndTag[0].startsWith(" ")) {
                NameAndTag[0].substring(1);
                let x = NameAndTag[0].substring(1);
                tableBodyIgnoreTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${nameTag}<span class="badge badge-pill badge-light ml-2" style="background-color: #d1e5f1" title="Удалить метку" onclick="$.removeCookie('${x}'); window.location.reload();">X</span></div></button><br>`);
            } else {
                tableBodyIgnoreTag.append(`<button type="button" class="btn btn-light btn-sm mr-1" style="background-color: #e1ecf4"><div style="color: #007bff">${nameTag}<span class="badge badge-pill badge-light ml-2" style="background-color: #d1e5f1" title="Удалить метку" onclick="$.removeCookie('${NameAndTag[0]}'); window.location.reload();">X</span></div></button><br>`);
            }
        }
    }

    let arr = HeaderCookie();
    if (arr === null) {
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
                'arr': arr,
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
            '<div class="question-block container mt-4 bg-light text-dark" style="opacity: 35%">' +
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