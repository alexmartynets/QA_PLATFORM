function getQuestionText(id) {

    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            let textOfDescription = data.description;
            document.getElementById("tblQuestionText").innerHTML = textOfDescription;

            let countQuestion = data.countValuable;
            document.getElementById("countValuableQuestion").innerHTML = countQuestion;
        },
        error: function () {
            alert("Текст вопроса ещё не загружен");
        }
    })
}