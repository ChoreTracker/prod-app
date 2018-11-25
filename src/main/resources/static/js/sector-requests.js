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

// const sectorAddButton = document.querySelector('.add-sector button');
// const sectorAddInput = document.querySelector('.add-sector input');
// const sectorsList = document.querySelector('.sectors-list ul');
// const sectorRemoveButton = document.querySelector('.remove-sector button');
// const sectorRemoveInput = document.querySelector('.remove-sector input');

// const xhr = new XMLHttpRequest()
// xhr.onreadystatechange = function(){
// 	if(xhr.readyState===4 && xhr.status ===200){
// 		const res = xhr.responseText;
// 		sectorsList.innerHTML = res;
// 	}
// }
// 
// sectorAddButton.addEventListener('click', function(){
// 	postSectors(sectorAddInput.value);
// 	sectorAddInput.value = "";
// })
// 
// sectorRemoveButton.addEventListener('click', function(){
// 	var ask = confirm('Are you sure?');
// 	if(ask == true){
// 		removeSectors(sectorRemoveInput.value);
// 		sectorRemoveInput.value = "";
// 	}
// })

// function postSectors(sectorName){
// 	xhr.open('POST', '/sectors/add/' + sectorName, true);
// 	xhr.send();
// }

// function removeSectors(sectorName){
// 	xhr.open('POST', '/sectors/remove/' + sectorName, true);
// 	xhr.send();
// }