const sectorAddButton = document.querySelector('.add-sector button');
const sectorAddInput = document.querySelector('.add-sector input');
const sectorsList = document.querySelector('.sectors-list ul');
const sectorRemoveButton = document.querySelector('.remove-sector button');
const sectorRemoveInput = document.querySelector('.remove-sector input');

// const userAddButton = document.querySelector('.add-user button');
// const userrAddInput = document.querySelector('.add-user input');
// const usersList = document.querySelector('.users-list ul');
// const userRemoveButton = document.querySelector('.remove-user button');
// const userrRemoveInput = document.querySelector('.remove-user input');

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
	if(xhr.readyState===4 && xhr.status ===200){
		const res = xhr.responseText;
		sectorsList.innerHTML = res;
	}
}

// const xhr = new XMLHttpRequest()
// xhr.onreadystatechange = function(){
// 	if(xhr.readyState===4 && xhr.status ===200){
// 		const res = xhr.responseText;
// 		usersList.innerHTML = res;
// 	}
// }

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

// userAddButton.addEventListener('click', function(){
// 	postUsers(userAddInput.value);
// 	userAddInput.value = "";
// })

// userRemoveButton.addEventListener('click', function(){
// 	var ask = confirm('Are you sure?');
// 	if(ask == true){
// 		removeUsers(sectorRemoveInput.value);
// 		userRemoveInput.value = "";
// 	}
// })

// function postUsers(userName){
// 	xhr.open('POST', 'users/' + userName, true);
// 	xhr.send();
// }

// function removeUsers(userName){
// 	xhr.open('POST', 'users/remove/' + userName, true);
// 	xhr.send();
// }