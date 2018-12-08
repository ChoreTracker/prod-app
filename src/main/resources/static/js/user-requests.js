

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

const newMissionButton = document.getElementById("add-mission-button");
const closeNewMissionSpan = document.getElementById("close-add-mission");
const addMissionModal = document.getElementById("add-mission-modal");

newMissionButton.addEventListener("click", function(){
	if (!addMissionModal.classList.contains("opened")){
		addMissionModal.classList.add("opened")
	}
	 else {
		addMissionModal.classList.remove("opened")
	}
})

closeNewMissionSpan.addEventListener("click",function() {
	if(!addMissionModal.classList.contains("opened")){
		addMissionModal.classList.add("opened")
	} else {
		addMissionModal.classList.remove("opened")
	}
})

window.addEventListener("click", function(event) {
	if (event.target == addMissionModal) {
		addMissionModal.classList.remove("opened")
	}
})

