$(document).ready(function (id) {
    let newAnswer = {};
    id = 1;

    $('[data-toggle="popover"]').popover({});

    $("[data-toggle=popover]")
        .popover({html: true});

    $('#btnPopover').click(function () {
        putHref();
    });

    $('#btnSendAnswer').click(function (id) {
        id = 1;
        newAnswer.text = $('#summernote').val();
        let answerDTO = JSON.stringify(newAnswer);
        putNewAnswer(answerDTO, id);
    });
    $('#btnUpCountPlus').click(function (id) {
        id = 1;
        putCountValuablePlus(id);
    });
    $('#btnDownCountMinus').click(function (id) {
        id = 1;
        putCountValuableMinus(id);
    });
    $('#SortCurrentTextOfQuestion').click(function (id) {
        id = 1;
        getSortCurrentTextOfQuestion(id);
    });
    $('#SortDateTextOfQuestion').click(function (id) {
        id = 1;
        getSortDateTextOfQuestion(id);
    });
    $('#SortReputationTextOfQuestion').click(function (id) {
        id = 1;
        getSortReputationTextOfQuestion(id);
    });
    getQuestion(id);
    getTextOfQuestion(id);
});



