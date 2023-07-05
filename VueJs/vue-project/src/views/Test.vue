<template>
    <div class="card border-0">
        
        <!-- Header -->
        <div class="card-header justify-content-between  align-items-center">
            <div class="user-profile  ">
                <img class="circleImg" src="../assets/test/0001.jpg" alt="">
                <div class="user-info">
                    <p class="username">Jeremy</p>
                    <p class="location">Paris</p>
                </div>
            </div>
            <img src="../assets/img/icon/more.png" class="icon">
        </div>
        <div class="post-file-container">
            <img class="single-post-photo" src="../assets/img/test/dog10.jpeg" alt="">
        </div>
        
    </div>
    

    
    
</template>

<style scoped>
    .card {
        width: 390px;
    }

    .card-header {
        padding-left: 0;
        padding-right: 0;
        background-color: white;
        border: 0;
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

    .icon {
        width: 25px;
    }

    .username {
        margin: 0;
        font-size: small;
        font-weight: 500;
        color:black;
        line-height:18px;
    }
    .location {
        margin: 0;
        font-size:smaller;
        font-weight: 400;
        color: grey;
        padding: 0;
        line-height:18px;
    }
    .post-file-container {
        width: 390px;
        height: 390px;
    }

    .single-post-photo {
            width:390px;
            min-height: 390px;
            object-fit: cover;
        }


</style>