<template>
  <div class="mainPagecontainer">
    <div class="topnavbar">
      <div class="tabcontent">
        <div class="tabbaer_leftdiv">
          <img src="@/assets/image/LOgo.png" alt />
          <Menu mode="horizontal"  :active-name="avticeindex">
            <MenuItem name="1" @click.native="chencktaber('/index','1')">首页</MenuItem>
            <MenuItem v-show="$store.state.userinfo.username" name="2" @click.native="chencktaber('/addarticle','2')">添加文章</MenuItem>
            <MenuItem v-show="$store.state.userinfo.username" name="3" @click.native="chencktaber('/myarticles','3')">我的文章</MenuItem>
            <MenuItem name="4">技术文章</MenuItem>
            <MenuItem name="5">程序员人生</MenuItem>
            <MenuItem name="6">关于博客</MenuItem>
          </Menu>
        </div>
        <div class="login_registdiv">
          <p v-if="!$store.state.userinfo.username">
            <span @click="$router.push({path:'/login'})">登录</span>
            <span @click="$router.push({path:'/register'})">注册</span>
          </p>
          <Dropdown v-else>
              <a href="javascript:void(0)">
                  {{$store.state.userinfo.username}}
                  <Icon type="ios-arrow-down"></Icon>
              </a>
              <DropdownMenu slot="list">
                  <!-- <DropdownItem>{{$store.state.userinfo.userphone}}</DropdownItem>
                  <DropdownItem>{{$store.state.userinfo.useremail}}</DropdownItem> -->
                  <DropdownItem @click.native="Logout()">退出登录</DropdownItem>
              </DropdownMenu>
          </Dropdown>
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
import { setTimeout } from 'timers';
export default {
  data() {
    return {
      currentopsion:this.$route.path,
    };
  },
  methods: {
    chencktaber(pagepath,index) {
      this.currentopsion = pagepath
      this.$router.push({ path: pagepath });
      if(pagepath == '/index' || pagepath == '/myarticles'){
        sessionStorage.setItem('path',pagepath)
      }
    },
    //退出登录
    Logout(){
      this.$Reqpost('/user/signoutlogin').then(res=>{
        if(res.code == 200){
          this.successAlert('退出成功')
          this.$store.dispatch('reqClearUserinfo')
            this.$router.push({path:'/index'})
          // setTimeout(()=>{
            
          // },1500)
        }
      }).catch(err=>{console.log(err)})
    },
    //获取用户信息
    getUserinformation(){
      if(!this.$store.state.userinfo.username){
        this.$Reqpost('/user/getuserinfo').then(res=>{
          console.log(res)
          if(res.code == 200){
            let resData = {}
            resData = res.data
            let userinfo = {
              username:resData.username,
              userphone:resData.userphone,
              useremail:resData.useremail,
              times:new Date().getTime()
            }
            this.$store.dispatch('reqSaveUserinfo',resData)
          }
        }).catch(err=>{
          console.log(err)
        })
      }
    }
  },
  computed:{
    avticeindex(){
      if(this.currentopsion == '/index'){
       return '1' 
      }else if(this.currentopsion == '/addarticle'){
        return '2'
      }else if(this.currentopsion == '/myarticles'){
        return '3'
      }else{
        let pathstr = sessionStorage.getItem('path')
        if(pathstr == '/index'){
          return '1'
        }else if(pathstr == '/myarticles'){
          return '3'
        }
      }
    }
  },
  created(){
    this.getUserinformation()
    console.log(this.currentopsion)
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
   border-bottom: 2px solid #009a61;
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
.login_registdiv .ivu-dropdown-rel a{
  color:white;
  font-size: 15px;
}
/*  主要内容 */
.mainContein {
  min-height: 829px;
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
