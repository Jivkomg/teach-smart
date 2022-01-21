document.getElementById("a-events-top30").addEventListener("click", function(e) {
    console.log("clickeddeded 30");
    localStorage.setItem("active-events", "top30");
    console.log("active tab = ", localStorage.getItem("active-events"));
});
document.getElementById("a-events-all").addEventListener("click", function(e) {
    localStorage.setItem("active-events", "all");
    console.log("active tab = ", localStorage.getItem("active-events"));
});


document.addEventListener("DOMContentLoaded", function(event) {
    document.getElementById("username").innerText = localStorage.getItem("username");
    getCurrentUser();
})

function ShowSaveConfirmation() {
    document.getElementById("save-info-message").style.visibility = "visible";
}

function HideSaveConfirmation() {
    document.getElementById("save-info-message").style.visibility = "hidden";
}

function getCurrentUser() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/user/current/" + sessionStorage.getItem('username'),
        success: function(user) {
            console.log(user);
            document.getElementById("username-h2").innerHTML = "<h2>Username: " + user.username + "</h2>";
            document.getElementById("email-h2").innerHTML = "<h2>Email: " + user.email + "</h2>";

        },
        error: function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function onFileUploadSubmit() {
    document.getElementById("username_form").value = sessionStorage.getItem('username');

    $('#profile_pic_upload').ajaxForm({
        url: "/file/upload",
        type: "POST",
        success: function(response) {
            alert("The server says: " + response);
        },
        error: function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function formSubmit(e) {
    e.preventDefault(); //This will prevent the default click action

    var formData = new FormData();
    var input = document.getElementById("profile_pic");
    var file = input.files[0];
    formData.append("profile_pic", file);

    $.ajax({
        type: 'POST',
        url: "/file/upload/" + sessionStorage.getItem('username'),
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            alert("The server says: " + response);
        },
        error: function(e) {
            alert("ERROR: ", e);
        }
    });
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            $('#pic_preview').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]); // convert to base64 string
    }
}

$("#profile_pic").change(function() {
    document.getElementById('uploaded-h2').style.visibility = "visible";
    readURL(this);
});