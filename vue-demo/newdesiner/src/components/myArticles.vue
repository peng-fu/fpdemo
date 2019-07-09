<template>
  <div class="myArticlesmain">
    <Sider breakpoint="md">
      <Menu active-name="1-1" width="auto">
        <MenuItem
          :name="`1-${index+1}`"
          v-for="(leftitem ,index) in categorydata"
          :key="index"
          @click.native="checkClassify(leftitem.name)"
        >
          <span>{{leftitem.name}}</span>
        </MenuItem>
      </Menu>
      <div slot="trigger"></div>
    </Sider>
    <div class="myarticle_content">
      <div class="index_contentitem" v-for="(items , index) in myarticles" :key="index" @click="jumpDetail(items.blogid)">
        <p class="contentitemtitle">{{items.blogtitle}}</p>
        <p class="contentitemconent">{{items.blogsynopsis}}</p>
        <div class="contentitemauhermsg">
          <div class="authermassageleft">
            <img src="@/assets/image/img_sys19.png" alt />
            <span class="authername">{{items.username}}</span>
            <span class="leftbottm ">|</span>
            <span>{{items.blogcategory}}</span>
            <span class="leftbottm">|</span>
            <span>{{items.releasetime | formateTime}}</span>
          </div>
          <div class="authermassageright">
            <span>阅读量</span>
            <span class="numberspan">{{items.viewquantity}}</span>
            <span class="leftbottm">|</span>
            <span>评论量</span>
            <span class="numberspan">{{items.commentquantity}}</span>
          </div>
        </div>
      </div>
      <div id="footpage">
          <Page :total="myarticleparams.totaldata" :page-size="myarticleparams.pagesize"  show-total @on-change="pagination"></Page>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      myarticleparams:{
        totaldata:0,//总数
        currentPage:1,//当前页
        pagesize:5,//每页显示的数据
        blogcategory:null,//文章类型
      },
      myarticles:[],//我的文章
      categorydata:[],
    };
  },
  methods: {
    //点击分类
    checkClassify(typename){
      if(typename == '全部'){
        this.myarticleparams.blogcategory = null
      }else{
        this.myarticleparams.blogcategory = typename
      }
      this.pagination(1)
    },
    //获取数据
    getMyArticleData(){
      let dataobj = {
        page:this.myarticleparams.currentPage,
        size:this.myarticleparams.pagesize,
        blogcategory:this.myarticleparams.blogcategory
      }
      this.$Reqpost('/blog/getmyblogdata',dataobj).then(res=>{
        console.log(res)
            if(res.code == 200){
              this.myarticles = res.data.datas
              this.myarticleparams.totaldata = res.data.totalSize
            }
        }).catch(err=>{
            console.log(err)
      })
    },
    //跳转页面
    jumpDetail(id){
      let dataobj = {
        blogid:id
      }
      this.$Reqpost('/blog/addviewquantity',dataobj).then(res=>{
        if(res.code == 200){
          localStorage.setItem('acticleid',id)
          this.$router.push({path:'/articledetail'})
        }
        }).catch(err=>{
          console.log(err)
      })
    },
    //获取分类
    getCategory(){
      this.$Reqpost('/blog/getblogtype').then(res=>{
        if(res.code == 200){
          let dataArr = res.data
          let firstobj = {}
          firstobj.name = '全部'
          firstobj.key = 0
          this.categorydata.push(firstobj)
          for(let i =0;i<dataArr.length;i++){
            let obj = {}
            obj.name = dataArr[i]
            obj.key = i+1
            this.categorydata.push(obj)
          }
        }
      }).catch(err=>{
          console.log(err)
      })
    },
    //分页
    pagination(page){
      this.myarticleparams.currentPage = page
      this.getMyArticleData()
    },
  },
  created(){
    this.getMyArticleData()
    this.getCategory()
  }
};
</script>
<style lang='less'>
.myArticlesmain {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}
.myarticle_content {
  min-height: 829px;
  width: 980px;
  background-color: white;
  border-radius: 3px;
  box-shadow: 2px 4px 10px 0 rgba(0, 0, 0, 0.16);
  position: relative;
}
.index_contentitem {
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  border-bottom: 1px solid #e3e5e8;
  cursor: pointer;
}
.contentitemtitle {
  font-size: 20px;
  color: black;
  margin-bottom: 10px;
}
.contentitemconent {
  font-size: 15px;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: horizontal;
  margin-bottom: 10px;
}
.contentitemauhermsg {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  width: 100%;
  color: #999;
}
.contentitemauhermsg img {
  width: 24px;
  margin-right: 8px;
}
.authermassageleft {
  display: flex;
  align-items: center;
  min-width: 250px;
  justify-content: space-between;
  .leftbottm{
    margin: 0 8px;
  }
}
.authername {
  color: black;
}
.leftbottm {
  font-size: 12px;
}
.authermassageright {
  min-width: 200px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.numberspan {
  color: #009a61;
}
#footpage {
  text-align: center;
  position: absolute;
  width: 100%;
  bottom: 10px;
}
.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu) {
    color: #f7f7f7;
    background: #009a61;
    z-index: 2;
}
</style>
