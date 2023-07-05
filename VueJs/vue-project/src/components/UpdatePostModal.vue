<template>
    <div class="modal fade" id="updatePostModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel" data-bs-interval="false" style="width: 500px; height: 500px;">
                    <div v-for="(photo, index) in postPhotos" id="post-photo-container" class="carousel-inner">
                        <div v-if="index == 0" class="carousel-item active">
                            <img :src="'data:image/jpeg;base64,'+ photo.postPhotoFile" class="d-block w-100 single-post-photo" alt="...">
                        </div>
                        <div v-else class="carousel-item">
                            <img :src="'data:image/jpeg;base64,'+ photo.postPhotoFile" class="d-block w-100 single-post-photo" alt="...">
                        </div>
                    </div>

                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>

                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">修改貼文</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <form @submit.prevent="updatePost" id="postForm">
             	<div class="modal-body">
             		<input type="hidden" name="postId" :value="selectedPostId">
                     <!-- <input type="hidden" name="postDate" :value="postDate"> -->
                    <div>
             			<input id="location" class="form-control" name="location" placeholder="請輸入您的位置" />
             		</div>
                 	<div>
                    	<textarea class="form-control" name="text" id="text" rows="1" placeholder="留言......"></textarea>
                 	</div>
             	</div>
             	
             	<div class="modal-footer">
             		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
            		<button type="submit" class="btn btn-primary">修改</button>
             	 </div>
             </form>
            </div>
            
        </div>
    </div> 
</template>

<script setup>
    import { ref, reactive } from 'vue'

    const props = defineProps([
        'selectedPostId'
    ])

    const postDate = ref('')
    const firstname = ref('')
    const memberPhoto = ref('')
    const postPhotos = reactive([])

    const updatePost = (e) => {
        let token = localStorage.getItem('jwtToken')
        let formdata = new FormData(e.target)

        fetch ("/api/post/update", {
			method: "PUT",
            headers: {
                Authorization: "Bearer " + token,
            },
			body: formdata
		})
		.then (response => {
            document.getElementById("postForm").reset();
            window.location.href = "/mypost";
        })
    }

    const getPostDetail = () => {
        let token = window.localStorage.getItem("jwtToken")

        fetch(`api/post/getById?postId=${props.selectedPostId}`, {
            method: "GET",
            headers: {
                Authorization: "Bearer " + token
            },
        })
        .then(response => response.json())
        .then(result => {
            document.getElementById('location').value = result.location
            document.getElementById('text').value = result.text
            postDate.value = result.postDate
            firstname.value = result.firstname
            memberPhoto.value = result.memberPhoto
            postPhotos.length = 0
            result.postPhotoDTOs.forEach(element => postPhotos.push(element))
        })
        .catch(err => {
            console.log(err);
        });
    }

    defineExpose ({
        getPostDetail
    })


</script>


<style scoped>
    .single-post-photo {
        width:500px;
        min-height: 500px;
        object-fit: cover;
    }
</style>
