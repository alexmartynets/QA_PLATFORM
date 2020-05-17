function putCountValuablePlus(id) {
    $.ajax({
        url: '/api/user/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {

            let count = data.countValuable;
            count++;
            let countObj = JSON.stringify(count);

            $.ajax({
                url: '/api/user/question/' + id,
                method: "POST",
                data: countObj,
                contentType: 'application/json; charset=utf-8',
                success:function () {

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