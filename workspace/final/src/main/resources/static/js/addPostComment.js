function addPostComment(event) {
	event.preventDefault();
    let target = event.target;
    let textElement = target.previousElementSibling;
    let text = textElement.value
    let idElement = textElement.previousElementSibling;
    let id = idElement.value
    let postCommentObject = {
		"text": text,
		"post": {
			"postId": id
		}
	};
    let postCommentJsonString = JSON.stringify(postCommentObject);
        
    fetch("http://localhost:8080/my-app/postComment/postnew", {
		method: "POST",
		headers: {
 			"Content-Type": "application/json; charset=UTF-8"
	 	},
		body: postCommentJsonString
	})
	.then(response => response.json())
	.then(commentList => {
		
		// CommentList
		let commentData
		commentList.forEach(comment => {
			target.parentNode.reset();
			commentData += `<div class="comment-user-profile">`
			commentData += `<img class="circleImg" src="/my-app/member/getPhoto?id=${comment.member.memberId}" />`
			commentData += "<p>" + comment.member.firstName + " " + comment.text  + "</p>"
			commentData += `</div>`
		})
		let myTable = document.getElementById("list_table_json")
		myTable.innerHTML = commentData
	})
	.catch(err => {
  		console.log(err);
	});
}
