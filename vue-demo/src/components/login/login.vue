<template>
    <div class="main">
        <transition name="fade">
            <div class="loginDiv " v-show="show">
                <div class="un " style="animated-detay">
                    <p class="fl tx"> 用户名:</p>
                    <i-input placeholder="请输入..." style="width: 300px"></i-input>
                </div>
                <div class="pw ">
                   <p class="fl tx"> 密码:</p>
                    <input type="password" />
                </div>
                <div>
                    <router-link class="fr" to="/regist">注册</router-link>
                    <router-link class="fl" to="/forget">忘记密码</router-link>
                </div>
                <Button class="info">登录</Button>
            </div>
        </transition>
    </div>
</template>

<script>
export default {
    data(){
        return{
            show:false,
        }
    },

    mounted(){
        this.show = true;
    },
    methods:{

    }
}
</script>

<style>

.fade-enter-active, .fade-leave-active {
  transition: opacity 5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

.main{
    background-color: aquamarine;
    width: 100%;
    height: 100%;
    padding-top: 25vh;
}
.loginDiv{
    width: 600px;
    height: 500px;
    background-color: white;
    border-radius:5px;
    box-shadow: 1px 2px 2px slategrey;
    border: none; 
    margin: 0 auto;
    padding:30px 80px; 
    font-size: 24px
}
.un{
    margin: 20px 0;
}
.pw{
   margin: 20px 0; 
}
.un input, .pw input{
    border: none;
    border-bottom: 1px solid #cccccc;
}
.tx{
    width: 200px;
    text-align: right
}

</style>
