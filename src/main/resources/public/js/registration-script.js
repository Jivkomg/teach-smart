$(function () {
  const registration = $('#registration');
  const displayError = $("#errorField");

  const validationRules = {
    rules: {
      username: {
        required: true,
        minlength: 4
      },
      email: {
        required: true,
        email: true
      },
      password: {
        required: true,
        minlength: 4
      },
      repeatPassword: {
        minlength: 4,
        equalTo: "#password"
      }
    },
    messages: {
      username: {
        required: "Моля, въведете потребителско име!",
        minlength: "Потребителското име трябва да е поне 4 символа!",
      },
      password: {
        required: "Моля, въведете парола!",
        minlength: "Паролата трябва да е поне 4 символа!"
      },
      repeatPassword: {
        minlength: "Паролата трябва да е поне 4 символа!",
        equalTo: "Паролите трябва да съвпадат!",
      },
      email: "Моля, въведете валиден имейл адрес!"
    },
  };

  const submitForm = () => {
    const username = $("#username").val().toString();
    const email = $("#email").val().toString();
    const password = $("#password").val().toString();
    const userData = {
      username,
      email,
      password
    };

    $.ajax({
      type: "POST",
      url: "/user/registration",
      data: JSON.stringify(userData),
      contentType: "application/json",

      success: () => {
        window.location.href = "/login";
      },
      error: () => displayError.text("Something went wrong! Please, try again later!")
    });
  };

  $(document).on('click', '#submitButton', (e) => {
    // @ts-ignore
    registration.validate({
      ...validationRules,
      submitHandler: submitForm
    });
  });
});
