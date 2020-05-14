function getTextOfQuestion(id) {
    let textOfAnswer = "Описание ответа";
    document.getElementById("tblHeadOfAnswerQuestion").innerHTML = textOfAnswer;

    let tableBody = $('#tblTextOfQuestion tbody');
    let testT = "ответ ответ ответ ответ ответ ответ ответ ответ ответ ответ " +
        "ответ ответ ответ ответ ответ ответ ответ ответ ответ ответ " +
        "ответ ответ ответ ответ ответ ответ ответ ответ ответ ответ ";
    tableBody.append(`${testT}`);

    $.ajax({
        url: '/api/user/question/{questionId}/answer/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            $(data).each(function (index, val) {
                let textOfAnswer = val(data.htmlBody);
                document.getElementById("tblQuestionText").innerHTML = textOfAnswer;

                let tableBody = $('#tblTextOfQuestion tbody');
                tableBody.empty();
                $(data).each(function (index, val) {
                    tableBody.append(`<tr>
                        <td>${val.htmlBody}</td>       
            </tr>`);
                })
            })
        },
        error: function () {
            alert("Текст ответа ещё не загружен");
        }
    })
}