function getQuestionName(id) {

    let te = "Название вопроса";
    document.getElementById("NameAnswer").innerHTML = te;

    $.ajax({
        url: '/api/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            let titleQuestion = val(data.title);
            document.getElementById("NameAnswer").innerHTML = titleQuestion;
        },
        error: function () {
            alert("Текст название вопроса ещё не загружен");
        }
    })
}