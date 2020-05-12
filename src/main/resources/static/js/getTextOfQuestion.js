function getTextOfQuestion(id) {
    let textOfAnswer = "Описание ответа";
    document.getElementById("tblTextOfQuestion").innerHTML = textOfAnswer;
    $.ajax({
        url: '/api/user/question/{questionId}/answer/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            $(data).each(function (index, val) {
                let textOfAnswer = val(data.htmlBody);
                document.getElementById("tblQuestionText").innerHTML = textOfAnswer;
            })
        },
        error: function (error) {
            alert(error);
        }
    })
}