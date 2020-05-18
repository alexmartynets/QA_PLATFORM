function putCountValuablePlus(id) {
    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            let count = data.countValuable;
            count++;
            data.countValuable = count;
            let questionDto = JSON.stringify(data);

            $.ajax({
                url: '/api/user/question/' + data.id,
                method: 'PUT',
                data: questionDto,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success:function (data) {
                    document.getElementById("countValuableQuestion").innerHTML = data.countValuable;
                    alert("сработало");
                },
                error: function (questionDto) {
                    alert(questionDto);
                }
            })
        },
        error: function () {
            alert("Не получилось");
        }
    })
}