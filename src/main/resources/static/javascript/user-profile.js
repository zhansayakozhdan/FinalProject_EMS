
getUserView();
function getUserView() {
    const req = new XMLHttpRequest();
    req.open('GET', 'http://localhost:8080/user/profile/');
    req.send();
    req.onload = function () {
        if (req.status === 200) {
            let user = JSON.parse(req.response);
            console.log(user);
            const elements = document.querySelectorAll('.fullName');
            elements.forEach(element => {
                element.textContent = user.fullName;
            })
            document.getElementById('userId').value=user.id;
            document.getElementById('email').textContent = user.email;
            document.getElementById('phone').textContent = user.phone;
            let roles = user.roles;
            roles.forEach(role => {
                let divElement = document.createElement('div');
                divElement.innerHTML = `
                    <div class="border border-1 p-1 me-2 text-muted">${role.name}</div>
                    `;
                document.querySelector('.roles').appendChild(divElement);
            });
            getRoleList();
        } else {
            console.log('Что то пошло не так');
        }
    }
}

function getRoleList() {
    const req = new XMLHttpRequest();
    let userId = document.getElementById('userId').value;
    req.open('GET', 'http://localhost:8080/booking?userId=' + userId);
    req.send();
    req.onload = function () {
        if (req.status === 200) {
            let bookings = JSON.parse(req.response);
            document.getElementById('bookingListSize').textContent=bookings.length;
            bookings.forEach(booking => {
                let bookingCard = document.createElement('div');
                bookingCard.classList.add('card', 'mb-2', 'bookingCard');
                bookingCard.innerHTML = `
                    <div class="card-header">${booking.event.eventDate + ', в ' + booking.event.eventTime}"</div>
                        <div class="card-body">
                        <h5 class="card-title">${'Название события: ' + booking.event.name}"</h5>
                        <h6 class="card-text">${'Бронирование на имя: ' + booking.user.fullName}</h6>
                        <div class="d-flex">
                            <p class="me-5">${'Кол-во забранированных мест: ' + booking.numberOfReservedSeats}</p>
                            <p>${'СТАТУС: ' + booking.status}</p>
                        </div>
                        <!-- Button cancel booking modal -->
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="${'#cancelBookingModal' + booking.id}">
                            Отменить Бронь
                        </button>
                        <!-- Modal for cancel booking -->
                   
                            <div class="modal fade bookingModal" id="${'cancelBookingModal' + booking.id}" tabindex="-1" aria-labelledby="deleteTaskModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="cancelBookingModalLabel">ОТМЕНА БРОНИ</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Вы точно хотите отменить бронирование на <b>${booking.event.name}</b>?</p>
                                            <input type="hidden" value="${booking.id}" name="id" id="bookingId">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-danger bookingDeleteButton" id="${booking.id}">Отменить Бронирование</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        
                    </div>`;
                document.querySelector('.userBookingList').appendChild(bookingCard);
            });
            let bookingDeleteButtons = document.querySelectorAll('.bookingDeleteButton');
            bookingDeleteButtons.forEach(button => {
                button.addEventListener('click', function(){
                    let buttonId = button.id;
                    deleteBooking(parseInt(buttonId));
                    alert('Бронирование отменено!')
                })
            })
        } else {
            console.log('Что то пошло не так');
        }
    }
}

function deleteBooking(bookingId) {
    const req = new XMLHttpRequest();
    req.open('DELETE', 'http://localhost:8080/booking/delete/'+bookingId);
    req.send();
    req.onload = function (){
        if(req.status === 200){
            window.location.href = '/profile';
        } else {
            console.log('Явно что то пошло не так')
        }
    }
}



document.addEventListener('DOMContentLoaded', function() {
    const changePasswordForm = document.getElementById('changePasswordForm');

    function changePassword(e) {
        e.preventDefault();
        let currentPassword = document.getElementById('currentPassword').value;
        let newPassword = document.getElementById('newPassword').value;
        let reNewPassword = document.getElementById('reNewPassword').value;
        let userId = document.getElementById('userId').value;

        let obj = {
            "id": parseInt(userId),
            "currentPassword": currentPassword,
            "newPassword": newPassword,
            "reNewPassword": reNewPassword
        };
        let json = JSON.stringify(obj);
        const req = new XMLHttpRequest();
        req.open('PUT', 'http://localhost:8080/user/changePassword');
        req.setRequestHeader("Content-Type", "application/json");
        req.send(json);
        console.log(json);
        req.onload = function () {
            hideAlerts();
            if(req.status === 200){
                showSuccessAlert()
                window.location.reload();
                console.log(req.response);
            } else {
                showErrorAlert();
                console.log('Что-то пошло не так');
            }
        };
    }

    if (changePasswordForm) {
        changePasswordForm.addEventListener('submit', changePassword);
    } else {
        console.error('Change password form element not found.');
    }
});

function showSuccessAlert() {
    const alertSuccess = document.getElementById('alertSuccess');
    alertSuccess.style.display = 'block';
}

function showErrorAlert() {
    const alertError = document.getElementById('alertError');
    alertError.style.display = 'block';
}

function hideAlerts() {
    const alertSuccess = document.getElementById('alertSuccess');
    const alertError = document.getElementById('alertError');
    alertSuccess.style.display = 'none';
    alertError.style.display = 'none';
}

