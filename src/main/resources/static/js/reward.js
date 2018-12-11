const viewRewardsButton = document.getElementById("view-rewards-button");
const closeViewRewardsSpan = document.getElementById("close-view-rewards");
const viewRewardsModal = document.getElementById("view-rewards-modal");

viewRewardsButton.addEventListener("click", function(){
	if (!viewRewardsModal.classList.contains("opened")){
		viewRewardsModal.classList.add("opened")
	}
	 else {
		viewRewardsModal.classList.remove("opened")
	}
})

closeViewRewardsSpan.addEventListener("click",function() {
	if(!viewRewardsModal.classList.contains("opened")){
		viewRewardsModal.classList.add("opened")
	} else {
		viewRewardsModal.classList.remove("opened")
	}
})

window.addEventListener("click", function(event) {
	if (event.target == viewRewardsModal) {
		viewRewardsModal.classList.remove("opened")
	}
})
