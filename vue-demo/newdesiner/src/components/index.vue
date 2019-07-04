<template>
  <div class="index_page">
    <!-- 分类 -->
    <!-- <div class="left_classify">
            <ul>
                <li v-for="(leftitem , index) in leftclassifylist" :key="index">{{leftitem.name}}</li>
            </ul>
    </div>-->
    <Sider breakpoint="md">
      <Menu active-name="1-1" width="auto">
        <MenuItem
          :name="`1-${index+1}`"
          v-for="(leftitem ,index) in leftclassifylist"
          :key="index"
          @click.native="checkClassify(index)"
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
            <img src="@/assets/image/slide1.jpg" alt />
          </div>
        </Carousel-item>
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
        <div class="index_contentitem">
          <p class="contentitemtitle">如何学习JavaScript？</p>
          <p class="contentitemconent">
            第一阶段：初识数据分析这个阶段是你学习数据分析的第一个月。核心的三本书就是：统计学、R IN ACTION、深入浅出数据分析。
            第一星期：好好的阅读一下统计学这本教材。按照每天3个小时的时间，一个星期你至少能看完8章。踏踏实实的看完，课后习题不需要做，重点放在理解公式推导以及专业名字定义的理解上。
            第二星期：有了统计学基础，R语言学习起来就不会太费劲。《R in action》 是公认的R语言经...
          </p>
          <div class="contentitemauhermsg">
            <div class="authermassageleft">
              <img src="@/assets/image/img_sys19.png" alt />
              <span class="authername">Nurdun</span>
              <span class="leftbottm">|</span>
              <span>Javascript</span>
              <span class="leftbottm">|</span>
              <span>6月10日</span>
            </div>
            <div class="authermassageright">
              <span>阅读量</span>
              <span class="numberspan">4000</span>
              <span class="leftbottm">|</span>
              <span>评论量</span>
              <span class="numberspan">555</span>
            </div>
          </div>
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
      currentclassify: 0
    };
  },
  methods: {
    checkClassify(index) {
      this.currentclassify = index;
    }
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
  background: #2d8cf0;
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
  background: #409eff;
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
}
.index_contentitem {
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  border-bottom: 1px solid #e3e5e8;
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
  color: #2d8cf0;
}
</style>
