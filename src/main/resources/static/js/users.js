// Pop-up sub-nav
function goToSelectedPopsUp(navType) {
    //var gotoSelected = document.getElementById("sub-nav-goto");
    var gotoSelected = document.getElementById(navType);
    //var gotoSelected = document.getElementById("sub-nav-settings");
    //var gotoSelected = document.getElementById("sub-nav-logout");

    //get current value of the display property
    var displaySetting = gotoSelected.style.display;

    if (displaySetting == 'block') {
        //var is visible. hide it.
        gotoSelected.style.display = 'none';
    }
    else {
        //var is hidden.show it.
        gotoSelected.style.display = 'block';
    }
}

const newUserButton = document.getElementById("add-user-button");
const closeNewUserSpan = document.getElementById("close-add-user");
const addUserModal = document.getElementById("add-user-modal");

newUserButton.addEventListener("click", function(){
	if (!addUserModal.classList.contains("opened")){
		addUserModal.classList.add("opened")
	}
	 else {
		addUserModal.classList.remove("opened")
	}
})

closeNewUserSpan.addEventListener("click",function() {
	if(!addUserModal.classList.contains("opened")){
		addUserModal.classList.add("opened")
	} else {
		addUserModal.classList.remove("opened")
	}
})

window.addEventListener("click", function(event) {
	if (event.target == addUserModal) {
		addUserModal.classList.remove("opened")
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