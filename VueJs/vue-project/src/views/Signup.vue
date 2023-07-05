<template>
    
<div class="container">
    <div class="row g-2">
        <form @submit.prevent="regist" id="dataForm">
            <div class="name">
                <input name="lastname" v-model="lastname" class="form-control form-control-name" placeholder="姓氏" />
                <input name="firstname" v-model="firstname" class="form-control form-control-name" placeholder="名字" />
            </div>

            <div>
                <input name="email" v-model="email" class="form-control" placeholder="電子郵件地址" />
            </div>

            <div>
                <input name="password" v-model="password" type="password" class="form-control" placeholder="密碼" />
            </div>

            <div>
                <input name="file" type="file" class="form-control" />
            </div>
            <!-- <p>{{ image }}</p>
            <img :src="image" /> -->

            <div class="g-2">
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">註冊</button>
                </div>
            </div>
        </form>

    </div>
</div>
</template>

<script setup>
    const regist = (e) => {
        let formdata = new FormData(e.target)

		fetch ("/api/v1/auth/register", {
			method: "POST",
			body: formdata
		})
		.then (response => response.json())
		.then (result => {
			document.getElementById("dataForm").reset();
            window.location.href = "/login";
		})
}
</script>


<style scoped>
    .btn-primary {
  		width:10em;
  	}  
    .form-control {
            width: 400px;
            margin: auto;
    }
    .form-control-name {
        width: 196px;
        margin: 0;
        
    }
    .name {
        padding: 0;
        width: 400px;
        display: flex;
        margin: auto;
        justify-content: space-between;
    }
    .btn {
        margin: 10px;
    }
</style>