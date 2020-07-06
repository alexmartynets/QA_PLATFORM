 function getAjax(items_number, page_number, list_element, pagination_element){
     $.ajax({
         url:`/pagination`,
         type: "GET",
         contentType: "application/json",
         dataType: "json",
         data: {
             "page": page_number,
             "size": items_number
         },
         success: function (data) {
             setupPagination(data, pagination_element);
             displayList(list_element, page_number, items_number);
         }
     })
}

function displayList (wrapper, page_number, items_number) {
    wrapper.innerHTML = "";
    $.ajax({
        url: `pagination`,
        type: 'GET',
        contentType: "application/json",
        dataType: "json",
        data: {
            "page": page_number,
            "size": items_number
        },
        success: function (pair) {
            let paginatedItems = Object.values(pair)[1];
            for (let i = 0; i < paginatedItems.length; i++) {
                let item = paginatedItems[i];
                addRow(item);
            }
        }
    })
}
