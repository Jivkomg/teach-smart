$(function () {
    // Display username and email of the current user
    const username = localStorage.getItem("username").toString();
    const displayErrorMessage = $("#errorField");
    const displaySuccessMessage = $("#successField");

    const getCurrentUserProfilePic = () => {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/user/current/profile-pic/" + sessionStorage.getItem('username'),
            success: (response) => {
                console.log(response);
                const image = new Image();
                image.src = 'data:image/jpg;base64,' + response.encodedImage;
                document.getElementById("imageResult").appendChild(image);
            },
            error: (err) => {
                console.log(err); displayErrorMessage.text('Възникна грешка!');
            }
        });
    };

    $("#readonlyUsername").val(username);
    $.ajax({
        type: 'get',
        contentType: 'application/json; charset=utf-8',
        url: `user/current/${username}`,
        success: (data) => { console.log(data); $("#readonlyEmail").val(data.email); },
        error: (err) => { console.log(err); displayErrorMessage.text('Възникна грешка!'); }
    });
    getCurrentUserProfilePic();

    const input = document.getElementById('upload');;
    const fileNameField = document.getElementById('file-name-field');

    $('#upload').on('change', () => {
        readURL(input);
    });

    const showFileName = (event) => {
        const input = event.srcElement;
        const fileName = input.files[0].name;
        fileNameField.textContent = fileName;
    };

    input.addEventListener('change', showFileName);

    // Save newly uploaded profile pic
    $(document).on('click', '#saveButton', (e) => {
        const formData = new FormData();
        const input = document.getElementById("upload");
        const file = input.files[0];

        if (file.length > 0)
            formData.append("profile_pic", file);

        $.ajax({
            url: `file/upload/${username}`,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: (data) => { displaySuccessMessage.text('Промените са запазени!'); },
            error: (err) => { console.log(err); displayErrorMessage.text('Възникна грешка!'); }
        });
    });

});

const readURL = (input) => {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#imageResult')
                .attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
};