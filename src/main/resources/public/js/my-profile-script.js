$(function () {
    // Display username and email of the current user
    const username = localStorage.getItem("username").toString();
    const displayError = $("#errorField");

    // @ts-ignore
    $("#readonlyUsername").val(username);
    // $.ajax({
    //     type: 'get',
    //     contentType: 'application/json; charset=utf-8',
    //     url: `user/current/${username}`,
    //     success: (data) => { console.log(data); $("#readonlyEmail").val(data.email); },
    //     error: (err) => { console.log(err); displayError.text('Възникна грешка!'); }
    // });

    const input = document.getElementById('upload');;
    const fileNameField = document.getElementById('file-name-field');

    $('#upload').on('change', function () {
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
        const file = input.files[0];
        if (file.length > 0) formData.append('profile_pic', file);

        $.ajax({
            url: `file/upload/${username}`,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: (data) => { console.log(data); },
            error: (err) => { console.log(err); displayError.text('Възникна грешка!'); }
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