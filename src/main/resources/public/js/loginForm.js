$(document).ready(function () {

  $(document).on("click", "#login", function (event) {
    event.preventDefault();
    checkUser();
  });

  function checkUser() {

    var username = $("#name").val();
    var password = $("#password").val();

    if ((!username) || (!password)) {
      alert("Всички полета трябва да се попълнят!");
    } else {
      var formData = {
        username: username,
        email: "",
        password: password
      };

      $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/user/loginForm",
        data: JSON.stringify(formData),
        success: function () {
          sessionStorage.setItem("username", username);
          localStorage.setItem("username", username);
          var userTickets = [];
          localStorage.setItem("userTickets", JSON.stringify(userTickets));
          console.log("USER ", localStorage.getItem("username"),"|| userTickets: ",localStorage.getItem("userEmail"));

          window.location.href = "/events";
        },
        error: function (jqXHR) {
          if (jqXHR.status === 401) {
            alert("Невалидни данни!")
          } else {
            alert(jqXHR.status);
            console.log(jqXHR);
          }
        }
      });
    }
  }
});
