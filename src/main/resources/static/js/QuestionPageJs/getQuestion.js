
function getQuestion(id) {

    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            document.getElementById("NameAnswer").innerHTML = data.title;
            document.getElementById("persistDateTime").innerHTML = data.persistDateTime;
            document.getElementById("viewCount").innerHTML = data.viewCount;
            document.getElementById("countAnswer").innerHTML = data.countAnswer;
            document.getElementById("tblQuestionText").innerHTML = data.description;
            document.getElementById("countValuableQuestion").innerHTML = data.countValuable;
        },
        error: function () {
            alert("Ошибка загрузки question");
        }
    })
}