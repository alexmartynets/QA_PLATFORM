jQuery(function ($) {

    $.ajax({
        url: '/api/user/question',
        type: 'GET',
        contentType : "application/json",
        dataType : "json",
        timeout : 100000,
        success: function (data) {
            data.forEach(function (element) {
                addRow(element);
            })
        },
    });

    function addRow(data) {

        var newRow = "";
        newRow += ('<li class="list-group-item">');
        newRow += ('<a href = "#">')
        newRow += ('<button type="button" class="btn btn-outline-dark">Голосов<span class="badge badge-light">' + data.countValuable + '</span></button>');
        newRow += ('<button type="button" class="btn btn-outline-dark">Ответов<span class="badge badge-light">' + data.countAnswer + '</span></button>');
        newRow += ('<button type="button" class="btn btn-outline-dark">Показов<span class="badge badge-light">' + data.viewCount + '</span></button>');
        newRow += ('<h3>' + data.title + '</h3>')
        newRow += ('</a>')
        newRow += ('</li>')

        $("#getQuestions").append(newRow);
    }
});