function getQuestion(id) {

    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            let tableBody = $('#tags tbody');
            tableBody.empty();

            $(data).each(function (index, val) {
                let userInfoDto = val.userDto;
                let tags = val.tags;
                let convertPersistDateTime = convertDate(data.persistDateTime);
                let convertLastUpdateDateTime = convertDate(userInfoDto.lastUpdateDateTime);
                let convertUserLastUpdateDateTimeToString = convertDateToString(userInfoDto.lastUpdateDateTime);
                let convertUserPersistDateTimeToString = convertDateToString(userInfoDto.persistDateTime);
                let convertPersistDateTimeToString = convertDateToString(data.persistDateTime);

                $(tags).each(function (index, val) {
                    tableBody.append(`<small class=" ml-2 " style="background-color: #e1ecf4"><a href="#" class="comment-user">${val.name}</a></small>`);
                });

                document.getElementById("NameAnswer").innerHTML = data.title;
                document.getElementById("persistDateTime").innerHTML = convertPersistDateTime;
                document.getElementById("viewCount").innerHTML = data.viewCount;
                document.getElementById("tblQuestionText").innerHTML = data.description;
                document.getElementById("countValuableQuestion").innerHTML = data.countValuable;
                document.getElementById("persistDateTimeUser").innerHTML = convertUserPersistDateTimeToString;
                document.getElementById("InfoUser").innerHTML = userInfoDto.fullName;
                document.getElementById("InfoUserReputation").innerHTML = userInfoDto.reputationCount;
                document.getElementById("lastUpdateDateTime").innerHTML = convertLastUpdateDateTime;
                document.getElementById("persistDateTimeTitle").title = convertPersistDateTimeToString;
                document.getElementById("persistDateTime").title = convertPersistDateTimeToString;
                document.getElementById("lastUpdateDateTime").title = convertUserLastUpdateDateTimeToString;
                document.getElementById("lastUpdateDateTime2").title = convertUserLastUpdateDateTimeToString;
                document.getElementById("viewCountTitle").title = data.viewCount;
                document.getElementById("btnPopover").title = "короткая постоянна ссылка на этот вопрос";
            })
        },
        error: function () {
            alert("Ошибка загрузки question");
        }
    })
}

function getQuestionComment(id) {

    $.ajax({
        url: '/api/user/question/' + id + '/comment/',
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            let tableBody = $('#tableComment tbody');
            tableBody.empty();
            $(data).each(function (index, val) {
                let persistDateTime = convertDateToString(val.persistDateTime);
                tableBody.append(`<tr><td style="padding: 0"><h>${val.text}</h>&nbsp;–&nbsp;<a href="#" class="comment-user">${val.fullName}</a><h>&nbsp;${persistDateTime}</h></td></tr>`);
            });
        },
        error: function () {
            alert("Текст ответа не загружен");
        }
    })
}

function putAnswerCountValuableMinus(id, questionId, countValuable, isHelpful) {
    let answerIdMinus = id;
    $.ajax({
        url: '/api/user/question/' + questionId + '/answer/',
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            $(data).each(function (index, val) {
                if (val.id == id) {
                    val.questionId = questionId;
                    let correctID = id;
                    val.id = correctID;
                    val.isHelpful = isHelpful;
                    countValuable--;
                    val.countValuable = countValuable;
                    let correctData = val;


                    let answerDTO = JSON.stringify(correctData);
                    $.ajax({
                        url: '/api/user/question/' + questionId + '/answer/' + answerIdMinus,
                        method: 'PUT',
                        data: answerDTO,
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            document.getElementById("answerCountValuable").innerHTML = data.countValuable;
                            getTextOfQuestion(questionId);
                        },
                        error: function () {
                            alert("не сработало");
                        }
                    })
                }
            });
        },
        error: function () {
            alert("Не получилось");
        }
    })
}

function putAnswerCountValuablePlus(id, questionId, countValuable, isHelpful) {
    let answerIdPlus = id;
    $.ajax({
        url: '/api/user/question/' + questionId + '/answer/',
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            $(data).each(function (index, val) {
                if (val.id == id) {
                    val.questionId = questionId;
                    let correctID = id;
                    val.id = correctID;
                    val.isHelpful = isHelpful;
                    countValuable++;

                    val.countValuable = countValuable;
                    let correctData = val;


                    let answerDTO = JSON.stringify(correctData);
                    $.ajax({
                        url: '/api/user/question/' + questionId + '/answer/' + answerIdPlus,
                        method: 'PUT',
                        data: answerDTO,
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            document.getElementById("answerCountValuable").innerHTML = data.countValuable;
                            getTextOfQuestion(questionId);
                        },
                        error: function () {
                            alert("не сработало");
                        }
                    })
                }
            });
        },
        error: function () {
            alert("Не получилось");
        }
    })
}

function putCountValuableMinus(id) {
    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            let count = data.countValuable;
            count--;
            data.countValuable = count;
            let questionDto = JSON.stringify(data);

            $.ajax({
                url: '/api/user/question/' + data.id,
                method: 'PUT',
                data: questionDto,
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    document.getElementById("countValuableQuestion").innerHTML = data.countValuable;

                },
                error: function (error) {
                    alert(error);
                }
            })
        },
        error: function () {
            alert("Не получилось");
        }
    })
}

function putCountValuablePlus(id) {
    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            let count = data.countValuable;
            count++;
            data.countValuable = count;
            let questionDto = JSON.stringify(data);

            $.ajax({
                url: '/api/user/question/' + data.id,
                method: 'PUT',
                data: questionDto,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    document.getElementById("countValuableQuestion").innerHTML = data.countValuable;
                },
                error: function (error) {
                    alert(error);
                }
            })
        },
        error: function () {
            alert("Не получилось");
        }
    })
}

function putNewAnswer(id, answerDTO) {
    let questionId = id;
    $.ajax({
        url: '/api/user/question/' + questionId + '/answer/',
        method: 'POST',
        data: JSON.stringify(answerDTO), questionId,
        contentType: 'application/json; charset=utf-8',
        success: function () {
        },
        error: function () {
            alert("Не корректно отправлен ответ");
        }
    })
}

function putHref() {

    let href = window.location.href;
    document.getElementById("hrefPage").innerHTML = href;
}

function convertDate(date) {
    let date1 = new Date();
    let date2 = new Date(date);
    let diff = Math.floor((Date.parse(date1) - Date.parse(date2)) / 86400000);
    let result = "";
    if (diff === 0) {
        result = "сегодня";
    } else if (diff === 1 && diff < 2) {
        result = "вчера";
    } else if (diff === 2 && diff < 3) {
        result = "позавчера";
    } else if (diff > 2 && diff < 31) {
        result = diff + " " + "дней назад";
    } else if (diff > 31 && diff < 58) {
        result = "месяц назад";
    } else if (diff > 31 && diff < 360) {
        let month1 = date1.getMonth();
        let month = date2.getMonth();
        let resultMonth = month1 - month;
        result = resultMonth + " " + "месяца назад";
    } else if (diff > 360 && diff < 720) {
        result = "год назад";
    } else if (diff > 720 && diff < 1080) {
        result = "несколько лет назад";
    } else if (diff > 1080) {
        result = "много лет назад";
    }
    return result;
}

function getTextOfQuestion(id) {

    $.ajax({
        url: '/api/user/question/' + id + '/answer/',
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            $.ajax({
                url: '/api/user/answer/' + id + '/comment/',
                method: 'GET',
                dataType: 'json',
                success: function (dataAnswerComment) {

                    let tableBody = $('#tblTextOfQuestion tbody');
                    tableBody.empty();
                    let num = 0;
                    $(data).each(function (index, val) {
                        $(val).each(function (index, value) {
                            num++;
                            document.getElementById("countAnswer").innerHTML = num;
                            let userInfoDto = value.userDto;
                            let href = window.location.href;
                            let questionId = val.questionId;
                            let persistDateTime = convertDateToString(val.persistDateTime);
                            tableBody.append(`<tr>
        <td  width="50" rowspan="1"><button onclick="putAnswerCountValuablePlus(${val.id},${questionId},${val.countValuable},${val.isHelpful})" class=" btn btn-link- outline-dark"
                                                    title="Ответ полезен">
                                                <svg class="bi bi-caret-up-fill" width="1em" height="1em"
                                                     viewBox="0 0 16 16"
                                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M7.247 4.86l-4.796 5.481c-.566.647-.106 1.659.753 1.659h9.592a1 1 0 00.753-1.659l-4.796-5.48a1 1 0 00-1.506 0z"/>
                                                </svg>
                                            </button>

                                            <div id="answerCountValuable" class=" ml-3 " >${val.countValuable}</div>

                                            <button onclick="putAnswerCountValuableMinus(${val.id},${questionId},${val.countValuable},${val.isHelpful})" class="btn btn-link- outline-dark"
                                                    title="Ответ не является полезеным">
                                                <svg class="bi bi-caret-down-fill" width="1em" height="1em"
                                                     viewBox="0 0 16 16"
                                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 01.753 1.659l-4.796 5.48a1 1 0 01-1.506 0z"/>
                                                </svg>                                                                      
                                            </button>
                                            <div class="pb-3  ml-1">${isHelpful(val.isHelpful)}</div></td>
        <td>${val.htmlBody}</td>
    </tr>
    <tr>
        <td colspan="2"> <button type="button" class="btn btn-link" data-container="body"                          
                                            data-toggle="popover" data-placement="bottom"
                                            data-content="<a>${href}</a>"
                                            data-html="true"
                                            title="Поделиться ссылкой на ответ">
                                        Поделиться
                                    </button>
                                    <button href="#" class="btn btn-link ">
                                        улучшить этот ответ
                                    </button>
                                    <span style="text-align:right;float:right; background-color: #e1ecf4"
                                          class="badge badge"><h style="text-align: left;float: left;">ответ дан:<h class=" ml-1 " >${persistDateTime}</h></h><br><div><svg style="text-align: left;float: left"
                                            xmlns="http://www.w3.org/2000/svg" viewBox="0 0 12 16" width="12"
                                            height="16"><path fill-rule="evenodd"
                                                              d="M12 14.002a.998.998 0 01-.998.998H1.001A1 1 0 010 13.999V13c0-2.633 4-4 4-4s.229-.409 0-1c-.841-.62-.944-1.59-1-4 .173-2.413 1.867-3 3-3s2.827.586 3 3c-.056 2.41-.159 3.38-1 4-.229.59 0 1 0 1s4 1.367 4 4v1.002z"></path></svg><h
                                            class=" ml-1 ">${userInfoDto.fullName}</h><br><h class=" ml-2 " style="text-align: left;float: left;" title="уровень репутации">${userInfoDto.reputationCount}</h></div></span></td>
    </tr>
     <tr>
     <td style="padding: 0" width="50" rowspan="1"></td>
            <td style="padding: 0">${isAnswerComment(val.id, dataAnswerComment)}</td>
    </tr>
 <tr>
        <td style="padding: 0" colspan="2">
         <a class="btn btn-link" title="Используйте комментарии для запроса дополнительной информации или предложения улучшений. Избегайте публикации ответа на вопросы в комментариях." data-toggle="collapse" href="#collapseComment${val.id}" role="button" aria-expanded="false" aria-controls="collapseComment${val.id}"> 
               добавить комментарий </a>
               <div class="collapse" id="collapseComment${val.id}">
  <div class="card card-body">
    <label for="textarea1">Оставьте свой комментарий</label>
    <textarea class="form-control" id="textarea1" rows="3"></textarea>
  </div>
  <button onclick="putComment(${val.id},${questionId})" type="button" class="btn btn-primary mt-3"
                                            style="text-align:left;float:left;">
                                        добавить комментарий
                                    </button>
</div><hr class="my-0" color="gainsboro"></td>                                                                          
    </tr>`);
                            $(popover());
                        });
                    });
                },

                error: function () {
                    alert("Текст ответа не загружен");
                }

            })
        },
        error: function () {
            alert("Комментарий ответа не загружен");
        }
    })
}

function getSortDateTextOfQuestion(id) {

    $.ajax({
        url: '/api/user/question/' + id + '/answer/sort/date',
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            $.ajax({
                url: '/api/user/answer/' + id + '/comment/',
                method: 'GET',
                dataType: 'json',
                success: function (dataAnswerComment) {

                    let tableBody = $('#tblTextOfQuestion tbody');
                    tableBody.empty();
                    let num = 0;
                    $(data).each(function (index, val) {
                        $(val).each(function (index, value) {
                            num++;
                            document.getElementById("countAnswer").innerHTML = num;
                            let userInfoDto = value.userDto;
                            let href = window.location.href;
                            let questionId = val.questionId;
                            let persistDateTime = convertDateToString(val.persistDateTime);
                            tableBody.append(`<tr>
        <td width="50" rowspan="1"><button onclick="putAnswerCountValuablePlus(${val.id},${questionId},${val.countValuable},${val.isHelpful})" class=" btn btn-link- outline-dark"
                                                    title="Ответ полезен">
                                                <svg class="bi bi-caret-up-fill" width="1em" height="1em"
                                                     viewBox="0 0 16 16"
                                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M7.247 4.86l-4.796 5.481c-.566.647-.106 1.659.753 1.659h9.592a1 1 0 00.753-1.659l-4.796-5.48a1 1 0 00-1.506 0z"/>
                                                </svg>
                                            </button>

                                            <div id="answerCountValuable" class=" ml-3 " >${val.countValuable}</div>

                                            <button onclick="putAnswerCountValuableMinus(${val.id},${questionId},${val.countValuable},${val.isHelpful})" class="btn btn-link- outline-dark"
                                                    title="Ответ не является полезеным">
                                                <svg class="bi bi-caret-down-fill" width="1em" height="1em"
                                                     viewBox="0 0 16 16"
                                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 01.753 1.659l-4.796 5.48a1 1 0 01-1.506 0z"/>
                                                </svg>                                                                      
                                            </button>
                                            <div class="pb-3  ml-1">${isHelpful(val.isHelpful)}</div></td>
        <td>${val.htmlBody}</td>
    </tr>
    <tr>
        <td colspan="2"> <button type="button" class="btn btn-link" data-container="body"                          
                                            data-toggle="popover" data-placement="bottom"
                                            data-content="<a>${href}</a>"
                                            data-html="true"
                                            title="Поделиться ссылкой на ответ">
                                        Поделиться
                                    </button>
                                    <button href="#" class="btn btn-link ">
                                        улучшить этот ответ
                                    </button>
                                    <span style="text-align:right;float:right; background-color: #e1ecf4"
                                          class="badge badge"><h style="text-align: left;float: left;">ответ дан:<h class=" ml-1 ">${persistDateTime}</h></h><br><div><svg style="text-align: left;float: left"
                                            xmlns="http://www.w3.org/2000/svg" viewBox="0 0 12 16" width="12"
                                            height="16"><path fill-rule="evenodd"
                                                              d="M12 14.002a.998.998 0 01-.998.998H1.001A1 1 0 010 13.999V13c0-2.633 4-4 4-4s.229-.409 0-1c-.841-.62-.944-1.59-1-4 .173-2.413 1.867-3 3-3s2.827.586 3 3c-.056 2.41-.159 3.38-1 4-.229.59 0 1 0 1s4 1.367 4 4v1.002z"></path></svg><h
                                            class=" ml-1 ">${userInfoDto.fullName}</h><br><h class=" ml-2 " style="text-align: left;float: left;" title="уровень репутации">${userInfoDto.reputationCount}</h></div></span></td>
    </tr>
     <tr>
     <td style="padding: 0" width="50" rowspan="1"></td>
            <td style="padding: 0">${isAnswerComment(val.id, dataAnswerComment)}</td>
    </tr>
<tr>
        <td style="padding: 0" colspan="2">
         <a class="btn btn-link" title="Используйте комментарии для запроса дополнительной информации или предложения улучшений. Избегайте публикации ответа на вопросы в комментариях." data-toggle="collapse" href="#collapseComment${val.id}" role="button" aria-expanded="false" aria-controls="collapseComment${val.id}"> 
               добавить комментарий </a>
               <div class="collapse" id="collapseComment${val.id}">
  <div class="card card-body">
    <label for="textarea1">Оставьте свой комментарий</label>
    <textarea class="form-control" id="textarea1" rows="3"></textarea>
  </div>
  <button onclick="putComment(${val.id},${questionId})" type="button" class="btn btn-primary mt-3"
                                            style="text-align:left;float:left;">
                                        добавить комментарий
                                    </button>
</div><hr class="my-0" color="gainsboro"></td>                                                                          
    </tr>`);
                            $(popover());
                        });
                    });
                },

                error: function () {
                    alert("Текст ответа не загружен");
                }

            })
        },
        error: function () {
            alert("Комментарий ответа не загружен");
        }
    })
}

function getSortReputationTextOfQuestion(id) {

    $.ajax({
        url: '/api/user/question/' + id + '/answer/sort/count',
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            $.ajax({
                url: '/api/user/answer/' + id + '/comment/',
                method: 'GET',
                dataType: 'json',
                success: function (dataAnswerComment) {


                    let tableBody = $('#tblTextOfQuestion tbody');
                    tableBody.empty();
                    let num = 0;
                    $(data).each(function (index, val) {
                        $(val).each(function (index, value) {
                            num++;
                            document.getElementById("countAnswer").innerHTML = num;
                            let userInfoDto = value.userDto;
                            let href = window.location.href;
                            let questionId = val.questionId;
                            let persistDateTime = convertDateToString(val.persistDateTime);
                            tableBody.append(`<tr>
        <td width="50" rowspan="1"><button onclick="putAnswerCountValuablePlus(${val.id},${questionId},${val.countValuable},${val.isHelpful})" class=" btn btn-link- outline-dark"
                                                    title="Ответ полезен">
                                                <svg class="bi bi-caret-up-fill" width="1em" height="1em"
                                                     viewBox="0 0 16 16"
                                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M7.247 4.86l-4.796 5.481c-.566.647-.106 1.659.753 1.659h9.592a1 1 0 00.753-1.659l-4.796-5.48a1 1 0 00-1.506 0z"/>
                                                </svg>
                                            </button>

                                            <div id="answerCountValuable" class=" ml-3 " >${val.countValuable}</div>

                                            <button onclick="putAnswerCountValuableMinus(${val.id},${questionId},${val.countValuable},${val.isHelpful})" class="btn btn-link- outline-dark"
                                                    title="Ответ не является полезеным">
                                                <svg class="bi bi-caret-down-fill" width="1em" height="1em"
                                                     viewBox="0 0 16 16"
                                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 01.753 1.659l-4.796 5.48a1 1 0 01-1.506 0z"/>
                                                </svg>                                                                      
                                            </button>
                                            <div class="pb-3  ml-1">${isHelpful(val.isHelpful)}</div></td>
        <td>${val.htmlBody}</td>
    </tr>
    <tr>
        <td colspan="2"> <button type="button" class="btn btn-link" data-container="body"                          
                                            data-toggle="popover" data-placement="bottom"
                                            data-content="<a>${href}</a>"
                                            data-html="true"
                                            title="Поделиться ссылкой на ответ">
                                        Поделиться
                                    </button>
                                    <button href="#" class="btn btn-link ">
                                        улучшить этот ответ
                                    </button>
                                    <span style="text-align:right;float:right; background-color: #e1ecf4"
                                          class="badge badge"><h style="text-align: left;float: left;">ответ дан:<h class=" ml-1 ">${persistDateTime}</h></h><br><div><svg style="text-align: left;float: left"
                                            xmlns="http://www.w3.org/2000/svg" viewBox="0 0 12 16" width="12"
                                            height="16"><path fill-rule="evenodd"
                                                              d="M12 14.002a.998.998 0 01-.998.998H1.001A1 1 0 010 13.999V13c0-2.633 4-4 4-4s.229-.409 0-1c-.841-.62-.944-1.59-1-4 .173-2.413 1.867-3 3-3s2.827.586 3 3c-.056 2.41-.159 3.38-1 4-.229.59 0 1 0 1s4 1.367 4 4v1.002z"></path></svg><h
                                            class=" ml-1 ">${userInfoDto.fullName}</h><br><h class=" ml-2 " style="text-align: left;float: left;" title="уровень репутации">${userInfoDto.reputationCount}</h></div></span></td>
    </tr>
     <tr>
     <td style="padding: 0" width="50" rowspan="1"></td>
            <td style="padding: 0">${isAnswerComment(val.id, dataAnswerComment)}</td>
    </tr>
<tr>
        <td style="padding: 0" colspan="2">
         <a class="btn btn-link" title="Используйте комментарии для запроса дополнительной информации или предложения улучшений. Избегайте публикации ответа на вопросы в комментариях." data-toggle="collapse" href="#collapseComment${val.id}" role="button" aria-expanded="false" aria-controls="collapseComment${val.id}"> 
               добавить комментарий </a>
               <div class="collapse" id="collapseComment${val.id}">
  <div class="card card-body">
    <label for="textarea1">Оставьте свой комментарий</label>
    <textarea class="form-control" id="textarea1" rows="3"></textarea>
  </div>
  <button onclick="putComment(${val.id},${questionId})" type="button" class="btn btn-primary mt-3"
                                            style="text-align:left;float:left;">
                                        добавить комментарий
                                    </button>
</div><hr class="my-0" color="gainsboro"></td>                                                                          
    </tr>`);
                            $(popover());
                        });
                    });
                },

                error: function () {
                    alert("Текст ответа не загружен");
                }

            })
        },
        error: function () {
            alert("Комментарий ответа не загружен");
        }
    })
}

function isHelpful(isHelpful) {
    let x = "";
    if (isHelpful === true) {
        x = "<img src='/images/check-mark.png' width='30' height='30' alt=''>";
        return x;
    } else {
        return x;
    }
}

function isAnswerComment(id, dataAnswerComment) {
    let newText = "";
    let persistDateTime = "";
    let button = "";
    let result = "";
    let border = "";
    let convertPersistDateTime = "";
    dataAnswerComment.forEach(
        dataAnswerComment => {
            if (dataAnswerComment.id === id) {
                newText = dataAnswerComment.text;
                persistDateTime = dataAnswerComment.persistDateTime;
                button = "<a href=\"#\" class=\"comment-user\">"+dataAnswerComment.fullName+"</a>";
                border = "<hr class=\"my-0\" color=\"gainsboro\">";
                convertPersistDateTime = convertDateToString(persistDateTime);
                result = border+newText+" – "+button+" "+convertPersistDateTime+border;
                return border+newText+" – "+button+" "+convertPersistDateTime+border;
            } else {
                return result;
            }
        }
    );
    return result;
}

function popover() {
    $('[data-toggle="popover"]').popover();
    $("[data-toggle=popover]")
        .popover({html: true});
}

function convertDateToString(date) {
    let newDate = new Date(date);
    let hours = newDate.getHours();
    let minutes = newDate.getMinutes();
    let convertDate = newDate.toLocaleDateString();
    let result = convertDate + " " + hours + ":" + minutes;
    return result;
}

function putComment(id) {
    let commentDto = document.getElementById("textarea1").value;
    let answerId = id;
    $.ajax({
        url: '/api/user/answer/' + id + '/comment',
        method: 'POST',
        data: JSON.stringify({"text": commentDto}), answerId,
        contentType: 'application/json; charset=utf-8',
        success: function () {
        },
        error: function () {
            alert("Не корректно отправлен комментарий");
        }
    })
}

function addComment(id, commentDto) {
    let questionId = id;
    $.ajax({
        url: '/api/user/question/' + id + '/comment',
        method: 'POST',
        data: JSON.stringify(commentDto), questionId,
        contentType: 'application/json; charset=utf-8',
        success: function () {
        },
        error: function () {
            alert("Не корректно отправлен комментарий");
        }
    })
}
