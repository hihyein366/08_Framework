const selectAll = document.querySelector(".selectAll")
const tbody = document.querySelector("tbody");

selectAll.addEventListener("click", () => {

    fetch("/stu/selectAll")
    .then(resp => resp.text())
    .then(result => {
        const stuList = JSON.parse(result);

        tbody.innerHTML = "";

        for(let stu of stuList) {
            const tr = document.createElement("tr");

            const arr = ['studentNo', 'studentName', 'studentMajor', 'studentGender'];

            for(let key of arr){
                const td = document.createElement("td");

                td.innerText = stu[key];
                tr.append(td);
            }
            tbody.append(tr);
        }
    })
});