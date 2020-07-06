// const section = document.querySelector('section');
// section.setAttribute("class", "grid-layout");
// // showTags();
// let requestURL = 'http://localhost:5557/api/user/tag/?pageSize=3&pageNumber=1';
// let request = new XMLHttpRequest();
// request.open('GET', requestURL);
// request.responseType = 'json';
// request.send();
// console.log(request);
//
// request.onload = function () {
//     let tags = request.response;
//     showTags(tags);
//     console.log(tags);
// }
//
// function showTags(jsonObj) {
//     console.log(jsonObj);
//     let tag = jsonObj['value'];
//
//     console.log(jsonObj);
//     let valueArray = jsonObj['value'];
//
//     for (let i = 0; i < valueArray.length; i++) {
//         let sNum = document.createElement('div');
//         sNum.setAttribute("class", "num");
//         let sCard = document.createElement('div');
//         sCard.setAttribute("class", "s-card js-tag-cell grid fd-column");
//         let myGrid = document.createElement('div');
//         myGrid.setAttribute("class", "grid jc-space-between ai-center mb12");
//         let gridCell = document.createElement('div');
//         gridCell.setAttribute("class", "grid--cell");
//         let myA = document.createElement('a');
//         myA.setAttribute("href", "#");
//         let myPara1 = document.createElement('p');
//         let myPara2 = document.createElement('p');
//         let myList = document.createElement('ul');
//
//         myA.textContent = valueArray[i].name;
//         myPara1.textContent = valueArray[i].description;
//         myPara2.textContent = 'Количество вопросов: ' + valueArray[i].questionCount;
//
//         sCard.appendChild(gridCell);
//         sCard.appendChild(myA);
//         sCard.appendChild(myPara1);
//         sCard.appendChild(myPara2);
//         sCard.appendChild(myList);
//         sNum.appendChild(sCard);
//         // section.appendChild(sNum);
//         section.appendChild(sNum);
//
//     }
// }
//
//
//
