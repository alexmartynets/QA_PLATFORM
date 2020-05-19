$(document).ready(function (id) {
    let newAnswer  = {};

    $('#btnSendAnswer').click(function (id) {
        id = 1;
        newAnswer .text = $('#summernote').val();
        let answerDTO = JSON.stringify(newAnswer);
        putNewAnswer(answerDTO , id);
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


