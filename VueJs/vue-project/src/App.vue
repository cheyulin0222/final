<script setup>
import { RouterView } from 'vue-router'
import NavBar from "./components/NavBar.vue";
</script>

<template>
  <header>
    <NavBar></NavBar>
  </header>
  <main>
    <RouterView />
  </main>
</template>

<style scoped>
main {
  flex-grow: 1;
  margin-top: 75px;
}

header {
  margin-bottom: 75px;
}
</style>
