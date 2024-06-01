getEventList();

function getEventList() {
    const req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/event");
    req.send();
    req.onload = function () {
        if (req.status === 200) {
            let events = JSON.parse(req.response);
            getCards(events);

        } else {
            console.error("Что-то пошло не так!");
        }
    }
}

const form = document.getElementById('searchEventForm');
function searchEvent() {
    let query = document.getElementById('query').value;
    const req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/event/search?query="+query);
    req.send();
    req.onload = function () {
        if (req.status === 200){
            let events = JSON.parse(req.response);
            updateEventsList(events);
        } else {
            console.log('Что то пошло не так!')
        }
    }
}

function updateEventsList(events) {
    let eventsList = document.getElementById('eventsList');
    // Проверка на наличие результатов
    if (events.length === 0) {
        document.getElementById('noResultsMessage').style.display = 'block';
        eventsList.innerHTML = '';
    } else {
        // Очистка текущего списка событий
        eventsList.innerHTML = '';

        getCards(events);
    }
}

function getCards(events) {
    events.forEach(event => {
        console.log(event);
        let card = document.createElement('div');
        card.classList.add('col-sm-6');
        card.innerHTML = `
                    <div class="card border-primary mb-3">
                        <div class="card-header"><time datetime="${event.eventDate}">${event.eventDate}</time>, в ${event.eventTime}</div>
                        <div class="card-body text-primary">
                            <h5 class="card-title">${event.name}</h5>
                            <p class="card-text">${event.description}</p>
                        <p></p>
                        <a href="/event/view/${event.id}" class="btn btn-primary">Купить билет</a>
                        </div>
                    </div>
                    `;
        document.querySelector('.events').appendChild(card);
    });
}

form.addEventListener('submit', function (e) {
    e.preventDefault();
    searchEvent();
});