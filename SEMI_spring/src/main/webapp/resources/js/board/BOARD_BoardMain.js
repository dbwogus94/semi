window.onload = function(){
	var content = document.getElementsByClassName("content")
	
	for(let i = 0; i<content.length; i++){
		var img = content[i].getElementsByTagName("img")
		//content[i].removeChild(img)
		
		for(let j = 0; j<img.length; j++){
			img[j].parentNode.removeChild(img[j]);
		}
	}
}
