const userId = window.location.pathname.split('/').pop();
userDetails(userId);

function userDetails(id) {
    const req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/user/" + id);
    req.send();
    req.onload = function () {
        if (req.status === 200) {
            let user = JSON.parse(req.response);
            document.getElementById('idField').value = user.id
            document.getElementById('fullNameField').value = user.fullName
            document.getElementById('emailField').value = user.email
            document.getElementById('phoneField').value = user.phone
            let roles = user.roles;
            roles.forEach(role => {
                let button = document.createElement('div');
                button.innerHTML = `
                    <button class="role-button btn btn-primary me-2" id="role${role.id}">${role.name} X</button>
                    `;
                document.querySelector('.roles').appendChild(button);
            });
            console.log(user);
        } else {
            console.error("Что-то пошло не так!");
        }
        getRoleList();
        // Получаем все кнопки с классом role-button
        let buttons = document.querySelectorAll(".role-button");
        buttons.forEach(button => {
            button.addEventListener("click", function () {
                const buttonId = button.id;
                // Получаем roleId из атрибута data-roleid кнопки
                let roleId = buttonId.replace('role', '');
                if (confirmDelete()) {
                    deleteRoleFromUser(roleId);
                } else {
                    console.log("Удаление отменено!")
                }
            });
        });
    }
}

function deleteUser() {
    let id = document.getElementById('idField').value;
    const request = new XMLHttpRequest();
    request.open("DELETE", "http://localhost:8080/user/" + id);
    request.send();
    request.onload = function () {
        if (request.status === 200) {
            window.location.href = '/admin/panel';
        }
    }
}

document.getElementById("deleteButton").addEventListener("click", function () {
    if (confirmDelete()) {
        deleteUser();
    } else {
        alert("Удаление отменено!");
    }
});

function editUser() {
    const rolesContainer = document.getElementById('roles');
    const roleButtons = rolesContainer.querySelectorAll('.btn');
    const roles = [];
    roleButtons.forEach(button => {
        // Извлекаем текст кнопки (название роли)
        const roleName = button.textContent.trim().replace(' X', ''); // Удаляем " X" в конце текста кнопки
        const buttonId = button.id; // Получаем id кнопки
        const roleId = buttonId.replace('role', ''); // Извлекаем role id
        console.log(`Role ID: ${roleId}`);
        // Создаем объект роли и добавляем его в массив
        const role = {
            id: roleId,
            name: roleName
        };
        roles.push(role);
    });
    console.log(roles);

    let fullName = document.getElementById('fullNameField').value
    let userId = document.getElementById('idField').value
    let email = document.getElementById('emailField').value
    let phone = document.getElementById('phoneField').value
    let obj = {
        "id": userId,
        "email": email,
        "fullName": fullName,
        "roles": roles,
        "phone": phone
    }
    let json = JSON.stringify(obj);
    console.log(json);
    const req = new XMLHttpRequest();
    req.open("PUT", "http://localhost:8080/user");
    req.setRequestHeader("Content-Type", "application/json");
    req.send(json);
    console.log(req.responseText);
    req.onload = function () {
        if (req.status === 200) {
            window.location.reload();

        }
    }
}

document.getElementById("editUserButton").addEventListener("click", editUser);


function addRoleForUser() {
    let id = document.getElementById('idField').value;
    let select = document.getElementById('roleList');
    let roleId = select.value;
    let roleName = select.options[select.selectedIndex].text;
    let role = {
        "id": roleId,
        "name": roleName
    }
    let json = JSON.stringify(role);
    console.log(json);
    const req = new XMLHttpRequest();
    req.open("PATCH", "http://localhost:8080/user/" + id);
    req.setRequestHeader("Content-Type", "application/json");
    req.send(json);
    req.onload = function () {
        if (req.status === 200) {
            console.log("Роль успешно добавлен!");
            window.location.reload();
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}

document.getElementById("addRoleButton").addEventListener("click", addRoleForUser);

function getRoleList() {
    let id = document.getElementById('idField').value;
    const request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/role/" + id);
    request.send();
    request.onload = function () {
        if (request.status === 200) {
            let roles = JSON.parse(request.response);
            roles.forEach(role => {
                let option = document.createElement('option');
                option.textContent = role.name;
                option.value = role.id;
                document.getElementById('roleList').appendChild(option);
            });
            if (roles.length === 0) {
                document.getElementById("addRoleForUser").remove();
            }
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}

function deleteRoleFromUser(roleId) {
    const request = new XMLHttpRequest();
    let id = document.getElementById('idField').value;
    request.open("PATCH", "http://localhost:8080/user/" + id + "?roleId=" + roleId);
    request.send();
    request.onload = function () {
        if (request.status === 200) {
            console.log("Роль успешно удален!");
            window.location.reload();
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}

function confirmDelete() {
    return confirm("Вы уверены в том что хотите это удалить?");
}