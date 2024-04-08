// 조회 버튼
const selectBookList = document.querySelector("#selectBookList");

// tbody
const bookList = document.querySelector("#bookList");

// td요소 만들기
const createTd = (text) => {
    const td = document.createElement("td");
    td.innerText = text;
    return td;
}

selectBookList.addEventListener("click", () => {
    fetch("/book/selectBookList")

    .then(resp => resp.json())
    .then(list => {
        console.log(list);
        bookList.innerHTML = "";

        list.forEach((book, index) => {
            const keyList = ['bookNo', 'bookTitle', 'bookWriter', 'bookPrice', 'regDate'];
            const tr = document.createElement("tr");
            keyList.forEach( key => tr.append(createTd(book[key])));
            bookList.append(tr)
        })
    });






});