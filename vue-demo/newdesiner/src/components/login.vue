<template>
  <div class="login_container">
    <h3>登录</h3>
    <!-- 用户名 -->
    <Input prefix="ios-contact" v-model="username" type="text" placeholder="请输入名字" />
    <!-- 密码 -->
    <Input prefix="md-lock" v-model="password" type="password" placeholder="请输入密码" />
    <Button type="success" long @click="loginFun()">登录</Button>
    <p class="rigistorforget">
      <span @click="$router.push({path:'/forgetpwd'})">忘记密码</span>
      <span @click="$router.push({path:'/register'})">注册</span>
    </p>
  </div>
</template>
<script>
export default {
  data() {
    return {
      username: undefined,
      password: undefined
    };
  },
  methods: {
    loginFun() {
      if (!this.username) {
        this.errorAlert("请输入用户名");
        return;
      }
      if (!this.password) {
        this.errorAlert("请输入密码");
        return;
      }
      let dataobj = {
        username: this.username,
        userpassword: this.password,
      };
      this.$Reqpost("/user/login",dataobj).then(res=>{
        let result = res
        if(res.code == 200){
           this.successAlert('登录成功')
           setTimeout(()=>{
               this.$router.push({path:'/index'})
           },1000)
        }else{
            this.errorAlert('登录失败')
        }
      }).catch(err=>{
          console.log(err)
      });
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
  padding: 45px;
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
.rigistorforget span {
  cursor: pointer;
}
</style>
