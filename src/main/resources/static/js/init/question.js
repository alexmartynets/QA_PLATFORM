$(document).ready(function () {
    let url = window.location.pathname;
    let id = url.substring(url.lastIndexOf('/') + 1);

    $('[data-toggle="popover"]').popover({});

    $("[data-toggle=popover]")
        .popover({html: true});

    $('#btnPopover').click(function () {
        putHref();
    });

    $('#btnSendAnswer').click(function () {
        let answerDTO = {};
        answerDTO.htmlBody = $('#summernote').val();
        putNewAnswer(id,answerDTO);
    });
    $('#addComment').click(function () {
        let commentDto = {};
        commentDto.text = $('#textarea2').val();
        addComment(id,commentDto);
    });
    $('#btnUpCountPlus').click(function () {
        putCountValuablePlus(id);
    });
    $('#btnDownCountMinus').click(function () {
        putCountValuableMinus(id);
    });
    $('#SortCurrentTextOfQuestion').click(function () {
        getTextOfQuestion(id);
    });
    $('#SortDateTextOfQuestion').click(function () {
        getSortDateTextOfQuestion(id);
    });
    $('#SortReputationTextOfQuestion').click(function () {
        getSortReputationTextOfQuestion(id);
    });

    getQuestion(id);
    getQuestionComment(id);
    getTextOfQuestion(id);
});



