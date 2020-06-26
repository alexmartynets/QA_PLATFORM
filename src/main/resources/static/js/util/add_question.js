function add() {
    let id = 1;         // нужно брать из html id user-а (пока отправляет 1)
    let title = $("#title").val();
    let body = $("#summernote").val();
    if ($("#tag").val() === ""){
        alert('Заполните все поля!');
    } else {
        let str = $("#tag").val();
        str = str.replace(/ +/g, ' ').trim();
        let tags = str.split(",");
        let tag1 = new Array();
        var j = -1;
        for (item in tags) {
            let tag = new Object;
            j++;
            s = tags[item].trim();
            tag.name = s;
            tag1[j] = tag;
        }
        let user = {
            id: id
        };
        let questionDto = {
            id: null,
            title: title,
            description: body,
            tags: tag1,
            userDto: user
        };
        console.log(JSON.stringify(questionDto));
        $.ajax({
            type: 'post',
            url: "/api/user/question/",
            contentType: 'application/json',
            data: JSON.stringify(questionDto),
            dataType: 'json',
            success: function (data) {
                window.location.replace("http://localhost:5557/question/" + data);
            },
            error: function (error) {
                alert('Заполните все поля!');
            }
        });
    }
};