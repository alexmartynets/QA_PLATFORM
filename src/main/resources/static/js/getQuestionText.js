function getQuestionText(id) {

    let t = "Текст вопроса";
    document.getElementById("tblQuestionText").innerHTML = t;

    $.ajax({
        url: '/api/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            let textOfDescription = val(data.description);
            document.getElementById("tblQuestionText").innerHTML = textOfDescription;
        },
        error: function () {
            alert("Текст вопроса ещё не загружен");
        }
    })
}