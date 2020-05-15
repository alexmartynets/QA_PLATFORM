function getQuestionText(id) {

    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            document.getElementById("tblQuestionText").innerHTML = data.description;
            document.getElementById("countValuableQuestion").innerHTML = data.countValuable;
        },
        error: function () {
            alert("Текст вопроса не загружен");
        }
    })
}