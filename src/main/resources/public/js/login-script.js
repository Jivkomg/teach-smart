$(function () {
  const login = $("#login");
  const displayError = $("#errorField");

  const validationRules = {
    rules: {
      username: {
        required: true,
        minlength: 4
      },
      password: {
        required: true,
      },
    },
    messages: {
      username: {
        required: "Моля, въведете потребителско име!",
        minlength: "Потребителското име трябва да е поне 4 символа!",
      },
      password: {
        required: "Моля, въведете парола!",
      }
    }
  };

  const submitForm = () => {
    console.log('heyo, you re in!');
    let username = $("#username").val().toString();
    let password = $("#password").val().toString();
    const userData = {
      username,
      password
    };

    $.ajax({
      type: "POST",
      url: "/user/login",
      data: JSON.stringify(userData),
      contentType: "application/json",

      success: () => {
        sessionStorage.setItem("username", username);
        localStorage.setItem("username", username);

        window.location.href = "/courses";
      },
      error: (jqXHR) => {
        if (jqXHR.status === 401) {
          displayError.text("Не съществува такъв потребител!");
        } else {
          console.log(jqXHR);
          displayError.text("Something went wrong! Please, try again later!");
        }
      }
    });
  };

  $(document).on("click", "#submitButton", (e) => {
    login.validate({ ...validationRules, submitHandler: submitForm });
  });
});
