
document.addEventListener("DOMContentLoaded", function(event){
	const container = document.getElementById("events-container");
	console.log(container);
	//TODO: images, names, times to be get from backend
	const images = ["1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg", "15.jpg", "16.jpg", "17.jpg", "18.jpg", "19.jpg", "20.jpg", "21.jpg", "22.jpg", "23.jpg", "24.jpg"];
	const names=["Borderlands 2", "Draconball Z", "Zelda", "Ghost Recon", "Sims4", "Tearaway", "Age of Empires II", "The Witcher", "Past Cure", "Red Dead Redemption II", "Call of Duty", "Final Fantasy VII", "Doom", "FarmVille", "Jedi", "Persona5", "Bloodhorne", "Daysgone", "Daysgone II", "NBA2K20", "Killzone", "Mortal Kombat II", "Outer Worlds", "Fallout 4"];
	const times = ["20:59, 08/10/2021", "08:00, 02/01/2021", "18:32, 30/10/2021", "08:31, 10/03/2021", "13:35, 08/04/2021", "21:07, 11/01/2021", "09:01, 18/04/2021", "23:39, 08/04/2021", "06:20, 22/09/2021", "21:15, 07/02/2021", "11:48, 20/04/2021", "02:30, 06/07/2021", "05:01, 14/04/2021", "22:53, 21/05/2021", "22:23, 22/09/2021", "00:14, 13/04/2021", "04:24, 26/01/2021", "22:04, 31/08/2021", "05:35, 22/10/2021", "02:42, 04/02/2021", "12:32, 01/09/2021", "04:17, 12/06/2021", "12:19, 15/07/2021", "02:12, 06/09/2021", "12:27, 30/01/2021", "03:44, 24/04/2021", "13:37, 08/06/2021", "15:28, 29/01/2021", "13:43, 19/10/2021", "03:40, 16/07/2021"];
	const prices = ["$34", "$61", "$87", "$72", "$13", "$94", "$40", "$18", "$32", "$72", "$67", "$32", "$86", "$75", "$62", "$71", "$62", "$85", "$92", "$14", "$56", "$38", "$52", "$59", "$55", "$94", "$42", "$26", "$89", "$46"];
	
	function makeRows(rows,cols){
		container.style.setProperty('--grid-rows', rows);
		container.style.setProperty('--grid-cols', cols);
	
		for(i = 0; i < (rows*cols); i++){
			let eventA =  document.createElement('a');
			eventA.href ="event-screen.html";
			eventA.classList.add('a-card');

			let eventDiv = document.createElement('div');
			eventDiv.className='event-card';
			let eventImg = document.createElement('img');
			eventImg.src="img/games-logos/" + images[i];
			console.log(eventImg.src);
			eventDiv.appendChild(eventImg);
	
			let eventName = document.createElement('p');
			let text = document.createTextNode(names[i].toUpperCase());
			eventName.appendChild(text);
			eventDiv.appendChild(eventName);

			let eventTime = document.createElement('p');
			let timeText = document.createTextNode(times[i]);
			eventTime.appendChild(timeText);
			eventDiv.appendChild(eventTime);

			let eventPrice = document.createElement('p');
			let price = document.createTextNode(prices[i]);
			eventPrice.appendChild(price);
			eventDiv.appendChild(eventPrice);

			eventA.appendChild(eventDiv);
			container.appendChild(eventA);
		};
	};

	makeRows(6,4);
});
