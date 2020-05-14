function getQuestionName(id) {

    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            let titleQuestion = data.title;
            document.getElementById("NameAnswer").innerHTML = titleQuestion;
        },
        error: function () {
            alert("Текст название вопроса ещё не загружен");
        }
    })
}