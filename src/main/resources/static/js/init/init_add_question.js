$(document).ready(function getPage() {
    $('#summernote').summernote({
        placeholder: 'Ваш вопрос',
        tabsize: 3,
        height: 100,
        lang: 'ru-RU',
        minHeight: 100,
        toolbar: [
            //[groupname[list buttons]]         //пример
            ['style', ['bold', 'italic', 'underline']],
            ['paragStyle', ['ol', 'ul', 'paragraph']]
        ]
    });
});