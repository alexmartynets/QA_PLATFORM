function add() {

    let id = 1;         // нужно брать из html id user-а (пока отправляет 1)
    let title = $("#title").val();
    let body = $("#summernote").val();
    let str = $("#tag").val();
    str = str.replace(/ +/g, ' ').trim();

    console.log(str);

    let tags = str.split(",");
    console.log(tags);

    let tag;

    tags.forEach(function (item, i, tags) {
        if (i === 0) {
            tag = '{'
        }
        item = item.trim();
        tag += '[name:"' + item + '"]'
    });
    tag += '}'
    console.log(tag);
    let l = JSON.stringify(tag);
    console.log(l);

};
