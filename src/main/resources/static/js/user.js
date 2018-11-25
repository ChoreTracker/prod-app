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