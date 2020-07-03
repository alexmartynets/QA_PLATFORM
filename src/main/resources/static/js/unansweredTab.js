$(document).ready(function () {

    getUnansweredQuestion();

    // getListOfTags is a method from mainPage.js
    getListOfTags();
})


function getUnansweredQuestion() {
    $.ajax({
        url: '/api/user/question/unanswered',
        type: 'GET',
        dataType: 'json',
        success: function (listOfQuestion) {
            $.each(listOfQuestion, function (i, q) {
                // fillQuestionBlock is a method from questionTab.js
                $('#getQuestionsU').append(fillQuestionBlock(q));
            });
        }
    })
}