// 조회 버튼
const selectAll = document.querySelector("#selectAll");
// tbody
const tbody = document.querySelector("#tbody");



const bookList = document.querySelector("#bookList");

const searchBook = document.querySelector("#searchBook");
const searchBookList = document.querySelector("#searchBookList");



// td요소 만들기
const createTd = (text) => {
    const td = document.createElement("td");
    td.innerText = text;
    return td;
}

selectAll.addEventListener("click", () => {
    fetch("/book/selectBookList")

    .then(resp => resp.text())

    .then(result => {
        const bookList = JSON.parse(result);

        tbody.innerHTML = "";

        for (let book of bookList) {
            const tr = document.createElement("tr");

            const arr = ['bookNo', 'bookTitle', 'bookWriter', 'bookPrice', 'regDate'];

            for(let key of arr){
                const td = document.createElement("td");
                td.innerText = book[key];
                tr.append(td);
            }

            tbody.append(tr);
        }
    });
});


// selectBookList.addEventListener("click", () => {
//     fetch("/book/selectBookList")

//     .then(resp => resp.json())
//     .then(list => {
//         console.log(list);
//         bookList.innerHTML = "";

//         list.forEach((book, index) => {
//             const keyList = ['bookNo', 'bookTitle', 'bookWriter', 'bookPrice', 'regDate'];
//             const tr = document.createElement("tr");
//             keyList.forEach( key => tr.append(createTd(book[key])));
//             bookList.append(tr)
//         })
//     });
// });


