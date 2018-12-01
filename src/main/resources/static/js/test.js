//A general Modal Box
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


//The tab text.
function myMissions() {
    var individualMissions2 = document.getElementById("individualMissions2");

    //get current value of the display property
    var displaySetting = individualMissions2.style.display;

    if (displaySetting == 'block') {
        //var is visible. hide it.
        individualMissions2.style.display = 'none';
    }
    else {
        //var is hidden.show it.
        individualMissions2.style.display = 'block';
    }
}

function unclaimedMissions() {
    var unclaimedlMissions2 = document.getElementById("unclaimedMissions2");

    //get current value of the display property
    var displaySetting = unclaimedlMissions2.style.display;

    if (displaySetting == 'block') {
        //var is visible. hide it.
        unclaimedMissions2.style.display = 'none';
    }
    else {
        //var is hidden.show it.
        unclaimedMissions2.style.display = 'block';
    }
}

function allMissions() {
    var allMissions2 = document.getElementById("allMissions2");

    //get current value of the display property
    var displaySetting = allMissions2.style.display;

    if (displaySetting == 'block') {
        //var is visible. hide it.
        allMissions2.style.display = 'none';
    }
    else {
        //var is hidden.show it.
        allMissions2.style.display = 'block';
    }
}



// Pop-up sub-nav
function goToSelectedPopsUp() {
    var gotoSelected = document.getElementById("sub-nav");

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





//Adding a new mission

