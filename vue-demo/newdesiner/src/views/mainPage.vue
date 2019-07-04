<template>
  <div class="mainPagecontainer">
    <div class="topnavbar">
      <div class="tabcontent">
        <div class="tabbaer_leftdiv">
          <img src="@/assets/image/LOgo.png" alt />
          <!-- <ul class="topul">
            <li
              :class="tabercurrent == index?'tabercurrentstyle':''"
              v-for="(taberitem ,index) in tabbarList"
              :key="index"
              @click="chencktaber(index,taberitem.path)"
            >{{taberitem.name}}</li>
          </ul>-->
          <Menu mode="horizontal"  active-name="1">
            <MenuItem :name="`${index+1}`" v-for="(taberitem ,index) in tabbarList" :key="index" @click.native="chencktaber(index,taberitem.path)">
              {{taberitem.name}}
            </MenuItem>
          </Menu>
        </div>
        <div class="login_registdiv">
          <p>
            <span>登录</span>
            <span>注册</span>
          </p>
        </div>
      </div>
    </div>
    <div class="mainContein">
      <div class="maindiv">
        <router-view></router-view>
      </div>
    </div>
    <div class="footerdiv">
      <div class="footer_maincontent">
        <p>
          <span>Copyright 2019.01.16 by 大BUG</span>
          <span>备案号：蜀ICP备16031990号-2</span>
        </p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tabbarList: [
        { name: "首页", path: "/index" },
        { name: "添加文章", path: "/addarticle" },
        { name: "我的文章", path: "/myarticles" },
        { name: "技术文章", path: "/index" },
        { name: "程序员人生", path: "/index" },
        { name: "关于博客", path: "/index" }
      ], //头部导航列表
      tabercurrent: -1 //当前被选中的tab选项
    };
  },
  methods: {
    chencktaber(index, pagepath) {
      this.tabercurrent = index;
      this.$router.push({ path: pagepath });
    }
  }
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
.mainPagecontainer {
  background-color: #f5f6f7;
}
.topnavbar {
  width: 100%;
  height: 50px;
  background-color: #1c2327;
}
.tabcontent {
  width: 1200px;
  margin: auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  line-height: 50px;
}
.tabbaer_leftdiv {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.tabbaer_leftdiv .ivu-menu-light{
   background: none;
   margin-left: 50px;
}
.tabbaer_leftdiv .ivu-menu-horizontal{
   height: 52px;
   line-height: 52px;
   color: white;
}
.tabbaer_leftdiv .ivu-menu-light.ivu-menu-horizontal .ivu-menu-item-active{
   color: white;
   border-bottom: 2px solid #2d8cf0;
}
.tabbaer_leftdiv .ivu-menu-light.ivu-menu-horizontal .ivu-menu-item, .ivu-menu-light.ivu-menu-horizontal .ivu-menu-submenu{color: white;}
.tabcontent img {
  width: 66px;
  height: 27px;
}
.tabcontent .topul {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-left: 50px;
}
.topul li {
  font-size: 16px;
  color: #19be6b;
  padding: 0 25px;
  cursor: pointer;
}
.tabercurrentstyle {
  background: #19be6b;
  color: white !important;
}
.topul li:hover {
  background: #19be6b;
  color: white;
}
.login_registdiv {
  font-size: 14px;
  color: white;
}
.login_registdiv p span {
  cursor: pointer;
}
.login_registdiv p span:last-child {
  margin-left: 30px;
}

/*  主要内容 */
.mainContein {
  min-height: 869px;
  margin: 20px 0;
}
.maindiv {
  width: 1200px;
  margin: auto;
}
/* 底部 */
.footerdiv {
  width: 100%;
  height: 50px;
  background-color: #1c2327;
  line-height: 50px;
}

.footer_maincontent {
  width: 1200px;
  margin: auto;
}
.footer_maincontent p {
  font-size: 16px;
  color: white;
}
.footer_maincontent p span:first-child {
  margin-right: 50px;
}
</style>
