<template>
    <div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">MyPostModal</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                            
                <div class="modal-body">
                    <p>你確定要登出嗎</p>
                    <button @click="logout" data-bs-target="#updatePostModal" data-bs-toggle="modal">登出</button>
                </div>
                    
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                </div>
            </div>
            
        </div>
    </div> 
</template>

<script setup>
const logout = () => {
    emit('logout')
}

const emit = defineEmits([
    'logout'
])
</script>