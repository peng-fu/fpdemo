import Vue from 'vue'
import '../../plugins/iview.js'
Vue.prototype.errorAlert = function (msg){
    this.$Message.error(msg);
}
Vue.prototype.successAlert = function (msg) {
    this.$Message.success(msg);
}