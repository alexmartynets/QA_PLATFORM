jQuery(document).ready(function ($) {

    //  обработка формы регистрации
    jQuery("#sign_up").click(function (e) {
        e.preventDefault();

        let data = new Data();
        let result = data.validateForm();

        if (result) {

            let dataForm = data.collectDataForm();
            data.sendDataForm(dataForm, '/registration', 'post');
        }

        $("#registration")[0].reset();

    });
});








