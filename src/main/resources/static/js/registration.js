jQuery(document).ready(function ($) {

    //  обработка формы регистрации
    jQuery("#sign_up").click(function (e) {
        e.preventDefault();

        let data = new Data();

        let dataForm = data.collectDataForm();

        data.sendDataForm(dataForm, '/#', 'post');

        $("#registration")[0].reset();

    });
});


class Data {

    constructor() {

    }

    // отправка данных на сервер
    sendDataForm(data, url, method) {
        $.ajax({
            type: method,
            url: url,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json',
            cache: false,
            async: false,
            success: function (data) {
                if (data !== null) {
                    alert('Вы зарегистрировались!');
                    // location.assign("#");
                }
            },
            error: function (xhr, status, error) {
                alert('Error - ' + xhr.status + ': ' + xhr.statusText + error);
            }
        });
    }

    // сбор данных из формы регистрации
    collectDataForm() {
        return {
            "username": $("#username").val(),
            "email": $("#email").val(),
            "password": $("#password").val()
        };
    }
}





