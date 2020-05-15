
function getQuestionName(id) {

    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            document.getElementById("NameAnswer").innerHTML = data.title;

          //   document.getElementById("Test").innerHTML = data.persistDateTime;
            // document.getElementById("repCount").innerHTML = data.reputationCount;


        },
        error: function () {
            alert("Текст название вопроса не загружен");
        }
    })
}