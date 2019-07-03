<template>
  <div class="login_container">
    <h3>注册</h3>
    <!-- 用户名 -->
    <Input prefix="ios-contact" v-model="username" type="text" placeholder="请输入名字" />
    <!-- 密码 -->
    <Input prefix="md-lock" v-model="password" type="password" placeholder="请输入密码" />
    <!-- 确认密码 -->
    <Input prefix="md-lock" v-model="confirmpassd" type="password" placeholder="请确认密码" />
    <!-- 手机号 -->
    <Input prefix="md-lock" v-model="userphone" type="text" placeholder="请输入手机号" />
    <!-- 邮箱 -->
    <Input prefix="md-lock" v-model="useremail" type="text" placeholder="请输入邮箱" />
    <Button type="success" long @click="registerFun()">注册</Button>
    <p class="rigistorforget">
      <span @click="$router.push({path:'/forgetpwd'})">忘记密码</span>
      <span @click="$router.push({path:'/login'})">登录</span>
    </p>
  </div>
</template>
<script>
export default {
  data() {
    return {
      username: undefined,
      password: undefined,
      confirmpassd:undefined,
      userphone:undefined,
      useremail:undefined
    };
  },
  methods:{
      registerFun(){
        if(!this.username){
          this.errorAlert('请输入用户名') 
          return;
        }
        if(!this.password){
          this.errorAlert('请输入密码') 
          return;
        }
        if(!this.confirmpassd){
          this.errorAlert('请输入二次密码') 
          return;
        }
        if(this.password !== this.confirmpassd){
          this.errorAlert('密码不一致') 
          return;
        }
        if(!this.userphone){
          this.errorAlert('请输入用户手机号') 
          return;
        }
        if(!this.useremail){
          this.errorAlert('请输入用户邮箱') 
          return;
        }
        this.$axios({
            method:'post',
            url:'http://192.168.86.234:8080/user/register',
            params:{
                username:this.username,
                userpassword:this.password,
                userphone:this.userphone,
                useremail:this.useremail
            }
        }).then(res=>{
            console.log(res)
        }).catch(err=>{
            console.log(err)
        })
      }
  }
};
</script>
<style>
.login_container {
  width: 400px;
  height: 400px;
  border-radius: 6px;
  background-color: #fff;
  padding: 30px 45px;
  position: absolute;
  top: 30%;
  box-shadow: 0px 0px 10px white;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.rigistorforget {
  line-height: 30px;
  font-size: 15px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-weight: 600;
  color: #2d8cf0;
}
.rigistorforget span{
    cursor: pointer;
}
</style>
