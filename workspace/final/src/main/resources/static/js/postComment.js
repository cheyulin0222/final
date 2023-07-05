function requestComment(event) {
	event.preventDefault();
    let target = event.target;
    let idElement = target.previousElementSibling;
    let id = idElement.getAttribute("value");
    let postObject = { "postId": id };
    let postJsonString = JSON.stringify(postObject);
        
    fetch("http://localhost:8080/my-app/post/getById", {
		method: "POST",
		headers: {
 			"Content-Type": "application/json; charset=UTF-8"
	 	},
		body: postJsonString
	})
	.then(response => response.json())
	.then(result => {
		// Photo
		let postPhoto
		result.postPhotos.forEach((photo, index) => {
			if (index == 0) {
				postPhoto += `<div class="carousel-item active">`
				postPhoto += `<img src="/my-app/postPhoto/getPhoto?id=${photo.postPhotoId}" class="d-block w-100 single-post-photo" alt="...">`
				postPhoto += `</div>`
			}else {
				postPhoto += `<div class="carousel-item">`
				postPhoto += `<img src="/my-app/postPhoto/getPhoto?id=${photo.postPhotoId}" class="d-block w-100 single-post-photo" alt="...">`
				postPhoto += `</div>`
			}
		})
		let photoContainer = document.getElementById("post-photo-container")
		photoContainer.innerHTML = postPhoto
		
		// Profile
		let userPhoto = document.getElementById("post-user-photo")
		userPhoto.innerHTML = `<img class="circleImg" src="/my-app/member/getPhoto?id=${result.member.memberId}" />`

		let userData = `<p class="test-title">${result.member.firstName}</p>`
		userData += `<p class="test">${result.location}</p>`
		let userInfo = document.getElementById("post-user-info")
		userInfo.innerHTML = userData
		
		// CommentList
		let commentData
		result.postComments.forEach(comment => {
			commentData += `<div class="comment-user-profile">`
			commentData += `<img class="circleImg" src="/my-app/member/getPhoto?id=${comment.member.memberId}" />`
			commentData += "<p>" + comment.member.firstName + " " + comment.text  + "</p>"
			if (comment.myLikeId == null)
				commentData += `<img class="icon" type="button" src="../img/icon/heart.png">`
			else 
				commentData += `<img class="icon" type="button" src="../img/icon/heartClick.png">`
			commentData += `</div>`
		})
		let myTable = document.getElementById("list_table_json")
		myTable.innerHTML = commentData
		
		// Add Comment
		let footer = `<form>`
		footer += `<input type="hidden" value="${result.postId}" />`
		footer += `<textarea class="form-control" id="exampleFormControlTextarea1" rows="1" placeholder="留言......"></textarea>`
		footer += `<button style="background-color:transparent; border: none;" onclick="addPostComment(event);">發佈</button>`
		footer += `</form>`
		let modalFooter = document.getElementById("postModalFooter")
		modalFooter.innerHTML = footer
	})
	.catch(err => {
  		console.log(err);
	});
}
