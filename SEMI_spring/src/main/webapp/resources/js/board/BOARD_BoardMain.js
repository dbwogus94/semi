window.onload = function(){
	imgTagDelete()
}


//원하는 위치의 img 태그를 찾아 css로 이미지 제거
function imgTagDelete(){
	var content = document.getElementsByClassName("content")
	
	for(var i = 0; i<content.length; i++){
		var imgTag = content[i].getElementsByTagName("img")
		for(let j = 0; j<imgTag.length; j++){
			imgTag[j].style.display = "none"
		}
		 
	}
}


// img를 포함한 부모태그를 삭제
function imgTagParentDelete(){
	var content = document.getElementsByClassName("content")
	
	// 썸머노트로 작성된 body에서 img를 포함하고 있는 p태그와 자손 삭제
	console.log(content)
	for(let i = 0; i<content.length; i++){
		let pArr = content[i].getElementsByTagName("p")
		for(let j = 0; j<pArr.length; j++){
			// p태그가 img태그를 포함한다면?
			if(pArr[j].getElementsByTagName("img")){
				pArr[j].parentNode.removeChild(pArr[j])		// 부모태그를 찾고 해당 부모태그에서 자식 요소 선택하여 삭제
			}
		}
	}
}


function paging(pageNum){
	console.log(pageNum)
//	var url = "main.do?currentPage=" + pageNum
//	location.href = url
	
}