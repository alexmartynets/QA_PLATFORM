function putCountValuablePlus(id) {
    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data,id) {

            let count = data.countValuable;
            count++;
            let countObj = JSON.stringify(count);

            $.ajax({
                url: '/api/user/question/' + id,
                method: "PUT",
                data: countObj,
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