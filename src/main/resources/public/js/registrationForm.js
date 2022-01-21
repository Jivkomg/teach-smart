$(document).ready(function () {

  $(document).on("click", "#register", function (event1) {
    event1.preventDefault();
    registerUser();
  });

  function registerUser() {

    var name = $("#name").val();
    var email = $("#email").val();
    var pass = $("#password").val();
    var retypePass = $("#repeatPassword").val();
    var isValid = validate();

    if ((!name) || (!email) || (!pass) || (!retypePass) || !isValid) {
      alert("Всички полета трябва да са попълнени!");
    } else {

      if (pass === retypePass) {

        // PREPARE FORM DATA
        var formData = {
          name: name,
          email: email,
          password: pass
        };

        $.ajax({
          type: "POST",
          url: "/user/registrationForm",
          contentType: "application/json",
          data: JSON.stringify(formData),
          success: function () {
            window.location.href = "/loginForm";
          },
          error: function (jqXHR) {
            if (jqXHR.status === 406) {
              alert("Невалиден формат на имейла!");
            } else if (jqXHR.status === 409) {
              alert("Потребителят вече съществува!");
            } else {
              alert(jqXHR.status)
            }
            $("#email").val("");
          }
        });
      } else {
        alert("Двете пароли не съвпадат! Въведи ги отново!");
        $("#password").val("");
        $("#retype_password").val("");
      }
    }
  }
});

//validate user input before registration

function validate()
{
  var result = true;
	var errorFieldsDOM = document.getElementsByClassName("error");
	console.log(errorFieldsDOM);
	//make all error messages display:none 
	for(let i=0;i<errorFieldsDOM.length;i++)
		errorFieldsDOM[i].style.display = "none";

	var username = document.getElementById("name").value;
	var email = document.getElementById("email").value;	
	var password = document.getElementById("password").value;
	var confirmPassword = document.getElementById("repeatPassword").value;	
	var nameOnCard = document.getElementById("name-on-card").value;
	var cardNumber = document.getElementById("card-number").value;
	var expiryDate = document.getElementById("expiry-date").value;
	var cvv = document.getElementById("security-code").value;
	var selectedCreditCard = document.getElementById("selected_card").textContent;
	console.log(selectedCreditCard);

	const visaRegex = /^4[0-9]{3}\s?([0-9]\s?){9}(?:[0-9]{3})?$/;
	const masterCardRegex = /^(?:5[1-5][0-9]{2}| 222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$/;
	const americanExpressRegex = /^3[47][0-9]{13}$/;
	const dinersClubRegex = /^3(?:0[0-5]|[68][0-9])[0-9]{11}$/;

	const americanExpressCvvRegex = /^[0-9]{4}$/;
	const regularCvvRegex = /^[0-9]{3}$/;

	let errorFields = new Array(8);

	//const usernameRegex = //;
	//errorFields[0] = usernameRegex.test(username);

	const emailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	errorFields[0] = emailRegex.test(String(email).toLowerCase());

	// const passwordRegex = //;
	// errorFields[2] = passwordRegex.test(password);

	errorFields[1] = ((password == confirmPassword)== true && password!= "" )? true : false;
	console.log(password == confirmPassword,errorFields[3]);

	const nameRegex = /^([a-zA-Z]{2,}\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\s?([a-zA-Z]{1,})?)/;
	errorFields[2] = nameRegex.test(nameOnCard);

	var cardNumberRegex;
	var cvvRegex = regularCvvRegex;
	switch(selectedCreditCard)
	{
		case "Visa": {cardNumberRegex = visaRegex; break;}
		case "MasterCard": {cardNumberRegex = masterCardRegex; break;}
		case "AmericanExpress": {cardNumberRegex = americanExpressRegex; cvvRegex = americanExpressCvvRegex; break;}
		case "Diners Club": {cardNumberRegex = dinersClubRegex; break;}

	}

	errorFields[3] = cardNumberRegex.test(cardNumber);

	const expiryDateRegex = /^(0[1-9]|1[0-2])\/([0-9]{4}|[0-9]{2})$/;
	errorFields[4] = expiryDateRegex.test(expiryDate);

	errorFields[5] = cvvRegex.test(cvv);

	for(let i=0;i<errorFieldsDOM.length;i++)
	{
		console.log(errorFields[i]);
		if(!errorFields[i])
		{
      errorFieldsDOM[i].style.display = "block";
      result=false;
		}
  }
  return result;

}



function setCurrentCreditCard(card_id){
	let currentCardElement = document.querySelector(".dropdown-bar ul li a:first-child");
	let oldCard = currentCardElement.textContent; //old user because will be replased with new current user
	
    let oldCardElement = document.getElementById(card_id);
	let currentCard = oldCardElement.textContent;
	console.log(currentCard);
	console.log(currentCardElement.textContent);
	currentCardElement.innerHTML = currentCard + '<i class="fa fa-angle-down"></i>';
	oldCardElement.innerHTML = oldCard;
}