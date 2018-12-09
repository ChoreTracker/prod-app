function openSubNav(buttonType, navType) {
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
    document.getElementById(buttonType).classList += " active";
}

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

