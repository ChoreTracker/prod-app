

$("document").ready(function () {
    $('#myMissionsButton').trigger('click'); 
 
});

function openMissions(buttonType, missionType) {
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
    document.getElementById(buttonType).classList += " active";
    // document.getElementById(missionType).classList += " active";
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

// function openSubNav(evt, navType) {
//     var i, navContent, navLinks;
//     navContent =  document.getElementsByClassName("navContent");
//     for(i = 0; i < navContent.length; i++){
//         navContent[i].style.display = "none";
//     }
//     navLinks = document.getElementsByClassName("navLink");
//     for (i = 0; i < navLinks.length; i++){
//         navLinks[i].className = navLinks[i].className.replace(" active", "");
//     }
//     document.getElementById(navType).style.display = "block";
//     evt.currentTarget.className += " active";
// }