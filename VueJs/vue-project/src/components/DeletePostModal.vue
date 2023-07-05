<template>
    <div class="modal fade" id="deletePostModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">刪除貼文</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                            
                <div class="modal-body">
                    <p>你確定要刪除貼文</p>
                </div>
                    
                <div class="modal-footer">
                    <button @click="deletePost">刪除</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                </div>
            </div>
            
        </div>
    </div> 
</template>


<script setup>

const props = defineProps([
    'selectedPostId'
])

const deletePost = () => {
    let token = localStorage.getItem('jwtToken')

    fetch(`/api/post/delete?postId=${props.selectedPostId}`, {
        method: 'DELETE',
        headers: {
            Authorization: "Bearer " + token,
        },
    })
    .then (response => {
        window.location.href = "/mypost";
    })
}
</script>

