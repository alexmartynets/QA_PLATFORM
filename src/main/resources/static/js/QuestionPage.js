$(document).ready(function () {
    let newAnswer  = {};
    getNameQuestion();
    $('#btnSendAnswer').click(function (id) {
        newAnswer .text = $('#Textarea').val();
        let answerObj = JSON.stringify(newAnswer);
        putNewAnswer(answerObj , id);
    })
});

function getNameQuestion() {

    let te = "Название вопроса";
    document.getElementById("NameAnswer").innerHTML = te;

    let tableBody = $('#tblTextOfQuestion tbody');
        let testT = "ответ ответ ответ ответ ответ ответ ответ ответ ответ ответ " +
            "ответ ответ ответ ответ ответ ответ ответ ответ ответ ответ " +
            "ответ ответ ответ ответ ответ ответ ответ ответ ответ ответ ";
        tableBody.append(`${testT}`);

    getQuestionName();
    getQuestionText();
    getTextOfQuestion();
}


