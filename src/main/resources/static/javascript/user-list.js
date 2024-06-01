// document.querySelector("a").addEventListener("click", getUserList, {once:true})
getUserList();

/* Находим форму по ее id */
const form = document.getElementById('myForm');

function addUser(e) {
    e.preventDefault();
    let fullName = document.getElementById('fullNameField').value
    let email = document.getElementById('emailField').value
    let password = document.getElementById('passwordField').value
    let rePassword = document.getElementById('rePasswordField').value
    let obj = {
        "email": email,
        "fullName": fullName,
        "password": password,
        "rePassword": rePassword
    }
    let json = JSON.stringify(obj);

    const request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/user");
    request.setRequestHeader("Content-Type", "application/json")
    request.send(json);
    request.onload = function () {
        if (request.status === 200) {
            /* let data = JSON.parse(request.response); */
            console.log(request.response);
            if (request.response === "ACCOUNT CREATED SUCCESSFULLY!") {
                showSuccessMessage(request.response);
            } else if (request.response === "EMAIL IS BUSY!") {
                showErrorMessage(request.response)
            } else {
                showErrorMessage(request.response)
            }
            closeModalAndRedirect()
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}

form.addEventListener('submit', (e) => addUser(e));


function closeModalAndRedirect() {
    // Закрываем модальное окно
    document.getElementById('addNewUserModal').style.display = 'none';
    // Перенаправляем пользователя на текущую страницу
    window.location.reload();
}


function showSuccessMessage(message) {
    alert(message); // Просто показываем всплывающее окно с сообщением
}

function showErrorMessage(message) {
    alert("Ошибка: " + message); // Просто показываем всплывающее окно с сообщением об ошибке
}

function getUserList() {
    const request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/user");
    request.send();
    request.onload = function () {
        if (request.status === 200) {
            let data = JSON.parse(request.response);
            console.log(data);
            data.forEach(user => {
                let row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.fullName}</td>
                    <td><a href="/user/details/${user.id}"  class="btn btn-primary">DETAILS</a></td>
                    `;
                document.querySelector('.app').appendChild(row);
            });
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}
