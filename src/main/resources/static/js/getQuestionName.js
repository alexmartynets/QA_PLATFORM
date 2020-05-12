function getNameQuestion1(id) {

    $.ajax({
        url: '/api/question/' + id,
        method: 'GET',
        dataType: 'json',

        success: function (data) {
            let tableBody = $('#tblQuestionName tbody');
            tableBody.empty();
            $(data).each(function (index, val) {
                tableBody.append(`<tr>
                        <td>${val.title}</td>       
            </tr>`);
            })
        },
        error: function (error) {
            alert(error);
        }
    })
}