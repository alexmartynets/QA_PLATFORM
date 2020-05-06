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
                    // location.assign("/login");
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

    // проверка формы регистрации
    validateForm() {
        let username = $("#username").val();
        let email = $('#email').val();
        let password = $('#password').val();

        if (username.length < 1) {
            $("#username").tooltip('show');
            return false;
        }
        if (email.length < 1) {
            $('#email').tooltip('show');
            return false;

        } else {
            let regEx = /@/;
            let validEmail = regEx.test(email);
            if (!validEmail) {
                $('#email').tooltip('show');
                return false;
            }
        }
        if (password.length < 8) {
            $('#password').tooltip('show');
            return false;

        } else {
            let regEx = /[0-9][A-Za-z]/;
            let validPassword = regEx.test(password);
            if (!validPassword) {
                $('#password').tooltip('show');
                return false;
            }
        }
        return true;
    }
}
