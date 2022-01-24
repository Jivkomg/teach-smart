let allEvents;
let top30events;
let eventTypes = new Set();
//menu
document.getElementById("a-events-top30").addEventListener("click", function (e) {
    console.log("clickeddeded 30");
    document.getElementById("event-screen").style.display = "none";
    document.getElementById("all-events").style.display = "block";
    localStorage.setItem("active-events", "top30");
    console.log("active tab = ", localStorage.getItem("active-events"));
    getTop30Events();
});

function loadFilter() {
    $('#myInput').trigger('keyup');
}


function hideCourse() {
    $("#course-screen").addClass('d-none');
    document.getElementById("courses-section").style.display = 'block';
}

document.getElementById("all-courses").addEventListener("click", function (e) {
    $("#course-screen").addClass('d-none');
    document.getElementById("courses-section").style.display = "block";
    localStorage.setItem("active-courses", "all");
    console.log("active tab = ", localStorage.getItem("active-courses"));
    getAllcourses();
});

document.addEventListener("DOMContentLoaded", function (course) {
    document.getElementById("username").innerText = localStorage.getItem("username");
});
$(document).ready(function () {
    let x = localStorage.getItem("active-courses");
    switch (x) {
        case "all":
            getAllcourses();
            break;
        case "top30":
            getTop30courses("top30");
            break;
        default:
            localStorage.setItem("active-courses", "all");
    }
});

if (localStorage.getItem("active-events") == "top30")
    getTop30Events();

if (localStorage.getItem("active-events") == "all")
    getAllEvents();
console.log(localStorage.getItem("active-events"));
}
);
function getAllEvents() {
    let deferred = $.Deferred();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/all",
        success: function (courses) {
            allCourses = courses;
            displayCourses(courses, "All courses");
            deferred.resolve();
        },
        error: function (e) {
            allCourses = [];
            console.log("ERROR: ", e);
            deferred.resolve();
        }
    });
    return deferred.promise();
}

function getTop30Events() {
    let deferred = $.Deferred();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/top30",
        success: function (courses) {
            top30courses = courses;
            displayCourses(courses, "Top 30 courses");
            deferred.resolve();
        },
        error: function (e) {
            top30courses = [];
            console.log("ERROR: ", e);
            deferred.resolve();
        }
    });
    return deferred.promise();
}

function getUpcomingEvents() {
    let activeObj = getActiveEventsFunction();
    let eventsFunction = activeObj["functionName"];
    let title = activeObj["title"];
    let events = activeObj["events"];
    console.log("INSIDE UPCOMING", eventsFunction);

    $.when(
        window[coursesFunction]()
    ).done(function () {
        let sortedEvenets = events.sort((a, b) => new Date(a.startTime).getTime() - new Date(b.startTime).getTime());
        displayEvents(sortedEvenets, title);
        console.log(sortedEvenets);
    });
}
// NEED TO REMOVE
function getCoursesByPrice(number) {
    // number = 1 - cheapest; 2 - most expensive
    let activeObj = getActiveEventsFunction();    // let title = "";
    let eventsFunction = activeObj["functionName"];
    let title = activeObj["title"];
    let events = activeObj["events"];
    console.log("INSIDE cheapest: ", eventsFunction);
    $.when(
        window[coursesFunction]()
    ).done(function () {
        let sortedEvenets = events.sort((a, b) => a.ticketPrice - b.ticketPrice); // cheapest default
        if (number == 2)
            sortedCourses = courses.sort((a, b) => b.ticketPrice - a.ticketPrice); //most expenisve

        displayCourses(sortedCourses, title);
        console.log(sortedCourses);
    });
}
function getCoursesTypes() {
    $.when(
        getAllCourses()
    ).done(function () {
        allCourses.forEach(course => {
            courseTypes.add(course.type);
        });
        alert(courseTypes);
    });
}

function onlyUnique(value, index, self) {
    return self.indexOf(value) === index;
}

// this is used for filtering by type - just pass the type you filter by as an argument
function getEventByType(type) {
    let activeObj = getActiveEventsFunction();    // let title = "";
    let eventsFunction = activeObj["functionName"];
    let title = activeObj["title"];
    let events = activeObj["events"];
    console.log("INSIDE category: ", eventsFunction);
    $.when(
        window[coursesFunction]() //getAllcourses() or getTop30courses()
    ).done(function () {
        let filteredEvents = events.filter(event => event.type === type);
        displayEvents(filteredEvents, title);
        console.log(filteredEvents);
    });
}


function getCourseById(id) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/" + id,
        success: function (course) {
            console.log(course);
            localStorage.setItem('chosenCourseObj', JSON.stringify(course));
            showCourseScreen(course);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}
function showEventScreen(event) {
    const currentId = event.eventId;
    console.log("eventid =", currentId);
    document.getElementById("all-events").style.display = "none";
    document.getElementById("event-screen").style.display = "block";
    document.getElementById("event-name").innerText = event.name;
    let selector = ".event-screen";
    getEventPosterById(currentId, selector);
    //popup elements
    document.getElementById("popup-event-name").innerHTML = "<b>" + event.name + "</b>";
    document.getElementById("popup-event-price").innerHTML = "<b>$" + event.ticketPrice + "</b>";
    localStorage.setItem("event-price", event.ticketPrice);

    localStorage.setItem("event-name", event.name);
    console.log("price = ", localStorage.getItem('event-price'));
    console.log("name = ", localStorage.getItem('event-name'));

    let type = event.type;

    //document.getElementById("type").innerHTML = type + event.type;
    let startTime = event.startTime;
    let duration = event.durationHours;
    let price = event.ticketPrice;
    getEventPerformers(currentId);
    getEventOrganizers(currentId);

    let ids = ["type", "start-time", "duration", "price"];
    let initialIdValues = [];
    let values = [type, startTime, duration, price];
    initialIdValues = ["Event type: ", "Start time: ", "Duration: ", "Price: $"];
    for (let i = 0; i < ids.length; i++) {
        document.getElementById(ids[i]).innerHTML = "<b>" + initialIdValues[i] + "</b> " + values[i];
        if (ids[i] == "duration")
            document.getElementById(ids[i]).innerHTML += " hours";
    }
    let successfulMessage = document.getElementById("popup-message").innerHTML;
    console.log(successfulMessage);
    localStorage.setItem("popup-message", successfulMessage);
    console.log("LOCAL storage =", localStorage.getItem('popup-message'));
}

function getCoursePosterById(id, selector) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/poster/" + id,
        success: function (response) {
            console.log(response);
            let image = new Image();
            image.src = 'data:image/jpg;base64,' + response.encodedImage;
            document.querySelector(selector).appendChild(image);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function getCourseOrganizers(id) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/organizers/" + id,
        success: function (organizers) {
            let organizersStr = "<b>Organizers:</b> ";
            if (organizers.length) {
                for (let i = 0; i < organizers.length; i++) {
                    organizersStr += organizers[i].name;
                    if (i != organizers.length - 1)
                        organizersStr += ", ";
                }
            }
            document.getElementById("organizer").innerHTML = organizersStr;

        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function getEventPerformers(id) {
    let performers = $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/tutors/" + id,
        success: function (performers) {
            console.log(performers);
            let performersStr = "<b>Performers:</b> ";
            if (performers.length) {
                for (let i = 0; i < performers.length; i++) {
                    performersStr += performers[i].name;
                    if (i != performers.length - 1)
                        performersStr += ", ";
                }
            }
            document.getElementById("performer").innerHTML = performersStr;
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
    return performers;
}


function displayEvents(events, h1_name) {
    removeExistingEvents();
    document.getElementById("h1_name").innerHTML = h1_name;
    document.getElementById("events-count").innerHTML = events.length + " events shown";
    const container = document.getElementById("events-container");
    const cols = 4;
    const rows = Math.ceil(events.length / cols).toString();
    container.style.setProperty('--grid-rows', rows);
    container.style.setProperty('--grid-cols', cols.toString());

    for (let i = 0; i < events.length; i++) {
        let eventA = document.createElement('a');

        let eventDiv = document.createElement('div');
        let currentId = "event_" + events[i].eventId;
        eventDiv.setAttribute("id", currentId);
        eventDiv.className = 'event-card';
        let selector = "#" + currentId;

        let eventName = document.createElement('p');
        let text = document.createTextNode(events[i].name.toUpperCase());
        eventName.appendChild(text);
        eventDiv.appendChild(eventName);

        let eventTime = document.createElement('p');
        let timeText = document.createTextNode(events[i].startTime);
        eventTime.appendChild(timeText);
        eventDiv.appendChild(eventTime);

        let eventPrice = document.createElement('p');
        let price = document.createTextNode("$" + events[i].ticketPrice);
        eventPrice.appendChild(price);
        eventDiv.appendChild(eventPrice);
        container.appendChild(eventDiv);
        getEventPosterById(currentId, selector);

        eventDiv.addEventListener("click", function (e) {
            console.log(e.target.id);
            let eventId = e.target.id;
            eventId = eventId.split("_")[1];
            getEventById(eventId); // returns eventId object - to load in new html page

        }

function filterByType(type) {
                let searchBox = document.getElementById("myInput");
                searchBox.value = type;
                searchBox.click();
            }

function displayTypes(courseTypes) {
                const typesContainer = document.getElementById("typesContainer");
                courseTypes.forEach(function (type) {
                    let typeLink = document.createElement('button');
                    typeLink.className = 'btn btn-success';
                    typeLink.setAttribute("onclick", "filterByType('" + type + "'); loadFilter(); ");
                    console.log("filterByType(" + type + ")");
                    typeLink.innerText = type;
                    typesContainer.appendChild(typeLink);
                });
            }
function getActiveEventsFunction() {
                let activeObj = {};
                let activeEvents = localStorage.getItem("active-events");
                if (activeEvents == "top30") {
                    activeObj["functionName"] = "getTop30Events";
                    activeObj["title"] = "Top 30 events";
                    activeObj["events"] = top30events;
                } else {
                    activeObj["functionName"] = "getAllEvents";
                    activeObj["title"] = "All events";
                    activeObj["events"] = allEvents;
                }

                console.log("active FUNC", activeObj["functionName"]);
                return activeObj;
            }

function setCurrentOption(dropdown_id, option_id) {
                let functionName = getActiveEventsFunction()["functionName"];
                let title = getActiveEventsFunction().title;
                let currentOptionElement;
                let optionsArr = [];
                switch (dropdown_id) {
                    case 1: {
                        currentOptionElement = document.querySelectorAll(".dropdown-bar:nth-child(2) ul li:first-child a")[0];
                        optionsArr = ["time_1"];
                        break;
                    }
                    case 2: {
                        currentOptionElement = document.querySelectorAll(".dropdown-bar:nth-child(3) ul li:first-child a")[0];
                        //optionsArr = ["Highest to lowest","Lowest to highest"];
                        optionsArr = ["price_1", "price_2"];
                        break;
                    }
                    case 3: {
                        currentOptionElement = document.querySelectorAll(".dropdown-bar:nth-child(4) ul li:first-child a")[0];
                        //optionsArr = ["Least recent","Most recent"];
                        optionsArr = ["category_1", "category_2", "category_3", "category_4"];
                        break;
                    }
                }

                let oldOption = currentOptionElement.textContent; //old user because will be replased with new current user
                let oldOptionElement = document.getElementById(option_id);
                let currentOption = oldOptionElement.textContent;
                currentOptionElement.innerHTML = currentOption + '<i class="fa fa-angle-down"></i>';
                oldOptionElement.innerHTML = oldOption;


                switch (dropdown_id + '|' + currentOption) {
                    case "1|Upcoming courses": { console.log("Time filtering"); getUpcomingcourses(); break; }
                    case "1|Time": { console.log("Time unfiltering"); window[functionName](); break; }//calling getTop30courses() or getAllcourses() according to option chosen by user}
                    case "2|Lowest to highest": { console.log("Price filtering - cheap"); getcoursesByPrice(1); break; }
                    case "2|Highest to lowest": { console.log("price filtering - expensive"); getcoursesByPrice(2); break; } //calling getTop30courses() or getAllcourses() according to option chosen by user}
                    case "2|Price": { console.log("price unfiltering"); window[functionName](); break; } //calling getTop30courses() or getAllcourses() according to option chosen by user}
                    case "3|Tech course": { console.log("Tech course filtering"); getCourseByType("Tech course"); break; }
                    case "3|Seminars": { console.log("Seminars filtering"); getCourseByType("Seminars"); break; }
                    case "3|Concert": { console.log("Concert filtering"); getCourseByType("Concert"); break; }
                    case "3|Music Festival": { console.log("Music festival filtering"); getCourseByType("Music Festival"); break; }
                    case "3|Category": { console.log("unfilter category"); window[functionName](); } //click Category - remove filter
                }

            }
function removeExistingCourses() {
                const container = document.getElementById("courses-container");
                container.textContent = '';

            };

