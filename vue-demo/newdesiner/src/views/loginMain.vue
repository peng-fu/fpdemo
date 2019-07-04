<template>
  <div class="login-div">
    <div class="container">
      <transition name="fade">
        <router-view></router-view>
      </transition>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
    };
  },
};
</script>
<style>
.login-div {
  width: 100%;
  height: 100%;
  background: url("../assets/image/bg_login.jpg") no-repeat center;
}
.container {
  margin: 0 auto;
  text-align: center;
  overflow: hidden;
  padding: 0;
  width: 400px;
  position: relative;
  height: 100%;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>
