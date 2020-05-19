function getTextOfQuestion(id) {

    $.ajax({
        url: '/api/user/question/' + id + '/answer/',
        method: 'GET',
        dataType: 'json',

        success: function (data) {


            let tableBody = $('#tblTextOfQuestion tbody');
            tableBody.empty();
            $(data).each(function (index, val) {
                tableBody.append(`<tr>
        <td width="50" rowspan="2"><button onclick="putAnswerCountValuablePlus(${val.id})" class=" btn btn-link- outline-dark"
                                                    title="Ответ полезен">
                                                <svg class="bi bi-caret-up-fill" width="1em" height="1em"
                                                     viewBox="0 0 16 16"
                                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M7.247 4.86l-4.796 5.481c-.566.647-.106 1.659.753 1.659h9.592a1 1 0 00.753-1.659l-4.796-5.48a1 1 0 00-1.506 0z"/>
                                                </svg>
                                            </button>

                                            <div id="answerCountValuable" class=" ml-3 " >${val.countValuable}</div>

                                            <button onclick="putAnswerCountValuableMinus(${val.id})" class="btn btn-link- outline-dark"
                                                    title="Ответ не является полезеным">
                                                <svg class="bi bi-caret-down-fill" width="1em" height="1em"
                                                     viewBox="0 0 16 16"
                                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 01.753 1.659l-4.796 5.48a1 1 0 01-1.506 0z"/>
                                                </svg>
                                            </button></td>
        <td>${val.htmlBody}</td>
    </tr>
    <tr>
         <td  style="background-color: gainsboro">ответ</td>
    </tr>
    <tr>
        <td colspan="2"><button type="button" class="btn btn-link" data-container="body"
                                            data-toggle="popover" data-placement="bottom" data-content="Поделиться"
                                            title="короткая постоянна ссылка на этот ответ">
                                        Поделиться
                                    </button>
                                    <button href="#" class="btn btn-link ">
                                        улучшить этот ответ
                                    </button>
                                    <span style="text-align:right;float:right; background-color: #e1ecf4"
                                          class="badge badge"><h>дан</h><h class=" ml-1 ">${val.persistDateTime}</h><div><svg
                                            xmlns="http://www.w3.org/2000/svg" viewBox="0 0 12 16" width="12"
                                            height="16"><path fill-rule="evenodd"
                                                              d="M12 14.002a.998.998 0 01-.998.998H1.001A1 1 0 010 13.999V13c0-2.633 4-4 4-4s.229-.409 0-1c-.841-.62-.944-1.59-1-4 .173-2.413 1.867-3 3-3s2.827.586 3 3c-.056 2.41-.159 3.38-1 4-.229.59 0 1 0 1s4 1.367 4 4v1.002z"></path></svg><h
                                            class=" ml-1 ">${val.userDto}</h></div></span></td>
    </tr>`);
            });
        },
        error: function () {
            alert("Текст ответа не загружен");
        }

    })
}