import Vue from "vue";
import Router from "vue-router";
import store from './store'

Vue.use(Router);

const router =  new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path:'/',
      name:'index',
      component:() =>  import('./views/mainPage.vue'),
      redirect:'/index',
      children:[
        {
          path:'/index',
          name:'index',
          component:() =>  import('./components/index.vue'),
        },
        {
          path:'/addarticle',
          name:'addarticle',
          component:() =>  import('./components/addingArticles.vue'),
        },
        {
          path:'/myarticles',
          name:'myarticles',
          component:() =>  import('./components/myArticles.vue'),
        },
        {
          path:'/articledetail',
          name:'articledetail',
          component:() =>  import('./components/articleDetails.vue'),
        },
      ]
    },
    {
      path: "/login",
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
export default router;
