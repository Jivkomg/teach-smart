var allCourses;

//header username
document.addEventListener("DOMContentLoaded", function (course) {
    document.getElementById("username").innerText = localStorage.getItem("username");
});

//refresh courses
document.getElementById("all-courses").addEventListener("click", function (e) {
    $("#course-screen").addClass('d-none');
    document.getElementById("courses-section").style.display = "block";
    localStorage.setItem("active-courses", "all");
    console.log("active tab = ", localStorage.getItem("active-courses"));
    getAllcourses();
});

//first load courses
$(document).ready(function () {
    getAllcourses();
});

function getAllcourses() {
    var deferred = $.Deferred();
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/courses/all/",
        success: function (courses) {
            allCourses = courses;
            displayCourses(courses, "Всички курсове");
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

//load course and show popup screen
function getCourseById(id) {
    console.log(id);
    $.ajax({
        type: "GET",
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

//popup course
function showCourseScreen(course) {
    $("#course-screen").removeClass('d-none');
    document.getElementById("courses-section").style.display = 'none';

    //fill info for course popup
    document.getElementById("popup-course-name").innerHTML = course.name;
    document.getElementById("popup-course-description").innerHTML = course.description;
    document.getElementById("popup-course-type").innerHTML = "Категория: " + "<b>" + course.type + "</b>";
    document.getElementById("popup-course-duration").innerHTML = "Продължителност на курса: " + "<b>" + course.duration + "</b>" + " минути";
    document.getElementById("popup-course-attendants").innerHTML = "Одобрили курса: " + "<b>" + course.attendants + "</b>" + " потребители";
    document.getElementById("courseImg").src = course.posterLocation;
}

function hideCourse() {
    $("#course-screen").addClass('d-none');
    document.getElementById("courses-section").style.display = 'block';
}

//load courses
function displayCourses(courses, h1_name) {
    removeExistingCourses();
    document.getElementById("h1_name").innerHTML = h1_name;
    document.getElementById("courses-count").innerHTML = "Брой курсове: " + courses.length;
    const container = document.getElementById("courses-container");
    let courseTypes = new Set();
    //Create all course cards and add it to courses-container
    for (let i = 0; i < courses.length; i++) {

        courseTypes.add(courses[i].type);

        //course - div (container card)
        let courseDiv = document.createElement('div');
        courseDiv.setAttribute("onclick", "getCourseById('" + courses[i].courseId + "')");
        courseDiv.className = 'card border border-white bg-dark text-white';

        let courseImg = document.createElement('img');
        courseImg.className = 'card-img-top';
        courseImg.src = courses[i].posterLocation;
        courseDiv.appendChild(courseImg);

        //course - div (container card)
        let cardBody = document.createElement('div');
        cardBody.className = 'card-body';
        courseDiv.appendChild(cardBody);

        //course - name
        let courseName = document.createElement('p');
        courseName.className = 'card-title';
        let _name = document.createTextNode(courses[i].name.toUpperCase());
        courseName.appendChild(_name);
        cardBody.appendChild(courseName);

        //course - description
        let courseType = document.createElement('p');
        courseType.className = 'card-text';
        let _courseType = document.createTextNode(courses[i].type);
        courseType.appendChild(_courseType);
        cardBody.appendChild(courseType);

        //Add course card to courses container
        courses - container.appendChild(courseDiv);
    };
    displayTypes(courseTypes);

}

//Search box for courses
$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#courses-container div").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
});

function loadFilter() {
    $('#myInput').trigger('keyup');
}

//Search box input from type menu
function filterByType(type) {
    let searchBox = document.getElementById("myInput");
    searchBox.value = type;
    searchBox.click();
}

function displayTypes(courseTypes) {
    const typesContainer = document.getElementById("typesContainer");
    courseTypes.forEach(function (type) {
        let typeLink = document.createElement('а');
        typeLink.className = 'dropdown-item';
        typeLink.setAttribute("onclick", "filterByType('" + type + "'); loadFilter(); ");
        typeLink.innerText = type;
        typesContainer.appendChild(typeLink);
    });
}

//clear loaded courses
function removeExistingCourses() {
    const container = document.getElementById("courses-container");
    container.textContent = '';
}