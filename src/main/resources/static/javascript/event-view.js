const eventId = window.location.pathname.split('/').pop();
getEvent(eventId);
let ticketCost = 0;
function getEvent(id){
    const req = new XMLHttpRequest();
    req.open('GET', 'http://localhost:8080/event/'+id);
    req.send();
    req.onload = function (){
        if(req.status === 200){
            let event = JSON.parse(req.response);
            console.log(event)
            document.getElementById('eventName').textContent = event.name;
            document.getElementById('description').textContent = event.description;
            document.getElementById('eventDate').textContent = event.eventDate;
            document.getElementById('eventTime').textContent = event.eventTime;
            document.getElementById('venue').textContent = event.venue;
            document.getElementById('ticketCost').textContent = event.ticketCost;
            document.getElementById('eventType').textContent = event.eventType.name;
            ticketCost = event.ticketCost;

            getComments(id);
        } else {
            console.log('Что то пошло не так!')
        }
    }
}

const totalTicketsElement = document.getElementById('totalTickets');
const ticketsContainer = document.getElementById('ticketsContainer');
const totalPriceElement = document.getElementById('totalPrice');
// const ticketCost = parseInt(document.getElementById('ticketCost').textContent);


let totalTickets = 0;
let totalPrice = 0;

document.getElementById('addTicketBtn').addEventListener('click' ,() => {
    totalTickets++;
    totalTicketsElement.textContent = totalTickets;
    totalPrice = totalTickets * ticketCost;
    totalPriceElement.textContent = totalPrice.toFixed(2);

        const newTicket = document.createElement('div');
        newTicket.classList.add('ticket');
        newTicket.innerHTML = `
        <div class="card mb-1">
            <div class="card-header d-flex justify-content-between">
                <p>БИЛЕТ №${totalTickets}</p>
                <p>${ticketCost} тг</p>
            </div>
        </div>
        `;
        ticketsContainer.appendChild(newTicket);
    });

function makeBooking() {
    let userEmail = document.getElementById('userEmailInput').value;
    let booking = {
        totalPrice: totalPrice,
        numberOfReservedSeats: totalTickets,
        userEmail: userEmail,
        eventId: eventId
    }
    let json = JSON.stringify(booking);
    const req = new XMLHttpRequest();
    req.open('POST', 'http://localhost:8080/booking');
    req.setRequestHeader('Content-type', 'application/json');
    req.send(json);
    console.log(json);
    req.onload = function (){
        if(req.status === 200){
            window.location.reload();
        } else {
            console.log('Что-то пошло не так');
        }
    }
}

let form = document.getElementById('makeBookingForm');
form.addEventListener('submit', function (e){
    e.preventDefault();
    makeBooking();
    alert("Бронирование успешно создан!")
});

const getComments = (eventId) => {
    const req = new XMLHttpRequest();
    req.open('GET', '/comments?eventId='+eventId);
    req.send();

    req.onload = function () {
        if(req.status === 200){
            let comments = JSON.parse(req.response);
            comments.forEach(comment => {
                let divElement = document.createElement('div');
                divElement.classList.add('card', 'mb-2');
                divElement.innerHTML = `
        <h6 class="card-header">${comment.userEmail}</h6>
        <div class="card-body">
            <p class="card-text" name="value">${comment.value}</p>
                <button type="button" class="btn btn-danger btn-sm" onclick="deleteComment(${comment.id})">
                    DELETE COMMENT
                </button> 
        </div>
                `;
                document.querySelector('.comments').appendChild(divElement);
            });
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}

const addCommentForm = document.getElementById('addCommentForm');
function addComment(e) {
    e.preventDefault();
    let value = document.getElementById('commentText').value;
    let userEmail = document.getElementById('userEmail').value;
    let comment = {
        "value": value,
        "eventId": eventId,
        "userEmail": userEmail
    }
    let json = JSON.stringify(comment);
    console.log(json);
    const req = new XMLHttpRequest();
    req.open('POST', 'http://localhost:8080/comments');
    req.setRequestHeader("Content-Type", "application/json");
    req.send(json);
    req.onload = function () {
        if(req.status === 200) {
        window.location.reload();
        console.log(req.response);
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}
addCommentForm.addEventListener('submit', (e)=> addComment(e));

function deleteComment(commentId){
    const req = new XMLHttpRequest();
    req.open('DELETE', 'http://localhost:8080/comments/'+commentId);
    req.send();
    req.onload = function () {
        if (req.status === 200) {
            console.log("Комментарий успешно удален!");
            window.location.reload();
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}


