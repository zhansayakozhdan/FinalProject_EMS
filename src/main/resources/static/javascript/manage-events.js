getAllEvents();
const form = document.getElementById('formForCreateEvent');

function getAllEvents() {
    const request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/event");
    request.send()
    request.onload = function () {
        if (request.status === 200) {
            let eventList = JSON.parse(request.response);
            console.log(eventList);
            eventList.forEach(event => {
                let row = document.createElement('tr');
                row.innerHTML = `
                    <td>${event.id}</td>
                    <td>${event.name}</td>
                    <td>${event.quantityOfTickets}</td>
                    <td><a href="/event/details/${event.id}"  class="btn btn-primary">DETAILS</a></td>
                    `;
                document.querySelector('.eventList').appendChild(row);
            });
        } else {
            console.error("Что-то пошло не так!");
        }
    }
}

function getEventTypes() {
    const req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/eventType");
    req.send();
    req.onload = function () {
        if (req.status === 200) {
            let eventTypes = JSON.parse(req.response);
            eventTypes.forEach(eventType => {
                let option = document.createElement('option');
                option.textContent = eventType.name;
                option.value = eventType.id;
                document.querySelector('.eventTypeList').appendChild(option);
            });
        } else {
            console.log("Что то пошло не так");
        }
    }
}

document.getElementById('createEventButton').addEventListener('click', getEventTypes);

function createEvent() {
    let name = document.getElementById('inputEventName').value;
    let description = document.getElementById('inputDescription').value;
    let date = document.getElementById('dateInput').value;
    let time = document.getElementById('timeInput').value;
    let venue = document.getElementById('venueInput').value;
    let select = document.getElementById('eventTypeSelect');
    let eventId = select.value;
    let eventName = select.options[select.selectedIndex].text;
    let eventType = {
        "id": eventId,
        "name": eventName
    }
    let quantityOfTickets = document.getElementById('quantityOfTickets').value;
    let ticketCost = document.getElementById('ticketCostInput').value;

    let newEvent = {
        name: name,
        description: description,
        eventDate: date,
        eventTime: time,
        venue: venue,
        eventType: eventType,
        quantityOfTickets: quantityOfTickets,
        ticketCost: ticketCost
    };
    let json = JSON.stringify(newEvent);
    const req = new XMLHttpRequest();
    req.open('POST', 'http://localhost:8080/event');
    req.setRequestHeader("Content-Type", "application/json");
    req.send(json);
    req.onload = function () {
        if (req.status === 200) {
            console.log(json);
            console.log('Event успешно создан');
            window.location.reload();
        } else {
            console.log('Явно что то пошло не так');
        }
    }
}

form.addEventListener('submit', (e) => {
    e.preventDefault();
    createEvent()
});




