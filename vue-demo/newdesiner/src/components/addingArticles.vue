<template>
    <div class="addarticle_main">
        <div class="addarticle_left">
            <div class="articletitle mainstyle">
                <label>标题:</label>
                <Input v-model="addtitleParams.artciletitle" placeholder="请输入标题"/>
            </div>
            <div class="articleclassify mainstyle">
                <label>分类:</label>
                 <Cascader :data="goodsarealist" placeholder="请选择分类" v-model="addtitleParams.artcileClassify"></Cascader>
            </div>
            <div class="articlebrief mainstyle">
                <label>简介:</label>
                <Input  v-model="addtitleParams.artcilebrief" type="textarea" :autosize="{minRows: 2,maxRows: 2,maxlength:100}" placeholder="请输入文章简介" />
            </div>
            <div class="articlecontent mainstyle">
                <label>内容:</label>
                <quill-editor
                    ref="myQuillEditor"
                    :options="editorOption"
                    @blur="onEditorBlur($event)"
                    @focus="onEditorFocus($event)"
                    @change="onEditorChange($event)"
                    ></quill-editor>
            </div>
            <div class="articlebtn">
                <Button size="large" type="primary" @click="addArticle()">添加文章</Button>
            </div>
        </div>
        <div class="addarticle_right">
            <div class="addarticlerht_top">
                <img src="@/assets/image/headimgbg.jpg" alt="">
            </div>
            <div class="addarticlerht_headimg">
                <img src="@/assets/image/headimg.jpg" alt="">
            </div>
            <div class="addarticlerht_username">
                <span>{{$store.state.userinfo.username}}</span>
                <img src="@/assets/image/man.png" alt="">
            </div>
            <div class="addarticle_right_main">
                <ul class="addarticlerht_usermsg">
                    <li>
                        <span>手机号:</span>
                        <span>{{$store.state.userinfo.userphone}}</span>
                    </li>
                    <li>
                        <span>邮箱:</span>
                        <span>{{$store.state.userinfo.useremail}}</span>
                    </li>
                </ul>
                <div class="addarticlerht_bottom">
                    <p>
                        <span>600</span>
                        <span>评论</span>
                    </p>
                    <p>
                        <span>600</span>
                        <span>访问量</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import Vue from 'vue'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
Vue.use(VueQuillEditor)
export default {
   data() {
      return {
        goodsarealist:[
            {value:'Javascript',label:'Javascript'},
            {value:'Java',label:'Java'},
            {value:'C++',label:'C++'},
            {value:'C#',label:'C#'},
            {value:'Python',label:'Python'},
            {value:'PHP',label:'PHP'},
            {value:'区块链',label:'区块链'},
        ],
        addtitleParams:{
            artciletitle:undefined,
            artcileClassify:undefined,
            artcilebrief :undefined,
            artcilecontent :undefined,
        },
        editorOption: {
          theme:'snow'
       },
      }
   },
   methods:{
    onEditorBlur(el) {
      // console.log(el); // 富文本获得焦点时的内容
    }, // 失去焦点事件
    onEditorFocus() {}, // 获得焦点事件
    onEditorChange(el) {
        //this.addtitleParams.artcilecontent = el.text
    }, // 内容改变事件
    //添加文章
    addArticle(){
        if(!this.addtitleParams.artciletitle){
            this.errorAlert('请输入标题')
            return
        }
        if(!this.addtitleParams.artcileClassify){
            this.errorAlert('请选择分类')
            return
        }
        if(!this.addtitleParams.artcilebrief){
            this.errorAlert('请输入简介')
            return
        }
        let refstr = this.$refs.myQuillEditor.$el.innerText
        this.addtitleParams.artcilecontent = refstr
        if(!this.addtitleParams.artcilecontent.trim()){
            this.errorAlert('请输入内容')
            return
        }
        let dataobj = {
            blogtitle:this.addtitleParams.artciletitle,
            blogcategory:this.addtitleParams.artcileClassify,
            blogsynopsis:this.addtitleParams.artcilebrief,
            blogcontent:this.addtitleParams.artcilecontent
        }
        console.log(dataobj)
        this.$Reqpost('/blog/addblog',dataobj).then(res=>{
            console.log(res)
            if(res.code == 200){
                this.successAlert('添加文章成功')
                setTimeout(()=>{
                    this.$router.push({path:'/myarticles'})
                },1200)
            }else{
                this.errorAlert('添加文章失败')
            }
        }).catch(err=>{
            console.log(err)
        })
    }
   },
   computed: {
    editor() {
      return this.$refs.myQuillEditor.quill;
    },
  },
}
</script>
<style>
.addarticle_main{
    min-height: 829px;
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
}
.addarticle_left{
    width:870px;
    min-height: 829px;
    background: white;
    border-radius: 3px;
    box-shadow: 2px 4px 10px 0 rgba(0,0,0,.16);
    padding: 25px;
}
.addarticle_right{
    width: 300px;
    height: 500px;
    background: white;
    border-radius: 3px;
    box-shadow: 2px 4px 10px 0 rgba(0,0,0,.16);
    position: relative;
}
/* 右部 */
.addarticlerht_top img{
    width: 100%;
    height: 157px;
    border-radius: 3px 3px 0 0;
}
.addarticlerht_headimg{
    border: 4px solid #ffffff;
    width: 80px;
    height: 80px;
    overflow: hidden;
    border-radius: 50%;
    margin: 0px auto;
    background-color: #d4d4d4;
    box-shadow: 0px 0px 6px 1px #424242;
    position: relative;
    top: -50px;
}
.addarticlerht_headimg img{
    width: 80px;
}
.addarticlerht_username{
    width: 78%;
    margin: auto;
    display: flex;
    align-items: center;
    justify-content: center;
    border-bottom: 1px solid #dcdee2;
    padding-bottom: 10px;
    font-size: 16px;
    color: black;
    font-weight: bold;
}
.addarticlerht_username img{
    transform: rotate(-180deg)
}
.addarticle_right_main{
    margin-top: 15px;
    height: 40%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: flex-start;
}
.addarticlerht_usermsg{
    width: 100%;
    padding: 0 25px;
}
.addarticlerht_usermsg li{
    font-size: 14px;
    color: black;
    padding: 10px 0;
}
.addarticlerht_usermsg li span:first-child{
    display: inline-block;
    width: 50px !important;
    font-size: 14px;
    margin-right: 10px;
}
.addarticlerht_bottom{
    width: 100%;
    height: 55px;
    background: black;
    border-radius: 0 0 3px 3px;
    font-size: 15px;
    color: white;
    font-weight: bold;
    padding: 0 25px;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.addarticlerht_bottom p{
    display: flex;
    flex-direction: column;
    text-align: center;
}
.addarticlerht_bottom p span:last-child{
    font-weight: normal;
}



.mainstyle{
    display: flex;
    align-items: center;
    justify-content: flex-start;
    margin-bottom: 20px;
}
.addarticle_left label{
    font-size: 16px;
    width: 50px;
}
.articleclassify label{
    width: 48px;
}
.articlecontent label{
    width: 74px;
}
.articlecontent /deep/ .ql-editor{
    height: 450px; 
    max-width: 100%;
}
.articlecontent /deep/ .ql-editor p{
    word-wrap:break-word;
}
.articlebtn{
    text-align: right;
}
</style>
