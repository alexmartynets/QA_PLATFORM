function getQuestionText(id) {

  //  let t = "Текст вопроса";
  //  document.getElementById("tblQuestionText").innerHTML = t;

    let countQuestion = "0";
    document.getElementById("countValuableQuestion").innerHTML = countQuestion;

    id = 1;
    $.ajax({
        url: '/api/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            let textOfDescription = data.description;
            document.getElementById("tblQuestionText").innerHTML = textOfDescription;
        },
        error: function () {
            alert("Текст вопроса ещё не загружен");
        }
    })
}