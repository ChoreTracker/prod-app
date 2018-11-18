const userAddButton = document.querySelector('.add-user button');
const userAddInput = document.querySelector('.add-user input');
const usersList = document.querySelector('.users-list ul');
const userRemoveButton = document.querySelector('.remove-user button');
const userrRemoveInput = document.querySelector('.remove-user input');

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
	if(xhr.readyState===4 && xhr.status ===200){
		const res = xhr.responseText;
		usersList.innerHTML = res;
	}
}

userAddButton.addEventListener('click', function(){
	postUsers(userAddInput.value);
	userAddInput.value = "";
})

userRemoveButton.addEventListener('click', function(){
	var ask = confirm('Are you sure?');
	if(ask == true){
		removeUsers(sectorRemoveInput.value);
		userRemoveInput.value = "";
	}
})

function postUsers(userName){
	xhr.open('POST', 'users/' + userName, true);
	xhr.send();
}

function removeUsers(userName){
	xhr.open('POST', 'users/remove/' + userName, true);
	xhr.send();
}

// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


