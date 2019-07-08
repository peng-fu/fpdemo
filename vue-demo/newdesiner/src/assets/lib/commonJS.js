import Vue from 'vue'
import '../../plugins/iview.js'
Vue.prototype.errorAlert = function (msg){
    this.$Message.error(msg);
}
Vue.prototype.successAlert = function (msg) {
    this.$Message.success(msg);
}
Vue.prototype.formateTime = function (time) {
    let date = new Date(time);
    let y = date.getFullYear();
    let MM = date.getMonth() + 1;
    MM = MM < 10 ? ('0' + MM) : MM;
    let d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    let h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    let m = date.getMinutes();
    m = m < 10 ? ('0' + m) : m;
    let s = date.getSeconds();
    s = s < 10 ? ('0' + s) : s;
    return y + "-" + MM + "-" + d + " " + h + ":" + m + ":" + s;
}
Vue.filter('formateTime',(time)=>{
    let date = new Date(time);
    let y = date.getFullYear();
    let MM = date.getMonth() + 1;
    MM = MM < 10 ? ('0' + MM) : MM;
    let d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    let h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    let m = date.getMinutes();
    m = m < 10 ? ('0' + m) : m;
    let s = date.getSeconds();
    s = s < 10 ? ('0' + s) : s;
    return y + "-" + MM + "-" + d + " " + h + ":" + m + ":" + s;
})