function showPopup(popup_id)
{
	document.getElementById(popup_id).style.visibility="visible";
	document.getElementsByClassName("fullscreen-container")[0].style.display="block";
}
function closePopup(popup_id){
document.getElementById(popup_id).style.visibility="hidden";
document.getElementsByClassName("fullscreen-container")[0].style.display="none";
}
