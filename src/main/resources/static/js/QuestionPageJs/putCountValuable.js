function putCountValuable(countObj,id) {
    $.ajax({
        url: '/api/user/question/'+ id +'/answer/',
        method: "POST",
        data: countObj,
        contentType: 'application/json; charset=utf-8',
        success:function () {

        },
        error: function (error) {
            alert(error);
        }
    })
}