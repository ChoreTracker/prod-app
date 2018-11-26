
const newSectorButton = document.getElementById("add-sector-button");
const closeNewSectorSpan = document.getElementById("close-add-sector");
const addSectorModal = document.getElementById("add-sector-modal");

newSectorButton.addEventListener("click", function(){
	if (!addSectorModal.classList.contains("opened")){
		addSectorModal.classList.add("opened")
	}
	 else {
		addSectorModal.classList.remove("opened")
	}
})

closeNewSectorSpan.addEventListener("click",function() {
	if(!addSectorModal.classList.contains("opened")){
		addSectorModal.classList.add("opened")
	} else {
		addSectorModal.classList.remove("opened")
	}
})

window.addEventListener("click", function(event) {
	if (event.target == addSectorModal) {
		addSectorModal.classList.remove("opened")
	}
})