<template>
    <div class="modal fade" id="myPostModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">MyPostModal</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                            
                <div class="modal-body">
                    <button data-bs-target="#deletePostModal" data-bs-toggle="modal">刪除</button>
                    <button @click="updatePost" data-bs-target="#updatePostModal" data-bs-toggle="modal">修改</button>
                </div>
                    
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
            
        </div>
    </div> 
</template>

<script setup>
const updatePost = () => {
    emit('updatePost')
}

const emit = defineEmits([
    'updatePost'
])
</script>