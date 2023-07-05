<template>
	<div class="container">
		<form @submit.prevent="login" id="dataForm">
			<div class="row login g-2">
				<div class="row login g-2">
					<input v-model="authenticationRequest.email" class="form-control" placeholder="電子郵件地址" />
				</div>
				<div class="text-center">
					<input v-model="authenticationRequest.password" type="password" class="form-control" placeholder="密碼" />
				</div>
				<div>{{  message }}</div>
				<div class="text-center">
					<button type="submit" class="btn btn-primary form-control">登入</button>
				</div>
				<div class="text-center">
					<div style="border-bottom: solid lightgray 1px; width: 400px; margin: auto;"></div>
				</div>
				<div class="g-2">
					<div class="text-center">
						<router-link class="btn btn-primary"  to="/signup">註冊</router-link>
					</div>
				</div>
			</div>
		</form>
	</div>
</template>


<script setup>
import { reactive, ref } from 'vue';

import { userStore } from '../stores/userStore'

const user = userStore()

const message = ref("")

const authenticationRequest = reactive({
	email: "",
	password: ""
})

const login = () => {
	
	fetch ("/api/v1/auth/authenticate", {
		method: "POST",
		headers: {
			'Content-Type': 'application/json;'
		},
		body: JSON.stringify(authenticationRequest)

	})
	.then (response => response.json())
	.then (result => {
		document.getElementById("dataForm").reset();
		if (result.token) {
			window.localStorage.clear();
			window.localStorage.setItem("jwtToken", result.token);
			user.setMemberId(result.memberId)
			user.setFirstname(result.firstname)
			user.setMemberPhoto(result.memberPhoto)
			console.log(user.firstname)
			console.log(user.memberId)
			window.location.href = "/myfollowing";
		}
	})
	.catch(err => {
		console.log("登入失敗")
		const status = err.name
		if (status == 403) {
			message.value = "登入失敗"
		}
	})

	
}
</script>

<style scoped>
	.signin-btn {
		margin: 10px;
	}
	.btn-primary {
		width:10em;
	}
	
	.form-control {
		width: 400px;
		margin: auto;
	}
	
	.btn {
		margin: 10px;
	} 
</style>