import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path:'/',
      name:'index',
      component:() =>  import('./views/index.vue'),
    },
    {
      path:'/index',
      name:'index',
      component:() =>  import('./views/index.vue'),
    },
    {
      path: "/userlogin",
      name: "logineMain",
      component:() => import('./views/loginMain.vue'),
      redirect:'/login',
      children:[
        {
          path:'/login',
          name:'login',
          component:() =>  import('./components/login.vue'),
        },
        {
          path:'/register',
          name:'register',
          component:() =>  import('./components/register.vue'),
        },
        {
          path:'/forgetpwd',
          name:'forgetpwd',
          component:() =>  import('./components/forgetpassword.vue'),
        },
      ]
    },
  ]
});
