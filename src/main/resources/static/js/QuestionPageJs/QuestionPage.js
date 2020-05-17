$(document).ready(function () {
    let newAnswer  = {};
    $('#btnSendAnswer').click(function (id) {
        newAnswer .text = $('#summernote').val();
        let answerObj = JSON.stringify(newAnswer);
        putNewAnswer(answerObj , id);
    });
    $('#btnUpCountPlus').click(function (id) {
        id = 1;
        putCountValuablePlus(id);
    });
    $('#btnDownCountMinus').click(function (id) {
        id = 1;
        putCountValuableMinus(id);
    });
});


function getNameQuestion(id) {
    id = 1;
    getQuestion(id);
    getTextOfQuestion(id);
}


