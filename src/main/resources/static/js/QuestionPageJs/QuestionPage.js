$(document).ready(function () {
    let newAnswer  = {};
    $('#btnSendAnswer').click(function (id) {
        newAnswer .text = $('#summernote').val();
        let answerObj = JSON.stringify(newAnswer);
        putNewAnswer(answerObj , id);
    })
});

function getNameQuestion(id) {
    id = 1;
    getQuestion(id);
    getTextOfQuestion(id);
}


