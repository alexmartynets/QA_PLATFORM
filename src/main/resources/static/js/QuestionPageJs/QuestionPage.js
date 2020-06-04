$(document).ready(function (id) {
    id = 1;

    $('[data-toggle="popover"]').popover({});

    $("[data-toggle=popover]")
        .popover({html: true});

    $('#btnPopover').click(function () {
        putHref();
    });

    $('#btnSendAnswer').click(function (id) {
        id = 1;
        let answerDTO = {};
        answerDTO.text = $('#summernote').val();
        putNewAnswer(id,answerDTO);
    });
    $('#addComment').click(function (id) {
        id = 1;
        let commentDto = {};
        commentDto.text = $('#textarea2').val();
        addComment(id,commentDto);
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
        getTextOfQuestion(id);
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



