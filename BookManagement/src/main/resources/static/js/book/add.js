const bookAddForm = document.querySelector("#bookAddForm");
const bookTitle = document.querySelector("#bookTitle");
const bookWriter = document.querySelector("#bookWriter");
const bookPrice = document.querySelector("#bookPrice");
const addBtn = document.querySelector("#addBtn");

// if(bookAddForm != null) {
//     bookAddForm.addEventListener("submit", e => {

//         if(bookTitle.value.trim().length == 0) {
//             alert("책 제목을 작성하세요");
//             e.preventDefault();
//             return;
//         }

//         if(bookWriter.value.trim().length == 0) {
//             alert("글쓴이를 입력하세요");
//             e.preventDefault();
//             return;
//         }
        
//         if(bookPrice.value.trim().length == 0) {
//             alert("가격을 입력하세요");
//             e.preventDefault();
//             return;
//         }
//     });
// }




addBtn.addEventListener("click", () => {
    const param = {
        "bookTitle" : bookTitle.value,
        "bookWriter" : bookWriter.value,
        "bookPrice" : bookPrice.value
    }

    fetch("/book/add", {
        method : "POST",
        headers : {"Contenet-Type" : "application/json"},
        body : JSON.stringify(param)
    })

    .then(resp => resp.text())

    .then(temp => {
        if(temp > 0) {
            alert("등록 성공");
        } else {
            alert("등록 실패");
        }

    });
})