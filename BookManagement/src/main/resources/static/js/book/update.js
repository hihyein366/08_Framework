// const tbody = document.querySelector("#tbody");

// const selectBookList = () => {

//     fetch("/book/update")

//     .then(resp => resp.text())

//     .then(result => {
//         const bookList = JSON.parse(result);

//         tbody.innerHTML = "";

//         for(let book of bookList) {
//             const tr = document.createElement("tr");

//             const arr = ['bookNo', 'bookTitle', 'bookWriter', 'bookPrice', 'regDate'];
//             for(let key of arr){
//                 const td = document.createElement("td");
//                 td.innerText = book[key];
//                 tr.append(td);
//             }
//             tbody.append(tr)
//         }
//     });

// };


// selectBookList();
const searchBook = document.querySelector("#searchBook");
const searchBookList = document.querySelector("#searchBookList");

const selectBookList = () => {
    fetch("/book/searchBookList")

    .then(resp => resp.text())

    .then(result => {
        const bookList = JSON.parse(result);

        searchBookList.innerHTML = "";

        for (let book of bookList) {
            const tr = document.createElement("tr");

            const arr = ['bookNo', 'bookTitle', 'bookWriter', 'bookPrice', 'regDate'];

            for(let key of arr){
                const td = document.createElement("td");
                td.innerText = book[key];
                tr.append(td);
            }

            const editBtn = document.createElement("button");
            editBtn.textContent = "수정";
            editBtn.addEventListener("click", () => {

            });
            const editTd = document.createElement("td");
            editTd.appendChild(editBtn);
            tr.appendChild(editTd);

            const deleteBtn = document.createElement("button");
            deleteBtn.textContent = "삭제";
            deleteBtn.addEventListener("click", () => {

            });
            const deleteTd = document.createElement("td");
            deleteTd.appendChild(deleteBtn);
            tr.appendChild(deleteTd);

            searchBookList.append(tr);
        }
    });
};

searchBook.addEventListener("click", () => {
    selectBookList();
});