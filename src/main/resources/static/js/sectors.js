
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

const themeButton = document.getElementById("change-theme-button");
const closeThemeSpan = document.getElementById("close-theme");
const themeModal = document.getElementById("theme-modal");

themeButton.addEventListener("click", function(){
	if (!themeModal.classList.contains("opened")){
		themeModal.classList.add("opened")
	}
	 else {
		themeModal.classList.remove("opened")
	}
})

closeThemeSpan.addEventListener("click",function() {
	if(!themeModal.classList.contains("opened")){
		themeModal.classList.add("opened")
	} else {
		themeModal.classList.remove("opened")
	}
})

window.addEventListener("click", function(event) {
	if (event.target == themeModal) {
		themeModal.classList.remove("opened")
	}
})