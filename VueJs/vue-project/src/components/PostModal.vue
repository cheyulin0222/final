<template>
    <div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
    
                <div class="modal-header justify-content-between align-items-center">
                    
                    <div class="user-profile">
                        <div id="post-user-photo">
                            <img :src="'data:image/jpeg;base64,'+ memberPhoto" class="circleImg" alt="...">
                        </div> 
                        <div id="post-user-info" class="user-info">
                            <p class="test-title">{{firstname}}</p>
                            <p class="test">{{location}}</p>
                        </div>
                    </div>
                    <div class="test-title" type="button" data-bs-toggle="modal" data-bs-target="#moreModal">
                        <img class="icon" src="../assets/img/icon/more.png">
                    </div>
                </div>
                
                <div id="postModalContent" class="modal-body">
                    <div v-for="(comment, key) in postComments" class="mytable" id="list_table_json">
                        <div class="comment-user-profile">
                            <img class="circleImg" :src="'data:image/jpeg;base64,'+ comment.memberPhoto"/>
                            <div class="comment">
                                <p class="test-title">{{comment.firstname}} {{comment.text}}</p>
                                <p class="test" type="button" @click="enableForm(comment.postCommentID,key)">回覆</p>
                                <button v-if="comment.replyAmount > 0 && comment.enableCheck" @click="getPostCommentComment(comment.postCommentId, key)" class="test-title">查看{{comment.replyAmount}}則留言</button>
                                <button v-if="comment.replyAmount < 0 || !comment.enableCheck" @click="hidePostCommentComment(key)" class="test-title">隱藏留言</button>
                            </div>

                            <img v-if="comment.myLikeId == null" class="icon" type="button" :src="heart" @click="commentLike(comment.postCommentId, key)">
                            <img v-else class="icon" type="button" :src="heartClick" @click="commentDislike(comment.myLikeId, key)">

                        </div>

                        <form v-if="comment.enableForm" @submit.prevent="addPostCommentComment(comment.postCommentId, key)"  id="dataform">
                            <textarea v-model="ccInputText" class="form-control" id="exampleFormControlTextarea1" rows="1" placeholder="留言......"></textarea>
                            <button style="background-color:transparent; border: none;" type="submit">發佈</button>
                        </form>

                        <div v-for="(commentComment, num) in comment.postCommentComments" class="comment-user-profile comment">
                            <img class="circleImg" :src="'data:image/jpeg;base64,' + commentComment.memberPhoto" />
                            <div class="comment">
                                <p class="test-title">{{commentComment.firstname}} {{commentComment.text}}</p>
                            </div>
                            <img v-if="commentComment.myLikeId == null" class="icon" type="button" :src="heart" @click="commentCommentLike(commentComment.postCommentCommentId, key, num)">
                            <img v-else class="icon" type="button" :src="heartClick" @click="commentCommentDislike(commentComment.myLikeId, key, num)">
                        </div>

                    </div>
                </div>
                
                <div id="postModalFooter" class="modal-footer">
                    <form @submit.prevent="addPostComment()" id="dataform">
                        <textarea v-model="inputText" class="form-control" id="exampleFormControlTextarea1" rows="1" placeholder="留言......"></textarea>
                        <button style="background-color:transparent; border: none;" type="submit">發佈</button>
                    </form>
                </div>

            </div>
        </div>
    </div>  
        
</template>

<script setup>
    import { ref, reactive } from 'vue'

    import heart from '../assets/img/icon/heart.png';
    import heartClick from '../assets/img/icon/heartClick.png';

    const postId = ref(0)
    const location = ref('')
    const firstname = ref('')
    const memberPhoto = ref('')
    const postPhotos = reactive([])

    const postComments = reactive([])
    const postCommentId = ref(0)
    const inputText = ref("")
    const ccInputText = ref("")

    const getPost = (id) => {
        postId.value = id
        getPostDetail()
        getPostComment()
    }

    const getPostDetail = () => {
        let token = window.localStorage.getItem("jwtToken")

        fetch(`api/post/getById?postId=${postId.value}`, {
            method: "GET",
            headers: {
                Authorization: "Bearer " + token
            },
        })
        .then(response => response.json())
        .then(result => {
            location.value = result.location
            firstname.value = result.firstname
            memberPhoto.value = result.memberPhoto
            postPhotos.length = 0
            result.postPhotoDTOs.forEach(element => postPhotos.push(element))
        })
        .catch(err => {
            console.log(err);
        });
    }
    
    const getPostComment = () => {
        let token = window.localStorage.getItem("jwtToken")
        
        fetch(`/api/postComment/get?postId=${postId.value}`, {
            method: "GET",
            headers: {
                Authorization: "Bearer " + token,
            },
        })
        .then(response => response.json())
        .then(result => {
            postComments.length = 0
            result.forEach(element => {
                element.enableForm = false
                element.enableCheck = true
                postComments.push(element)
            }) 
        })
        .catch(err => {
            console.log(err);
        });    
    }

    const addPostComment = () => {
        let token = window.localStorage.getItem("jwtToken")
        let postComment = {
            text: inputText.value,
            post: {
                postId: postId.value
            }
        }

        fetch("/api/postComment/post", {
		method: "POST",
		headers: {
            Authorization: "Bearer " + token,
 		    "Content-Type": "application/json; charset=UTF-8"
	 	},
		body: JSON.stringify(postComment)
        })
        .then(response => response.json())
        .then(result => {
            inputText.value = ""
            postComments.length = 0
            result.forEach(element => {
                element.enableForm = false
                element.enableCheck = true
                postComments.push(element)
            }) 
        })
        .catch(err => {
            console.log(err);
        });
    }

    const addPostCommentComment = (id, key) => {
        let token = window.localStorage.getItem("jwtToken")
        let postCommentComment = {
            text: ccInputText.value,
            postComment: {
                postCommentId: id
            }
        } 

        fetch("/api/postCommentComment/post", {
		method: "POST",
		headers: {
            Authorization: "Bearer " + token,
 		    "Content-Type": "application/json; charset=UTF-8"
	 	},
		body: JSON.stringify(postCommentComment)
        })
        .then(response => response.json())
        .then(result => {
            ccInputText.value = ""
            postComments[key].enableForm = false
            postComments[key].enableCheck = false
            postComments[key].postCommentComments.length = 0
            postComments[key].replyAmount = result.length
            result.forEach(element => {
                postComments[key].postCommentComments.push(element)
            }) 
        })
        .catch(err => {
            console.log(err);
        });
    }

    const getPostCommentComment = (id, key) => {
        let token = window.localStorage.getItem("jwtToken")
        
        fetch(`/api/postCommentComment/get?postCommentId=${id}`, {
            method: "GET",
            headers: {
                Authorization: "Bearer " + token,
            },
        })
        .then(response => response.json())
        .then(result => {
            postComments[key].enableCheck = false
            postComments[key].postCommentComments.length = 0
            postComments[key].replyAmount = result.length
            result.forEach(element => {
                postComments[key].postCommentComments.push(element)
            }) 
        })
        .catch(err => {
            console.log(err);
        });    
    }

    const hidePostCommentComment = key => {
        postComments[key].enableCheck = true
        postComments[key].postCommentComments.length = 0
    }

    const commentLike = (postCommentId, key) => {

        let token = window.localStorage.getItem("jwtToken")

        let postCommentLike = {
            postComment: {
                postCommentId: postCommentId
            }
        }
        fetch('/api/postCommentLike/post', {
            method: "POST",
            headers: {
                Authorization: "Bearer " + token,
                'Content-Type': 'application/json;'
            },
            body: JSON.stringify(postCommentLike)
        })
        .then (response => response.json())
        .then (result => {
            postComments[key].myLikeId = result.postCommentLikeId
        })
    }

    const commentDislike = (postCommentLikeId, key) => {
        let token = window.localStorage.getItem("jwtToken")

        fetch(`/api/postCommentLike/delete?postCommentLikeId=${postCommentLikeId}`, {
            method: "DELETE",
            headers: {
                Authorization: "Bearer " + token,
            },
        })
        .then (response => {
            postComments[key].myLikeId = null
        })
    }

    const commentCommentLike = (id, key, num) => {

        let token = window.localStorage.getItem("jwtToken")

        let postCommentCommentLike = {
            postCommentComment: {
                postCommentCommentId: id
            }
        }
        fetch('/api/postCommentCommentLike/post', {
            method: "POST",
            headers: {
                Authorization: "Bearer " + token,
                'Content-Type': 'application/json;'
            },
            body: JSON.stringify(postCommentCommentLike)
        })
        .then (response => response.json())
        .then (result => {
            postComments[key].postCommentComments[num].myLikeId = result.postCommentCommentLikeId
        })
        }

    const commentCommentDislike = (postCommentCommentLikeId, key, num) => {
        let token = window.localStorage.getItem("jwtToken")

        fetch(`/api/postCommentCommentLike/delete?postCommentCommentLikeId=${postCommentCommentLikeId}`, {
            method: "DELETE",
            headers: {
                Authorization: "Bearer " + token,
            },
        })
        .then (response => {
            postComments[key].postCommentComments[num].myLikeId = null
        })
    }

    const enableForm = (id, key) => {
        ccInputText.value = ""
        postCommentId.value = id
        postComments.forEach(element => element.enableForm = false)
        postComments[key].enableForm = true
    }

    

    defineExpose ({
        getPost
    })
</script>

<style scoped>
        .user-profile {
            display: flex;
            align-items: center;
        }
        .comment-user-profile {
            display: flex;
            align-items: flex-start;
            margin: 10px;
        }

        .user-info {
            margin-left: 10px;
        }
        .icon {
            width: 25px;
            margin-bottom: 10px;
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
            width:500px;
            min-height: 500px;
            object-fit: cover;
        }
        .modal-body {
            padding-left: 7px;
            padding-right: 15px;
        }
		.mytable {
			width: 485px;
            margin-left: 0;
            padding-left: 0;
		}     
        .comment {
            margin-left: 10px;
            width: 400px;
            padding-right: 5px;
        }
        
</style>