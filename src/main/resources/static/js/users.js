// const users=document.querySelectorAll(".user");

// for(let i=0; i<missions.length; i++) {
// 	let button=users[i].querySelector(".userButton");
// 	buttonid="button"+ i;
// 	button.id = buttonid;
// 	console.log(button.id);



// const userButton = document.querySelector('.userButton');

// 	userButton.addEventListener('click', function(){
// 		window.open("@{/user(id=${user.id})}");
// 	})

// function myFunction(){
// 	window.open("@{/user(id=${user.id})}");
// }



// const userAddButton = document.querySelector('.add-user button');
// const userAddInput = document.querySelector('.add-user input');
// const usersList = document.querySelector('.users-list ul');
// const userRemoveButton = document.querySelector('.remove-user button');
// const userrRemoveInput = document.querySelector('.remove-user input');

// const xhr = new XMLHttpRequest()
// xhr.onreadystatechange = function(){
// 	if(xhr.readyState===4 && xhr.status ===200){
// 		const res = xhr.responseText;
// 		usersList.innerHTML = res;
// 	}
// }

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