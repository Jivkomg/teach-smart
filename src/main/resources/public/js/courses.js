var allCourses;
var top30courses;
var courseTypes = new Set();

$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#courses-container div").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});

function loadFilter(){
    $('#myInput').trigger('keyup');
}


function hideCourse(){
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
})

//OK
$(document).ready(function () {
    let x = localStorage.getItem("active-courses");
    switch (x){
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

function getAllcourses() {
    var deferred = $.Deferred();
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

function getTop30courses() {
    var deferred = $.Deferred();
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

function getUpcomingCourses() {
    var activeObj = getActiveCoursesFunction();
    var coursesFunction = activeObj["functionName"];
    var title = activeObj["title"];
    var courses = activeObj["courses"];
    console.log("INSIDE UPCOMING", coursesFunction);

    $.when(
        window[coursesFunction]()
    ).done(function () {
        var sortedCourses = courses.sort((a, b) => new Date(a.startTime).getTime() - new Date(b.startTime).getTime());
        displayCourses(sortedCourses, title);
        console.log(sortedCourses);
    });
}
// NEED TO REMOVE
function getCoursesByPrice(number) {
    // number = 1 - cheapest; 2 - most expensive
    var activeObj = getActiveCoursesFunction();    // var title = "";
    var coursesFunction = activeObj["functionName"];
    var title = activeObj["title"];
    var courses = activeObj["courses"];
    console.log("INSIDE cheapest: ", coursesFunction);
    $.when(
        window[coursesFunction]()
    ).done(function () {
        var sortedCourses = courses.sort((a, b) => a.ticketPrice - b.ticketPrice); // cheapest default
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
function getCoursesByType(type) {
    var activeObj = getActiveCoursesFunction();    // var title = "";
    var coursesFunction = activeObj["functionName"];
    var title = activeObj["title"];
    var courses = activeObj["courses"];
    console.log("INSIDE category: ", coursesFunction);
    $.when(
        window[coursesFunction]() //getAllcourses() or getTop30courses()
    ).done(function () {
        var filteredCourses = courses.filter(course => course.type === type);
        displayCourses(filteredCourses, title);
        console.log(filteredCourses);
    });
}


function getCourseById(id) {
    console.log(id);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/" + id,
        success: function (course) {
            localStorage.setItem('chosenCourseObj', JSON.stringify(course));
            showCourseScreen(course);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}


function showCourseScreen(course) {
    currentId = course.courseId;
    //console.log("courseId =", currentId);
    $("#course-screen").removeClass('d-none');
    document.getElementById("courses-section").style.display = 'none';

    //fill info for course popup
    document.getElementById("popup-course-name").innerHTML = course.name;
    document.getElementById("popup-course-description").innerHTML = course.description;
    document.getElementById("popup-course-type").innerHTML = "Категория: "+"<b>"+course.type+ "</b>";
    document.getElementById("popup-course-duration").innerHTML = "Продължителност на курса: "+"<b>"+course.durationHours + "</b>" +" часа";
    document.getElementById("popup-course-attendants").innerHTML = "Удобрили курса: "+"<b>"+course.attendants + "</b>" + " потребители";
    document.getElementById("youtube").src = course.posterLocation;
}

function getCoursePosterById(id, selector) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/poster/" + id,
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

function getCourseOrganizers(id) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/organizers/" + id,
        success: function (organizers) {
            var organizersStr = "<b>Organizers:</b> ";
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

function getCoursePerformers(id) {
    var performers = $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/courses/tutors/" + id,
        success: function (performers) {
            console.log(performers);
            var performersStr = "<b>Performers:</b> ";
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


function displayCourses(courses, h1_name) {
    removeExistingCourses();
    //document.getElementById("h1_name").innerHTML = h1_name;
    document.getElementById("courses-count").innerHTML ="Брой курсове: " + courses.length;
    const container = document.getElementById("courses-container");
    let courseTypes = new Set();
    //Create all course cards and add it to courses-container
    for (i = 0; i < courses.length; i++) {

        courseTypes.add(courses[i].type);

        //course - div (container card)
        let courseDiv = document.createElement('div');
        courseDiv.setAttribute("onclick", "getCourseById('"+courses[i].courseId+"')");
        courseDiv.className='card border-0 bg-dark text-white';

        let courseImg = document.createElement('img');
        courseImg.className = 'card-img-top';
        courseImg.src = courses[i].posterLocation;
        courseDiv.appendChild(courseImg);

        //course - div (container card)
        let cardBody = document.createElement('div');
        cardBody.className='card-body';
        courseDiv.appendChild(cardBody);

        //course - name
        let courseName = document.createElement('h5');
        courseName.className='card-title';
        let _name = document.createTextNode(courses[i].name.toUpperCase());
        courseName.appendChild(_name);
        cardBody.appendChild(courseName);

        //course - description
        let courseType = document.createElement('p');
        courseType.className='card-text';
        let _courseType = document.createTextNode(courses[i].type);
        courseType.appendChild(_courseType);
        cardBody.appendChild(courseType);

        //Add course card to courses container
        courses-container.appendChild(courseDiv);
        };
        displayTypes(courseTypes);

    }

function filterByType(type){
    let searchBox = document.getElementById("myInput");
    searchBox.value = type;
    searchBox.click();
}

function displayTypes(courseTypes){
    const typesContainer = document.getElementById("typesContainer");
    courseTypes.forEach(function(type){
        let typeLink = document.createElement('button');
        typeLink.className = 'btn btn-success';
        typeLink.setAttribute("onclick", "filterByType('"+type+"'); loadFilter(); ");
        typeLink.innerText = type;
        typesContainer.appendChild(typeLink);
    })
}


function getActiveCoursesFunction() {
    var activeObj = {};
    var activeCourses = localStorage.getItem("active-courses");
    activeObj["functionName"] = "getAllcourses";
    activeObj["title"] = "Всички курсове";
    activeObj ["courses"] = allCourses;

    console.log("active FUNC", activeObj["functionName"]);
    return activeObj;
}

function setCurrentOption(dropdown_id, option_id) {
    var functionName = getActiveCoursesFunction()["functionName"];
    var title = getActiveCoursesFunction().title;
    let currentOptionElement;
    var optionsArr = [];
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

}