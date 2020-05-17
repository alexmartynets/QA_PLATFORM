function putCountValuableMinus(id) {
    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            let count = data.countValuable;
            count--;
            data.countValuable = count;
            let questionDto = JSON.stringify(data);

            $.ajax({
                url: '/api/user/question/' + data.id,
                method: 'PUT',
                data: questionDto,
                contentType: 'application/json; charset=utf-8',
                success:function () {
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