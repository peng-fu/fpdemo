<template>
  <div class="index_page">
    <Sider breakpoint="md">
      <Menu active-name="1-1" width="auto">
        <MenuItem
          :name="`1-${index+1}`"
          v-for="(leftitem ,index) in indexcategorydata"
          :key="index"
          @click.native="checkClassify(leftitem.name,index)"
        >
          <span>{{leftitem.name}}</span>
        </MenuItem>
      </Menu>
      <div slot="trigger"></div>
    </Sider>
    <!-- 右边主要内容 -->
    <div class="right_maindiv">
      <Carousel autoplay v-if="currentclassify === 0">
        <Carousel-item>
          <div class="demo-carousel">
            <img src="@/assets/image/slide2.jpg" alt />
          </div>
        </Carousel-item>
        <Carousel-item>
          <div class="demo-carousel">
            <img src="@/assets/image/slide3.jpg" alt />
          </div>
        </Carousel-item>
        <Carousel-item>
          <div class="demo-carousel">
            <img src="@/assets/image/slide4.jpg" alt />
          </div>
        </Carousel-item>
      </Carousel>
      <div class="rigth_content">
        <div class="index_contentitem" v-for="(dataitem,index) in indexAllData" :key="index"  @click="jumpDetail(dataitem.blogid)">
          <p class="contentitemtitle">{{dataitem.blogtitle}}</p>
          <p class="contentitemconent">{{dataitem.blogsynopsis}}</p>
          <div class="contentitemauhermsg">
            <div class="authermassageleft">
              <img src="@/assets/image/img_sys19.png" alt />
              <span class="authername">{{dataitem.username}}</span>
              <span class="leftbottm">|</span>
              <span>{{dataitem.blogcategory}}</span>
              <span class="leftbottm">|</span>
              <span>{{dataitem.releasetime | formateTime}}</span>
            </div>
            <div class="authermassageright">
              <span>阅读量</span>
              <span class="numberspan">{{dataitem.viewquantity}}</span>
              <span class="leftbottm">|</span>
              <span>评论量</span>
              <span class="numberspan">{{dataitem.commentquantity}}</span>
            </div>
          </div>
        </div>

        <div id="indexfootpage">
          <Page :total="indexParams.totaldata" :page-size="indexParams.pagesize"  show-total @on-change="pagination"></Page>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      leftclassifylist: [
        { name: "全部", key: 0 },
        { name: "Javascript", key: 1 },
        { name: "Java", key: 2 },
        { name: "C++", key: 3 },
        { name: "C#", key: 4 },
        { name: "Python", key: 5 },
        { name: "PHP", key: 6 },
        { name: "区块链", key: 7 }
      ], //左边分类
      currentclassify: 0,
      indexParams:{
        totaldata:0,
        currentpage:1,
        pagesize:10,
        blogcategory:null,
      },
      indexAllData:[],
      indexcategorydata:[],
    };
  },
  methods: {
    //点击分类
    checkClassify(typename,index){
      if(typename == '全部'){
        this.indexParams.blogcategory = null
      }else{
        this.indexParams.blogcategory = typename
      }
      this.pagination(1)
    },
    //获取总数据
    getIndexBlogdata(){
      let dataobj = {
        page:this.indexParams.currentpage,
        size:this.indexParams.pagesize,
        blogcategory:this.indexParams.blogcategory
      }
      this.$Reqpost('/blog/getblogdata',dataobj).then(res=>{
        console.log(res)
        if(res.code == 200){
          this.indexAllData = res.data.datas
          this.indexParams.totaldata = res.data.totalSize
        }
      }).catch(err=>{console.log(err)})
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
          this.indexcategorydata.push(firstobj)
          for(let i =0;i<dataArr.length;i++){
            let obj = {}
            obj.name = dataArr[i]
            obj.key = i+1
            this.indexcategorydata.push(obj)
          }
        }
      }).catch(err=>{
          console.log(err)
      })
    },
    //分页
    pagination(page){
      this.indexParams.currentpage = page
      this.getIndexBlogdata()
    }
  },
  created(){
    this.getIndexBlogdata()
    this.getCategory()
  }
};
</script>
<style>
.index_page {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: space-between;
}
.left_classify {
  width: 120px;
  padding: 10px 0;
  background: white;
}
.left_classify ul li {
}
.right_maindiv {
  width: 980px;
  min-height: 869px;
}
.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu) {
  color: #f7f7f7;
  background: #009a61;
  z-index: 2;
}
.ivu-carousel-list{
    margin-bottom: 20px;
    box-shadow: 2px 4px 10px 0 rgba(0,0,0,.16);
}
.ivu-carousel-item img {
  width: 100%;
}
.ivu-carousel-dots li button {
  background: #009a61;
}
.ivu-layout-sider-children{
    box-shadow: 2px 4px 10px 0 rgba(0,0,0,.16);
}
/* 列表内容  */
.rigth_content {
  background: white;
  min-height: 433px;
  border-radius: 3px;
  box-shadow: 2px 4px 10px 0 rgba(0,0,0,.16);
  padding-bottom: 20px;
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
}
.authermassageleft {
  display: flex;
  align-items: center;
  min-width: 250px;
  justify-content: space-between;
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
#indexfootpage {
  text-align: center;
  width: 100%;
  margin: 30px 0 0px 0;
}
</style>
