
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

function openSubNav(evt, navType) {
    var i, navContent, navLinks;
    navContent =  document.getElementsByClassName("navContent");
    for(i = 0; i < navContent.length; i++){
        navContent[i].style.display = "none";
    }
    navLinks = document.getElementsByClassName("navLink");
    for (i = 0; i < navLinks.length; i++){
        navLinks[i].className = navLinks[i].className.replace(" active", "");
    }
    document.getElementById(navType).style.display = "block";
    evt.currentTarget.className += " active";
}