
document.addEventListener("DOMContentLoaded", function(event){
	const container = document.getElementById("courses-container");
	console.log(container);
	//TODO: images, names, times to be get from backend
	const names=["Borderlands 2", "Draconball Z", "Zelda", "Ghost Recon", "Sims4", "Tearaway", "Age of Empires II", "The Witcher", "Past Cure", "Red Dead Redemption II", "Call of Duty", "Final Fantasy VII", "Doom", "FarmVille", "Jedi", "Persona5", "Bloodhorne", "Daysgone", "Daysgone II", "NBA2K20", "Killzone", "Mortal Kombat II", "Outer Worlds", "Fallout 4"];
	const type = "CSS";
    const durationHours = "2 часа";
    const images = ["1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg", "15.jpg", "16.jpg", "17.jpg", "18.jpg", "19.jpg", "20.jpg", "21.jpg", "22.jpg", "23.jpg", "24.jpg"];
	const description = "Това е най-добрия курс за CSS";

	function makeRows(rows,cols){
		container.style.setProperty('--grid-rows', rows);
		container.style.setProperty('--grid-cols', cols);
	
		for(i = 0; i < (rows*cols); i++){

			//course - div (container card)
            let courseDiv = document.createElement('div');
            courseDiv.className='course-card';

            //course - name
			let courseName = document.createElement('h3');
			let _name = document.createTextNode(names[i].toUpperCase());
			courseName.appendChild("Gosho");
			courseDiv.appendChild(courseName);

			//course - img
			let courseImg = document.createElement('img');
			courseImg.src="https://vkusnoipolezno.bg/wp-content/uploads/2021/01/sladko-ot-qgodi-buzko.jpg";
			console.log(courseImg.src);
			courseDiv.appendChild(courseImg);

			let courseDuration = document.createElement('p');
            //let timeText = document.createTextNode(times[i]);
            courseDuration.appendChild(durationHours);
            courseDiv.appendChild(courseDuration);

            let courseLink =  document.createElement('a');
            courseLink.href ="course-screen.html";

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
