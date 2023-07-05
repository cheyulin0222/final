<template>
  <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <router-link to="/" class="navbar-brand">Fakebook</router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav ms-auto align-items-center">
          <router-link to="/allpost" class="nav-link active" aria-current="page">全部貼文</router-link>
          
          <router-link to="/myfollowing" class="nav-link active" aria-current="page">我的追蹤</router-link>
          <div class="nav-link active" type="button" aria-current="page" data-bs-toggle="modal" data-bs-target="#addPostModal">新增貼文</div>
          
          <router-link to="/allmember" class="nav-link active" aria-current="page">全部成員</router-link>
          
          <router-link to="/test" class="nav-link active" aria-current="page">Test</router-link>
          <router-link to="/mypost" class="nav-link active" aria-current="page">我的貼文</router-link>

          <div v-if="isAuthorizated()" class="nav-link active aaaa" type="button" aria-current="page" data-bs-toggle="modal" data-bs-target="#logoutModal">
            <img class="circleImg" :src="'data:image/jpeg;base64,'+ user.memberPhoto" alt="">
            登出
          </div>
          <router-link to="/login" v-if="!isAuthorizated()" class="nav-link active" aria-current="page">登入</router-link>
        </div>
      </div>
    </div>
  </nav>

  

  <AddPostModal></AddPostModal>    
  <LogoutModal @logout="logout"></LogoutModal>

</template>

<script setup>
  import AddPostModal from './AddPostModal.vue';
  import LogoutModal from './LogoutModal.vue';

  import { userStore } from '../stores/userStore'

  const user = userStore()

  function isAuthorizated () {
    let USER = localStorage.getItem("jwtToken");
    return !!USER;
  }

  const logout = () => {
    localStorage.removeItem("jwtToken")
    localStorage.removeItem("userStore")
    window.location.href = "/login";
  }

</script>

<style scoped>
.circleImg{
            border-radius: 50%;
            width:32px;
            min-height: 32px;
            object-fit: cover;
        }
</style>
