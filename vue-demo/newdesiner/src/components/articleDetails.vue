<template>
  <div class="detailscontainer">
    <div class="detailtopdiv">
      <p class="detailtitle-p">
        <span>专栏 /</span>
        <span>{{detailData.username}} /</span>文章详情
      </p>
      <div class="detail_userinfo">
        <img src="@/assets/image/img_sys19.png" alt />
        <p class="userinfo">
          <span>{{detailData.username}}</span>发布于
          <span>{{detailData.releasetime | formateTime}}</span>
        </p>
      </div>
      <p class="title-p">{{detailData.blogtitle}}</p>
      <div class="acticle_category">
        <span>原创</span>
        <span>{{detailData.blogcategory}}</span>
        <span>{{detailData.viewquantity}}次阅读</span>
      </div>
    </div>
    <div class="detail_middle_div">
      <div class="acticles_contents">
        <p>{{detailData.blogcontent}}</p>
      </div>
      <div class="commentquantitycntainer">
        <p class="comment_top_p">{{commentparams.totaldata}}条评论</p>
        <div class="comment_div">
          <div class="commenter_img">
            <img src="@/assets/image/user-64.png" alt />
          </div>
          <div class="comment_btn">
            <Input
              v-model="commenttext"
              :rows="5"
              type="textarea"
              placeholder="Enter something..."
            />
            <Button size="large" type="primary" @click="commentFun">发布评论</Button>
          </div>
        </div>
        <div class="comment_content">
          <div class="comment_items" v-for="(commentitem ,index) in commentdata" :key="index">
            <dir class="commenter_leftdiv">
              <img src="@/assets/image/user-64.png" alt />
            </dir>
            <div class="commentitem_detail">
              <p class="comment_username">
                <span class="comment_usernamess">{{commentitem.username}}</span>
                <span class="comment_time">{{commentitem.commenttime | formateTime}}</span>
              </p>
              <p class="commetn_item_content">{{commentitem.commentcontent}}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="footpage">
      <Page :total="commentparams.totaldata" show-total @on-change="pagination"></Page>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      detailData: {}, //详情数据
      commenttext: "", //评论内容
      articleid: undefined,
      commentparams: {
        totaldata: 0, //总数
        currentPage: 1, //当前页
        pagesize: 5, //每页显示的数据
        blogid: null //文章id
      },
      commentdata: [] //评论内容
    };
  },
  methods: {
    getDetaildata() {
      let acticleid = localStorage.getItem("acticleid");
      this.articleid = acticleid;
      let dataobj = {
        blogid: acticleid
      };
      this.$Reqpost("/blog/getblogdetails", dataobj)
        .then(res => {
          console.log(res);
          if (res.code == 200) {
            this.detailData = res.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    //发布评论
    commentFun() {
      if (this.$store.state.userinfo.username) {
        this.commenttext = this.commenttext.trim();
        if (!this.commenttext) {
          this.errorAlert("请输入评论内容");
          return;
        }
        let dataobj = {
          blogid: this.articleid,
          commentcontent: this.commenttext
        };
        this.$Reqpost("/comment/addcomment", dataobj)
          .then(res => {
            console.log(res);
            if (res.code == 200) {
              this.pagination(1);
            }
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        this.$router.push({ path: "/login" });
      }
    },
    //评论分页
    pagination(page) {
      this.commentparams.currentPage = page;
      this.getCommentData();
    },
    //获取评论内容
    getCommentData() {
      let dataobj = {
        page: this.commentparams.currentPage,
        size: this.commentparams.pagesize,
        blogid: this.articleid
      };
      this.$Reqpost("/comment/getallcomment", dataobj)
        .then(res => {
          console.log(res);
          if (res.code == 200) {
            this.commentdata = res.data.datas;
            this.commentparams.totaldata = res.data.totalSize;
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  created() {
    this.getDetaildata();
    this.getCommentData();
  }
};
</script>
<style lang="less" scoped>
.detailscontainer {
  min-height: 829px;
  width: 100%;
  background-color: white;
  border-radius: 3px;
  box-shadow: 2px 4px 10px 0 rgba(0, 0, 0, 0.16);
  position: relative;
  padding: 20px;
  .detailtopdiv {
    .detailtitle-p {
      font-size: 14px;
      color: #777;
      margin-bottom: 25px;
      span {
        color: #009a61;
      }
      span:nth-child(2) {
        margin: 0 5px;
      }
    }
    .detail_userinfo {
      display: flex;
      align-items: center;
      img {
        width: 40px;
        margin-right: 20px;
      }
      .userinfo {
        font-size: 15px;
        span:first-child {
          color: #009a61;
          font-weight: bold;
          margin-right: 15px;
        }
        span:last-child {
          font-size: 13px;
          margin-left: 10px;
        }
      }
      margin-bottom: 15px;
    }
    .title-p {
      font-size: 20px;
      margin-bottom: 15px;
      color: black;
    }
    .acticle_category {
      margin-bottom: 15px;
      span:first-child {
        display: inline-block;
        width: 38px;
        height: 22px;
        text-align: center;
        line-height: 24px;
        font-size: 12px;
        border-radius: 3px;
        background-color: rgba(242, 174, 67, 0.25);
        color: #f2ae43;
      }
      span:nth-child(2) {
        display: inline-block;
        padding: 0 6px;
        color: #017e66;
        background-color: rgba(1, 126, 102, 0.08);
        height: 22px;
        line-height: 22px;
        font-weight: normal;
        font-size: 13px;
        text-align: center;
        margin: 0 20px;
      }
    }
  }
  .acticles_contents {
    margin-bottom: 30px;
    p {
      color: black;
      font-size: 15px;
      letter-spacing: 1px;
    }
  }

  //   评论
  .commentquantitycntainer {
    .comment_top_p {
      font-size: 15px;
      padding-bottom: 10px;
      border-bottom: 1px solid rgba(0, 0, 0, 0.09);
    }
    .comment_div {
      padding: 20px;
      background: #fafafa;
      display: flex;
      align-items: flex-start;
      border-bottom: 1px solid rgba(0, 0, 0, 0.09);
      .commenter_img {
        margin-right: 20px;
        img {
          width: 32px;
          border-radius: 50%;
        }
      }
      .comment_btn {
        width: 100%;
        text-align: right;
        .ivu-input-wrapper {
          margin-bottom: 15px;
        }
        .ivu-btn-primary {
          background-color: #009a61;
          border-color: #008151;
        }
      }
    }
    .comment_content {
      .comment_items {
        padding: 20px;
        border-bottom: 1px solid rgba(0, 0, 0, 0.09);
        display: flex;
        align-items: self-start;
        .commenter_leftdiv {
          img {
            width: 32px;
            border-radius: 50%;
            margin-right: 15px;
          }
        }
        .commentitem_detail {
          width: 100%;
          .comment_username {
            font-size: 14px;
            color: #009a61;
            font-weight: bold;
            margin-bottom: 10px;
            .comment_time {
              margin-left: 20px;
              font-size: 12px;
              color: gray;
              font-weight: normal;
            }
          }
          .commetn_item_content {
            font-size: 14px;
          }
        }
      }
    }
  }
  .detail_middle_div{
      margin-bottom: 30px;
  }
  #footpage {
    text-align: center;
    width: 100%;
  }
}
</style>
