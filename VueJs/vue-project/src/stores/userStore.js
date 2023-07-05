import { ref } from 'vue'
import { defineStore } from 'pinia'

export const userStore = defineStore('userStore', () => {
    
    const memberId = ref(0)
    const firstname = ref('')
    const memberPhoto = ref('')

    function setMemberId(id) {
        memberId.value = id
    } 
    function setFirstname(name) {
        firstname.value = name
    } 
    function setMemberPhoto(photo) {
        memberPhoto.value = photo
    }
    
    return { memberId, firstname, memberPhoto, setMemberId, setFirstname, setMemberPhoto }
}, {
    persist: true,
})