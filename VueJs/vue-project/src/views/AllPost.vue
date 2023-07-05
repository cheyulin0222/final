<template>
    <div class="container">
        <div v-for="(post, key) in posts">
            <!-- Card -->
            <div class="card border-0">

                <!-- Header -->
                <div class="card-header justify-content-between align-items-center">
                    <div class="user-profile">
                        <img class="circleImg" :src="'data:image/jpeg;base64,'+ post.memberPhoto"/>
                        
                        <div class="user-info">
                            <p class="test-title">{{ post.firstname }}</p>
                            <p class="test">{{ post.location }}</p>
                        </div>
                    </div>
                
		            <img class="icon" src="../assets/img/icon/more.png" type="button" data-bs-toggle="modal" data-bs-target="#moreModal"> 
                </div>
                
                <!-- IMAGE SLIDE -->
                <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel" data-bs-interval="false" style="width: 460px; height: 460px;">
                <!-- IMAGE -->
                    <div class="carousel-inner">
                        <div v-for="(photo, index) in post.postPhotoDTOs" >
                            <div v-if="index == 0" class="carousel-item active">
                                <img :src="'data:image/jpeg;base64,'+ photo.postPhotoFile" class="d-block w-100 single-post-photo" alt="...">
                            </div>
                            <div v-else class="carousel-item">
                                <img :src="'data:image/jpeg;base64,'+ photo.postPhotoFile" class="d-block w-100 single-post-photo" alt="...">
                            </div>
                        </div>
                    </div>
                <!-- BUTTON -->
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>

                <!-- CARD BODY -->
                <div class="card-body">
                    
                    <!-- BAR -->
                    <div class="icon-bar">
                        <div class="front-icons card-item">
                            <img  v-if="post.myLikeId != null" id="heart" class="icon" type="button" :src="heartClick" @click="dislike(post.myLikeId, key)">
                            <img  v-else id="heart" class="icon" type="button"  :src="heart" @click="like(post.postId, key)">
                            <img id="comment" class="icon" src="../assets/img/icon/speech-bubble.png" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal" />
                            <img id="send" type="button" class="icon" src="../assets/img/icon/send.png" />
                        </div>
                        <div class="" type="button">
                            <img class="icon" src="../assets/img/icon/save-instagram.png">
                        </div>
                    </div>

                    <!-- LIKE -->
                    <div class="like card-item">
                        <p class="test-title">{{ post.likeAmount }} 個讚</p>
                    </div>

                    <!-- TEXT -->
                    <div class="post-content card-item">
                        <p class="test-title"><span>{{ post.firstname }}</span> {{ post.text }}</p>
                    </div>

                    <!-- TAG -->
                    <div class="tag card-item">
                        <p class="test-title">#dog</p>
                    </div>
                    <!-- CHECK COMMENT -->
                    <div>
                        <button @click="onChildClick(post.postId)" class="test-title" data-bs-toggle="modal" data-bs-target="#postModal">查看留言</button>
                    </div>

                    <!-- HAVE A COMMENT -->
                    <div class="form card-item">
                        <form th:action="@{/postComment/post}" method="post">
                            <input type="hidden" name="postId" :value="post.postId" />
                            <textarea name="text" class="form-control" id="exampleFormControlTextarea1" rows="1" placeholder="留言......"></textarea>
                                        
                            <div>
                                <button type="submit" style="background-color:transparent; border: none; ">發佈</button>
                            </div>
                        </form>
                    </div>

                </div>

            </div>
        </div>
        
        

    </div>

    <!-- MORE MODAL -->
    <div class="modal fade" id="moreModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">More</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                            
                <div class="modal-body">
                    ...
                </div>
                    
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
            
        </div>
    </div> 

    <PostModal ref="child"></PostModal>
    
</template>

<script setup>

    import PostModal from "../components/PostModal.vue";
    import { ref, reactive, onMounted } from 'vue';
    import heart from '../assets/img/icon/heart.png';
    import heartClick from '../assets/img/icon/heartClick.png';

    // const heart = heart;
    // const heartClick = heartClick;

    const child = ref(null)

    const posts = reactive([])

    const onChildClick = (id) => {
        child.value.getPost(id)
    }

    const getAllPost = () => {
        let token = window.localStorage.getItem("jwtToken")

        fetch ('/api/post/getAll', {
            method: "GET",
            headers: {
                Authorization: "Bearer " + token
            },
	    })
	    .then (response => response.json())
	    .then (result => {
            result.forEach(post => posts.push(post))
	    })
    }

    const like = (postId, key) => {

        let token = window.localStorage.getItem("jwtToken")

        let postLike = {
            post: {
                postId: postId
            }
        }
        fetch('/api/postLike/post', {
            method: "POST",
            headers: {
                Authorization: "Bearer " + token,
                'Content-Type': 'application/json;'
            },
            body: JSON.stringify(postLike)
        })
        .then (response => response.json())
        .then (result => {
            posts[key].myLikeId = result.postLikeId
            posts[key].likeAmount++
        })
    }

    const dislike = (postLikeId, key) => {
        let token = window.localStorage.getItem("jwtToken")

        posts[key].myLikeId = null
        posts[key].likeAmount--

        fetch(`/api/postLike/delete?postLikeId=${postLikeId}`, {
            method: "DELETE",
            headers: {
                Authorization: "Bearer " + token,
            },
        })
    }

    onMounted(() => {
        getAllPost()
    })

</script>

<style scoped>

        .card {
            width: 460px;
        }
        .card-header {
            padding-left: 0;
            padding-right: 0;
            background-color: white;
            border: 0;
            display: flex;

        }
        .user-profile {
            display: flex;
            align-items: center;
        }
        .user-info {
            margin-left: 10px;
        }
        .icon-bar {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .icon {
            width: 25px;
            margin-bottom: 10px;
        }
        .front-icons .icon {
            margin-right: 10px;   
        }
        .card-body {
            padding-right: 0;
            padding-left: 0;
            padding-top: 10px;
        }

        .test-title {
            margin: 0;
            font-weight: 400;
            color:black
        }
        .test {
            margin: 0;
            font-size:small;
            font-weight: 300;
            color: grey;
        }
        .circleImg{
            border-radius: 50%;
            width:45px;
            min-height: 45px;
            object-fit: cover;
        }
        .single-post-photo {
            width:460px;
            min-height: 460px;
            object-fit: cover;
        }
</style>