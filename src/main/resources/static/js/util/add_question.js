function add() {
    let id = 1;         // нужно брать из html id user-а (пока отправляет 1)
    let title = $("#title").val();
    let body = $("#summernote").val();
    let tag = $("#tag").val().split(",");

    let questionDto = {
        "id":id,
        "title":title,
        "description" : body,
        "tags": tag
    };

    $.post(
        "http://localhost:5557/rest/question", {
           questionDto: questionDto
        },
        onAjaxSuccess
    );

    function onAjaxSuccess(data) {
    }
};
