import Vue from "vue";
import Vuex from "vuex";
import { stat } from "fs";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userinfo:{
      username:undefined,
      userphone:undefined,
      useremail:undefined,
      times:undefined,
    },
  },
  mutations: {
    SaveUserinfo(state,userinfo){
      state.userinfo.username = userinfo.username
      state.userinfo.userphone = userinfo.userphone
      state.userinfo.useremail = userinfo.useremail
      state.userinfo.times = new Date().getTime()
    },
    ClearUserinfo(state){
      state.userinfo.username = undefined
      state.userinfo.userphone = undefined
      state.userinfo.useremail = undefined
      state.userinfo.times = undefined
    }
  },
  actions: {
    reqSaveUserinfo(context,userinfo){
      context.commit('SaveUserinfo',userinfo)
    },
    reqClearUserinfo(context){
      context.commit('ClearUserinfo')
    }
  }
});
