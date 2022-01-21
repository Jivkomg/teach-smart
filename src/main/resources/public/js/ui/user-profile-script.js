
document.getElementById("a-events-top30").addEventListener("click", function (e) {
    console.log("clickeddeded 30");
    localStorage.setItem("active-events", "top30");
    console.log("active tab = ", localStorage.getItem("active-events"));
});
document.getElementById("a-events-all").addEventListener("click", function (e) {
    localStorage.setItem("active-events", "all");
    console.log("active tab = ", localStorage.getItem("active-events"));
});

document.addEventListener("DOMContentLoaded", function(event){
    document.getElementById("username").innerText = localStorage.getItem("username");
})

document.addEventListener("DOMContentLoaded", function(event)
{
    document.getElementById("card-username").innerText = localStorage.getItem("username");
    getCurrentUserProfilePic();

    const container = document.getElementById("tickets-container");
    const noTicketsContainer = document.getElementById("no-tickets-container");
	const events = JSON.parse(localStorage.getItem("userTickets")); //array of all tickets bought by user
    const cols = 4;
    const rows = Math.ceil(events.length / cols);
    container.style.setProperty('--grid-rows', rows);
	container.style.setProperty('--grid-cols', cols);
	if(events.length==0)
	{
        container.style.display = "none";
        document.getElementsByClassName("category")[0].classList.add("no-tickets");
        let message = document.createElement('p');
        // let h2 = document.createElement('h2');
        // h2.appendChild(document.createTextNode("No tickets."));
        let text = document.createTextNode("To buy a ticket, navigate to \"Events\" section");
        let img = document.createElement("img");
        img.src="https://i.ibb.co/2SKjX0Q/ticket.png";

        message.appendChild(text);
        
        noTicketsContainer.display="block";
       // noTicketsContainer.appendChild(h2);
        noTicketsContainer.appendChild(message);
        noTicketsContainer.appendChild(img);
		return;
    }
    document.getElementsByClassName("category")[0].classList.remove("no-tickets");
    noTicketsContainer.style.display = "none";
//     container.style.display = "block";
	removeExistingEvents();

    for (i = 0; i < events.length; i++) {
		let eventA = document.createElement('a');
		eventA.classList.add('a-card');

        let eventDiv = document.createElement('div');
        //console.log("ID = ", events[i].eventId);
        let currentId = events[i].eventId;
        eventDiv.id = currentId;
       // console.log("ID2 = ", eventDiv.id);
        eventDiv.className = 'event-card';
         
        let selector = "#"+currentId;
        getEventPosterById(currentId, selector);

        let eventName = document.createElement('p');
        let text = document.createTextNode(events[i].name.toUpperCase());
        eventName.appendChild(text);
        eventDiv.appendChild(eventName);

        let eventTime = document.createElement('p');
        let timeText = document.createTextNode(events[i].startTime);
        eventTime.appendChild(timeText);
        eventDiv.appendChild(eventTime);

        let eventPrice = document.createElement('p');
        let price = document.createTextNode("$"+events[i].ticketPrice);
        eventPrice.appendChild(price);
        eventDiv.appendChild(eventPrice);

        container.appendChild(eventDiv);

        eventDiv.addEventListener("click", function (e) {
            console.log(e.target.id);
          //  getEventById(e.target.id);
        });
    }
})

function getEventPosterById(id, selector) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/events/poster/" + id,
        success: function (response) {
            console.log(response);
            var image = new Image();
            image.src = 'data:image/jpg;base64,' + response.encodedImage;
            document.querySelector(selector).appendChild(image);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function removeExistingEvents() {
    const container = document.getElementById("tickets-container");
    container.textContent = '';

}


function getCurrentUserProfilePic() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/user/current/profile-pic/" + sessionStorage.getItem('username'),
        success: function (response) {
            console.log(response);
            var image = new Image();
            image.src = 'data:image/jpg;base64,' + response.encodedImage;
            document.getElementById("user-card").appendChild(image);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}
