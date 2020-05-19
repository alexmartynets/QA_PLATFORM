function putNewAnswer(answerObj,id) {
    $.ajax({
        url: '/api/user/question/'+ id +'/answer/'+ id,
        method: "POST",
        data: answerObj,
        contentType: 'application/json; charset=utf-8',
        success:function () {

        },
        error: function (error) {
            alert(error);
        }
    })
}