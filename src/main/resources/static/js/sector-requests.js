const sectorAddButton = document.querySelector('.add-sector button');
const sectorAddInput = document.querySelector('.add-sector input');
const sectorsList = document.querySelector('.sectors-list ul');
// const sectorRemoveButton = document.querySelector('.remove-sector button');
// const sectorRemoveInput = document.querySelector('.remove-sector input');

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
	if(xhr.readyState===4 && xhr.status ===200){
		const res = xhr.responseText;
		sectorsList.innerHTML = res;
	}
}

sectorAddButton.addEventListener('click', function(){
	postSectors(sectorAddInput.value);
	sectorAddInput.value = "";
})

// sectorRemoveButton.addEventListener('click', function(){
// 	var ask = confirm('Are you sure?');
// 	if(ask == true){
// 		removeSectors(sectorRemoveInput.value);
// 		sectorRemoveInput.value = "";
// 	}
// })

function postSectors(sectorName){
	xhr.open('POST', '/sectors/add/' + sectorName, true);
	xhr.send();
}

// function removeSectors(sectorName){
// 	xhr.open('POST', '/sectors/remove/' + sectorName, true);
// 	xhr.send();
// }

const missions=document.querySelectorAll(".mission");

for(let i=0; i<missions.length; i++) {
	let button=missions[i].querySelector(".missionButton");
	buttonid="button"+ i;
	button.id = buttonid;
	console.log(button.id);
 
	var modal = missions[i].getElementsByClassName('modal')[0];
	modalid = "modal" + i;
	modal.id = modalid;
	console.log(modal.id);

	var span = missions[i].getElementsByClassName("close")[0];
	spanid = "span"+ i;
	span.id = spanid;
	console.log(span.id);

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
			// style.display = "none";
		console.log("Hello")
	})
	window.addEventListener("click", function(event) {
		var currentIndex = i;
		var modalIndex = "modal" + currentIndex;
		modal = document.getElementById(modalIndex);
		if (event.target == modal) {
		modal.style.display = "none";
		}
	})
}




// // Get the modal
// var modal = document.getElementsByClassName('modal');

// // Get the button that opens the modal
// var btn = document.getElementsByClassName("missionButton");

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
// }