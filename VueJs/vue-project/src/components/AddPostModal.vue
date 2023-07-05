<template>
    <div class="modal fade" id="addPostModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">新增貼文</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <form @submit.prevent="addPost" id="postForm">
             	<div class="modal-body">
             		<div>
             			<input class="form-control" name="location" placeholder="請輸入您的位置" />
             		</div>
                 	<div>
                    	<textarea class="form-control" name="text" id="exampleFormControlTextarea1" rows="1" placeholder="留言......"></textarea>
                 	</div>
                 	<div>
                    	<input name="file" type="file" />
                 	</div>
             	</div>
             	
             	<div class="modal-footer">
             		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
            		<button type="submit" class="btn btn-primary">發佈</button>
             	 </div>
             </form>
            </div>
            
        </div>
    </div> 
</template>

<script setup>

    const addPost = (e) => {
        let token = localStorage.getItem('jwtToken')
        let formdata = new FormData(e.target)

        fetch ("/api/post/add", {
			method: "POST",
            headers: {
                Authorization: "Bearer " + token,
            },
			body: formdata
		})
		.then (response => {
            document.getElementById("postForm").reset();
            window.location.href = "/allpost";
        })
    }


</script>
