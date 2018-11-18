const sectorAddButton = document.querySelector('.add-sector button');
const sectorAddInput = document.querySelector('.add-sector input');
const sectorsList = document.querySelector('.sectors-list ul');
// const sectorRemoveButton = document.querySelector('.remove-sector button');
// const sectorRemoveInput = document.querySelector('.remove-sector input');

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
	if(xhr.readyState===4 && xhr.status ===200){
		const res = xhr.responseText;
		sectorsList.innerHTML = res;
	}
}

sectorAddButton.addEventListener('click', function(){
	postSectors(sectorAddInput.value);
	sectorAddInput.value = "";
})

// sectorRemoveButton.addEventListener('click', function(){
// 	var ask = confirm('Are you sure?');
// 	if(ask == true){
// 		removeSectors(sectorRemoveInput.value);
// 		sectorRemoveInput.value = "";
// 	}
// })

function postSectors(sectorName){
	xhr.open('POST', '/sectors/add/' + sectorName, true);
	xhr.send();
}

// function removeSectors(sectorName){
// 	xhr.open('POST', '/sectors/remove/' + sectorName, true);
// 	xhr.send();
// }

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