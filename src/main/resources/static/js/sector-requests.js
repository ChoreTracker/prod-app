const sectorAddButton = document.querySelector('.add-sector button');
const sectorAddInput = document.querySelector('.add-sector input');
const sectorsList = document.querySelector('.sectors-list ul');
const sectorRemoveButton = document.querySelector('.remove-sector button');
const sectorRemoveInput = document.querySelector('.remove-sector input');

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

sectorRemoveButton.addEventListener('click', function(){
	var ask = confirm('Are you sure?');
	if(ask == true){
		removeSectors(sectorRemoveInput.value);
		sectorRemoveInput.value = "";
	}
})

function postSectors(sectorName){
	xhr.open('POST', '/sectors/add/' + sectorName, true);
	xhr.send();
}

function removeSectors(sectorName){
	xhr.open('POST', '/sectors/remove/' + sectorName, true);
	xhr.send();
}