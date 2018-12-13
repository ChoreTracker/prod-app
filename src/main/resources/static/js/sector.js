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

function openMissions(evt, missionType) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(missionType).style.display = "block";
    evt.currentTarget.className += " active";
}

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

const assignMissionsButton = document.getElementById("assign-missions");
const closeAssignMissionSpan = document.getElementById("close-assign-mission");
const assignMissionModal = document.getElementById("assign-mission-modal");

assignMissionsButton.addEventListener("click", function(){
	if (!assignMissionModal.classList.contains("opened")){
		assignMissionModal.classList.add("opened")
	}
	 else {
		assignMissionModal.classList.remove("opened")
	}
})

closeAssignMissionSpan.addEventListener("click",function() {
	if(!assignMissionModal.classList.contains("opened")){
		assignMissionModal.classList.add("opened")
	} else {
		assignMissionModal.classList.remove("opened")
	}
})

window.addEventListener("click", function(event) {
	if (event.target == assignMissionModal) {
		assignMissionModal.classList.remove("opened")
	}
})


