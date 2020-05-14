function putNewAnswer(answerObj ,id) {
    $.ajax({
        url: '/{answerId}/' + id,
        method: "PUT",
        data: answerObj,
        contentType: 'application/json; charset=utf-8',
        success:function () {

        },
        error: function (error) {
            alert(error);
        }
    })
}