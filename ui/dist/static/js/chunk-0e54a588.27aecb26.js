(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0e54a588"],{"6b29":function(t,e,n){},"9ff8":function(t,e,n){"use strict";n("6b29")},baee:function(t,e,n){"use strict";n.d(e,"f",(function(){return o})),n.d(e,"g",(function(){return a})),n.d(e,"d",(function(){return i})),n.d(e,"a",(function(){return c})),n.d(e,"h",(function(){return s})),n.d(e,"b",(function(){return l})),n.d(e,"e",(function(){return u})),n.d(e,"i",(function(){return d})),n.d(e,"c",(function(){return f}));var r=n("b775");function o(t){return Object(r["a"])({url:"/eng/word/list",method:"get",params:t})}function a(t){return Object(r["a"])({url:"/eng/word/list/"+t.articleId,method:"get",params:t})}function i(t){var e=t.wordName;return Object(r["a"])({url:"/eng/word?wordName="+e,method:"get"})}function c(t){return Object(r["a"])({url:"/eng/word",method:"post",data:t})}function s(t,e){return Object(r["a"])({url:"/eng/word/"+t,method:"post",contentType:"application/x-www-form-urlencoded",data:{words:e}})}function l(t,e){return Object(r["a"])({url:"/eng/word/"+t+"/"+e,method:"post"})}function u(t){return Object(r["a"])({url:"/eng/word/api/"+t,method:"get"})}function d(t){return Object(r["a"])({url:"/eng/word",method:"put",data:t})}function f(t){return Object(r["a"])({url:"/eng/word/"+t,method:"delete"})}},c074:function(t,e,n){"use strict";n.d(e,"a",(function(){return a}));n("ac1f"),n("00b4");var r,o=new Audio,a=function(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:function(){};o.src="/prod-api"+t,o.load(),clearTimeout(r),o.pause();var a,i,c=1,s=(e||"").split(",");if(2==s.length||3==s.length){if(/^\d+$/.test(s[0]))i=parseInt(s[0]);else if(/^\d+:\d+$/.test(s[0])){var l=s[0].split(":");i=60*parseInt(l[0])+parseInt(l[1])}/^\d+(.\d+)*$/.test(s[1])&&(a=parseFloat(s[1])),s[2]&&(c=parseFloat(s[2]))}if(o.currentTime=0|i,o.playbackRate=c,o.play().catch((function(t){n&&n(t)})),a){var u=a/c;console.log("duration",u),r=setTimeout((function(){o.pause()}),1e3*u)}return o}},d044:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"word-detail"},[n("el-input",{staticStyle:{width:"50%","margin-right":"10px"},attrs:{placeholder:"请输入单词",clearable:""},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleQuery(e)}},model:{value:t.wordName,callback:function(e){t.wordName=e},expression:"wordName"}}),n("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:t.handleQuery}},[t._v("搜索")]),n("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:t.resetQuery}},[t._v("重置")]),n("el-divider",{attrs:{"content-position":"center"}}),t.form.id?n("div",[n("el-descriptions",{staticClass:"margin-top",attrs:{title:t.form.wordName,column:1,colon:!1}},[n("el-descriptions-item",{attrs:{label:""}},[t.form.phMp3?n("div",{staticClass:"ph"},[t._v(" "+t._s(t.form.phonetics)),n("el-button",{staticStyle:{marginleft:"10px"},attrs:{type:"text"},on:{click:function(){return t.play(t.form.phMp3)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1),n("el-button",{staticStyle:{marginleft:"10px"},attrs:{type:"text"},on:{click:function(){return t.play(t.form.phMp3,"0,0,0.5")}}},[n("svg-icon",{attrs:{"icon-class":"sound"}}),n("span",{staticClass:"slow"},[t._v("慢")])],1)],1):t._e(),t.form.relId?t._e():n("el-button",{attrs:{type:"text"},on:{click:t.updateRel}},[n("svg-icon",{attrs:{"icon-class":"star1"}})],1),t.form.relId?n("el-button",{attrs:{type:"text"},on:{click:t.updateRel}},[n("svg-icon",{attrs:{"icon-class":"star-fill"}})],1):t._e(),n("el-button",{attrs:{type:"text",icon:"el-icon-edit-outline"},on:{click:t.updateWord}})],1),t._l(t.form.acceptation.split("|"),(function(e,r){return[n("el-descriptions-item",{key:e,attrs:{labelStyle:{width:"64px"},label:0==r?"词霸释义:":""}},[t._v(t._s(e))])]})),t.form.exchange?n("el-descriptions-item",{attrs:{labelStyle:{width:"64px"},label:"简明注释:"}},[t._v(t._s(t.form.exchange))]):t._e()],2),n("el-card",{staticClass:"box-card"},[n("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[n("span",[t._v("自定义例句")])]),t._l(t.form.sentenceList,(function(e){return n("div",{key:e.id},[t._v(" "+t._s(e.content)),e.mp3?n("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"text"},on:{click:function(){return t.play(e.mp3)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1):t._e(),n("br"),n("br"),e.acceptation?n("span",[t._v(t._s(e.acceptation)),n("br"),n("br"),n("br"),n("br")]):t._e()],1)}))],2),n("el-card",{staticClass:"box-card"},[n("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[n("span",[t._v("爱词霸例句")])]),t._l(t.form.icibaSentenceList,(function(e){return n("div",{key:e.id},[t._v(" "+t._s(e.orig)),n("br"),n("br"),t._v(" "+t._s(e.trans)),n("br"),n("br"),n("br"),n("br")])}))],2),n("br")],1):t._e(),n("el-dialog",{attrs:{title:"更新单词",visible:t.open,width:"500px","append-to-body":""},on:{"update:visible":function(e){t.open=e}}},[n("el-form",{ref:"form",attrs:{model:t.form,"label-width":"80px"}},[n("div",{staticClass:"word-acceptation"},[t._v(t._s(t.form.acceptation))]),n("el-form-item",{attrs:{label:"简明注释",prop:"exchange"}},[n("el-input",{attrs:{placeholder:"请输入简明注释"},model:{value:t.form.exchange,callback:function(e){t.$set(t.form,"exchange",e)},expression:"form.exchange"}})],1)],1),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:t.submitForm}},[t._v("确 定")]),n("el-button",{on:{click:t.cancel}},[t._v("取 消")])],1)],1)],1)},o=[],a=(n("14d9"),n("baee")),i=n("c074"),c=n("e532"),s={name:"Word",data:function(){return{loading:!0,form:{},open:!1,wordName:this.$route.query&&this.$route.query.w}},created:function(){this.getWord(this.wordName)},methods:{getWord:function(){var t=this;this.wordName?(this.loading=!0,Object(a["d"])({wordName:this.wordName}).then((function(e){t.form=e.data,t.loading=!1}))):this.form={}},updateWord:function(){this.open=!0},handleQuery:function(){this.$router.push("/eng/word?w="+this.wordName)},resetQuery:function(){this.wordName="",this.handleQuery()},play:function(t,e){Object(i["a"])(t,e)},updateRel:function(){var t=this,e=this.form.relId;e?Object(c["b"])(e).then((function(){t.$modal.msgSuccess("取消收藏成功"),t.getWord()})):Object(c["a"])({wordName:this.form.wordName,articleId:0}).then((function(){t.$modal.msgSuccess("收藏成功"),t.getWord()}))},cancel:function(){this.open=!1},submitForm:function(){var t=this;Object(a["i"])(this.form).then((function(e){t.$modal.msgSuccess("修改成功"),t.open=!1,t.getWord()}))}},watch:{$route:function(t){var e=t.query&&t.query.w||"";this.wordName=e,this.getWord()}}},l=s,u=(n("9ff8"),n("2877")),d=Object(u["a"])(l,r,o,!1,null,null,null);e["default"]=d.exports},e532:function(t,e,n){"use strict";n.d(e,"a",(function(){return o})),n.d(e,"b",(function(){return a}));var r=n("b775");function o(t){return Object(r["a"])({url:"/eng/articleWordRel",method:"post",data:t})}function a(t){return Object(r["a"])({url:"/eng/articleWordRel/"+t,method:"delete"})}}}]);