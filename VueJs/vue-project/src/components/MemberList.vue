<template>
    <div class="card">
        <div class="card-header justify-content-between  align-items-center">
        <div class="user-profile  ">
            <img class="circleImg" :src="'data:image/jpeg;base64,'+ member.memberPhoto" alt="">
            <div class="user-info">
                <p class="username ">{{ member.firstname }}</p>
            </div>
        </div>
        <p @click="follow" class="username" type="button">Follow</p>
    </div>
    </div>
    
</template>

<script setup>
    const props = defineProps({
        member:{}
    })

    const emit = defineEmits([
        "follow"
    ])

    const follow = () => {
        emit("follow", props.member)
    }

</script>

<style scoped>
    .card {
        margin: 3px;
        border: 1px lightgrey solid;
        width: 390px;
    }
    .card-header {
        display: flex;
        align-items: center;
    }

    .user-profile {
        display: flex;
        align-items: center;
    }

    .circleImg{
        border-radius: 50%;
        width:32px;
        min-height: 32px;
        object-fit: cover;
    }
    .user-info {
        margin-left: 8px;
    }
    .username {
        margin: 0;
        font-size: small;
        font-weight: 500;
        color:black;
        line-height:18px;
    }
</style>