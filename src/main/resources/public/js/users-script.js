
document.addEventListener("DOMContentLoaded", function (event) {
	document.getElementById("username").innerText = localStorage.getItem("username");
});

document.addEventListener("DOMContentLoaded", function (event) {
	getAllUsers();

});

function getAllUsers() {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/user/all",
		success: function (allUsers) {
			displayUsers(allUsers);
			console.log(allUsers);
		},
		error: function (e) {
			console.log("ERROR: ", e);
		}
	});
}

//TODO: USER AVATAR
let userAvatars = ["https://i.ibb.co/z69VD7h/1.png", "https://i.ibb.co/mhMM7hJ/2.png", "https://i.ibb.co/G0VZ5Kv/3.png", "https://i.ibb.co/7zhNdGN/4.png", "https://i.ibb.co/rZ48ZYM/5.png", "https://i.ibb.co/cJ8zhmj/6.png", "https://i.ibb.co/C8jQYW5/7.png", "https://i.ibb.co/2ZgCnHQ/8.png", "https://i.ibb.co/VBKb5Qz/9.png", "https://i.ibb.co/74mzc0B/10.png"];

function displayUsers(users) {
	const container = document.getElementById("users-container");
	console.log(container);
	const cols = 4;
	const rows = Math.ceil(users.length / cols);
	container.style.setProperty('--grid-rows', rows);
	container.style.setProperty('--grid-cols', cols);

	for (i = 0; i < (rows * cols) - 1 ; i++) {
		let userDiv = document.createElement('div');
		userDiv.className='card border border-white bg-dark text-white';
		userDiv.id = users[i].userId;

		let userImg = document.createElement('img');
		let randomAvatar = Math.floor(Math.random() * 10); //10 avatars available
		userImg.src = userAvatars[randomAvatar];
		userImg.className = 'card-img-top';
		userDiv.appendChild(userImg);

		//course - div (container card)
                let cardBody = document.createElement('div');
                cardBody.className='card-body';
                userDiv.appendChild(cardBody);

		let userName = document.createElement('p');
		let userNameText = users[i].username;
		let text = document.createTextNode(userNameText);
		userName.className='card-title';
		userName.appendChild(text);
		cardBody.appendChild(userName);

		let userEmail = document.createElement('p');
		let userEmailText = users[i].email;
		let email = document.createTextNode(userEmailText);
		userEmail.className='card-text';
		userEmail.appendChild(email);
		cardBody.appendChild(userEmail);
		container.appendChild(userDiv);
	};
}
