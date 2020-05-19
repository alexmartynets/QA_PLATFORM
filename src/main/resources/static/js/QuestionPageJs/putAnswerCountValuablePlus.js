function putAnswerCountValuablePlus(id,questionId) {
    $.ajax({
        url: '/api/user/question/' + id + '/answer/',
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            $(data).each(function (index, val) {
                val.questionId = questionId;
                let correctID = id;
                val.id = correctID;
                if(val.id === id){
            let count = val.countValuable;
            count++;
            val.countValuable = count;

            data = val;
            }});
            let answerDTO = JSON.stringify(data);
            $.ajax({
                url: '/api/user/question/' + id + '/answer/' + id,
                method: 'PUT',
                data: answerDTO,
                contentType: 'application/json; charset=utf-8',
                success:function (data) {
                    document.getElementById("answerCountValuable").innerHTML = data.countValuable;
                    alert("сработало");
                },
                error: function () {
                    alert("не сработало");
                }
            })
        },
        error: function () {
            alert("Не получилось");
        }
    })
}