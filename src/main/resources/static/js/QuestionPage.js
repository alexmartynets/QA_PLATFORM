$(document).ready(function () {
    let newAnswer  = {};
    $('#btnSendAnswer').click(function (id) {
        newAnswer .text = $('#Textarea').val();
        let answerObj = JSON.stringify(newAnswer);
        putNewAnswer(answerObj , id);
    })
});

function getNameQuestion() {

    let countQuestion = "0";
    document.getElementById("countValuableQuestion").innerHTML = countQuestion;

    let countAnswer = "1";
    document.getElementById("countValuableAnswer").innerHTML = countAnswer;


    getQuestionName();
    getQuestionText();
    getTextOfQuestion();
}


