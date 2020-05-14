function getQuestionName(id) {

  // let te = "Название вопроса";
  //  document.getElementById("NameAnswer").innerHTML = te;
    id = 1;
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