<template>
    <MemberList v-for="member in members" :member="member" @follow="addFollow" />
</template>

<script setup>

    import { onMounted, reactive } from 'vue';
    import MemberList from '../components/MemberList.vue'

    const members = reactive([])

    const follow = {
        following:{}
    }

    const addFollow = (member) => {
        let token = localStorage.getItem('jwtToken')
        follow.following = member

        fetch('/api/follow/post', {
            method: 'POST',
            headers: {
                Authorization: "Bearer " + token,
                'Content-Type': 'application/json;'
            },
            body: JSON.stringify(follow)
        })
        .then (response => response.json())
        .then (result => {
            members.length = 0
            result.forEach(element => {
                members.push(element)
            })
        })
    }
    

    const getAllMember = () => {
        let token = localStorage.getItem('jwtToken')
        
        fetch('/api/member/getWithoutFollowing', {
            method: 'GET',
            headers: {
                Authorization: "Bearer " + token,
            },
        })
        .then (response => response.json())
        .then (result => {
            members.length = 0
            result.forEach(element => {
                members.push(element)
            })
        })
    }

    onMounted(() => {
        getAllMember()
    })
</script>

<style scoped>
</style>