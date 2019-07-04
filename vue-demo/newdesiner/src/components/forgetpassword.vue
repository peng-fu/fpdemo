<template>
  <div class="login_container">
    <h3>忘记密码</h3>
    <!-- 用户名 -->
    <Input prefix="ios-contact" v-model="userphone" type="text" placeholder="请输入手机号" />
    <!-- 密码 -->
    <Input prefix="md-lock" v-model="password" type="password" placeholder="请输入密码" />
    <Button type="success" long @click="saveFun">确认</Button>
    <p class="rigistorforget">
      <span @click="$router.push({path:'/register'})">注册</span>
      <span @click="$router.push({path:'/login'})">登录</span>
    </p>
  </div>
</template>
<script>
export default {
  data() {
    return {
      userphone: undefined,
      password: undefined
    };
  },
  methods:{
    saveFun(){
      if (!this.userphone) {
        this.errorAlert("请输入手机号");
        return;
      }
      if (!this.password) {
        this.errorAlert("请输入密码");
        return;
      }
      let dataobj = {
        userphone:this.userphone,
        userpassword:this.password
      }
      console.log(dataobj)
      this.$Reqpost("/user/forgetpwd",dataobj).then(res=>{
        if(res.code == 200){
           this.successAlert('找回密码成功')
           setTimeout(()=>{
               this.$router.push({path:'/login'})
           },1000)
        }else{
            this.errorAlert('找回密码失败')
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
.rigistorforget span{
    cursor: pointer;
}