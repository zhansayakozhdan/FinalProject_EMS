const eventId = window.location.pathname.split('/').pop();
eventDetails(eventId);

let form = document.getElementById('updateEvent');
function eventDetails(id) {
    const req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/event/" + id);
    req.send();
    req.onload = function () {
        if (req.status === 200) {
            let event = JSON.parse(req.response);
            document.getElementById('eventId').value = event.id;
            document.getElementById('eventNameInput').value = event.name;
            document.getElementById('descriptionInput').value = event.description;
            document.getElementById('venueInput').value = event.venue;
            document.getElementById('dateInput').value = event.eventDate;
            document.getElementById('timeInput').value = event.eventTime;
            document.getElementById('quantityOfTicketsInput').value = event.quantityOfTickets;
            document.getElementById('ticketCostInput').value = event.ticketCost;
            document.getElementById('eventTypeId').value = event.eventType.id;
            console.log(event);
            getEventTypesList();
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}

function deleteEvent() {
    const request = new XMLHttpRequest();
    request.open('DELETE', 'http://localhost:8080/event/' + eventId);
    request.send();
    request.onload = function () {
        if (request.status === 200) {
            window.location.href = '/organizer/panel';
        }
    }
}

document.getElementById('deleteEventButton').addEventListener("click", function () {
    if (confirmDelete()) {
        deleteEvent();
    } else {
        alert("Удаление отменено!");
    }
});

function getEventTypesList() {
    let eventTypeId = document.getElementById('eventTypeId').value;
    const req = new XMLHttpRequest();
    req.open('GET', 'http://localhost:8080/eventType');
    req.send();
    req.onload = function () {
        if (req.status === 200) {
            let eventTypes = JSON.parse(req.response);
            eventTypes.forEach(eventType => {
                let option = document.createElement('option');
                option.value = eventType.id;
                option.textContent = eventType.name;
                if (eventTypeId === option.value) {
                    option.selected = true;
                }
                document.querySelector('.eventTypeList').appendChild(option);
            });
        } else {
            console.log('Явно что то пошло не так');
        }
    }
}

function editEvent() {
    let id = document.getElementById('eventId').value;
    let name = document.getElementById('eventNameInput').value;
    let description = document.getElementById('descriptionInput').value;
    let venue = document.getElementById('venueInput').value;
    let eventDate = document.getElementById('dateInput').value;
    let eventTime = document.getElementById('timeInput').value;
    let quantityOfTickets = document.getElementById('quantityOfTicketsInput').value;
    let ticketCost = document.getElementById('ticketCostInput').value;
    let eventTypeId = document.getElementById('eventTypeSelect').value;
    let eventTypeName = document.getElementById('eventTypeSelect').textContent;

    let updatedEvent = {
        id: id,
        name: name,
        description: description,
        venue: venue,
        eventDate: eventDate,
        eventTime: eventTime,
        quantityOfTickets: quantityOfTickets,
        ticketCost: ticketCost,
        eventType: {
            id: eventTypeId,
            name: eventTypeName
        }
    }
    const req = new XMLHttpRequest();
    req.open('PUT', 'http://localhost:8080/event');
    req.setRequestHeader('Content-type', 'application/json');
    req.send(JSON.stringify(updatedEvent));
    req.onload = function () {
        if(req.status === 200){
            window.location.reload();
        } else {
            console.log('Что то пошло не так')
        }
    }
}
form.addEventListener('submit', function (e){
    e.preventDefault();
    if(confirmEdit()){
        editEvent();
    } else {
        console.log('Изменение отменено!')
    }
})

function confirmDelete() {
    return confirm("Вы уверены в том что хотите это удалить?");
}

function confirmEdit(){
    return confirm('Вы точно хотите изменить данные?')
}