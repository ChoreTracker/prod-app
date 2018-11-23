

const missions=document.querySelectorAll(".mission");

for(let i=0; i<missions.length; i++) {
	let button=missions[i].querySelector(".missionButton");
	buttonid="button"+ i;
	button.id = buttonid;
	
 
	var modal = missions[i].getElementsByClassName('modal')[0];
	modalid = "modal" + i;
	modal.id = modalid;


	var span = missions[i].getElementsByClassName("close")[0];
	spanid = "span"+ i;
	span.id = spanid;
	


	button.addEventListener("click", function(){
		var currentIndex = i;
		var modalIndex = "modal" + currentIndex;
		modal = document.getElementById(modalIndex);
		if (!modal.classList.contains("opened")){
			modal.classList.add("opened")
		}
		 else {
			modal.classList.remove("opened")
		}
		
	})

	span.addEventListener("click",function() {
		var currentIndex = i;
		var modalIndex = "modal" + currentIndex;
		modal = document.getElementById(modalIndex);
		if(!modal.classList.contains("opened")){
			modal.classList.add("opened")
		} else {
			modal.classList.remove("opened")
		}
		
	})
	window.addEventListener("click", function(event) {
		var currentIndex = i;
		var modalIndex = "modal" + currentIndex;
		modal = document.getElementById(modalIndex);
		if (event.target == modal) {
			modal.classList.remove("opened")
		}
	})
}



// const missions=document.querySelectorAll(".missionButton");

// for(let i=0; i<missions.length; i++) {
// 	let button=missions[i].querySelector("article");

// 	var modal = missions[i].getElementsByClassName('modal');
// 	var span = missions[i].getElementsByClassName("close")[0];
// 	button.addEventListener("click", function(){
// 		if(!modal[0].classList.contains("opened")){
// 			modal[0].classList.add("opened")
// 		} else {
// 			modal[0].classList.remove("opened")
// 		}
		
// 	})

// // Get the modal
// var modal = document.getElementById('myModal');

// // Get the button that opens the modal
// var btn = document.getElementById("myBtn");

// // Get the <span> element that closes the modal
// var span = document.getElementsByClassName("close")[0];

// // When the user clicks the button, open the modal 
// btn.onclick = function() {
//     modal.style.display = "block";
// }

// // When the user clicks on <span> (x), close the modal
// span.onclick = function() {
//     modal.style.display = "none";
// }

// // When the user clicks anywhere outside of the modal, close it
// window.onclick = function(event) {
//     if (event.target == modal) {
//         modal.style.display = "none";
//     }
// 
