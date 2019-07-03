<template>
  <div class="login_container">
    <h3>登录</h3>
    <!-- 用户名 -->
    <Input prefix="ios-contact" v-model="username" type="text" placeholder="请输入名字" />
    <!-- 密码 -->
    <Input prefix="md-lock" v-model="password" type="password" placeholder="请输入密码" />
    <Button type="success" long >登录</Button>
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
</style>
