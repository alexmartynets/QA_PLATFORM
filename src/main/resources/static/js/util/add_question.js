function add() {
    let id = 1;         // нужно брать из html id user-а (пока отправляет 1)
    let title = $("#title").val();
    let body = $("#summernote").val();
    let str = $("#tag").val();
    str = str.replace(/ +/g, ' ').trim();
    let tags = str.split(",");
    let tag = new Object;
    let tag1 = new Array();
    var j = -1;
    tags.forEach(function (item, i, tags) {
        j++;
        item = item.trim();
        tag.name = item;
        tag1[j] = tag;
    });
    let user = {
        id: id
    };
    let questionDto = {
        id: null,
        title: title,
        description: body,
        tags: tag1,
        userDto: user,
        viewCount: 0,
        countValuable: 0
    };
    $.ajax({
        type: 'post',
        url: "/api/user/question",
        contentType: 'application/json',
        data: JSON.stringify(questionDto),
        dataType: 'json',
        success: function (data) {
        },
    });
};