function getQuestionName(id) {

    $.ajax({
        url: '/api/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            let titleQuestion = val(data.title);
            document.getElementById("NameAnswer").innerHTML = titleQuestion;
        },
        error: function (error) {
            alert(error);
        }
    })
}