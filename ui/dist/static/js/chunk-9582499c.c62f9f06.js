(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9582499c"],{"0057":function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"read-container"},[n("div",{staticClass:"article"},[t.article.picture?n("div",{staticClass:"img",style:"background-image: url("+(t.baseUrl+t.article.picture)+")"}):t._e(),n("div",{staticClass:"article-info"},[n("section",{staticClass:"title"},[t._v(t._s(t.article.title))]),n("section",{staticClass:"group"},[t._v(t._s(t.article.groupName))])])]),n("el-radio-group",{attrs:{size:"mini"},model:{value:t.type,callback:function(e){t.type=e},expression:"type"}},[n("el-radio-button",{attrs:{label:"sentence"}},[t._v("句子")]),n("el-radio-button",{attrs:{label:"word"}},[t._v("单词")])],1),"sentence"==t.type?n("div",[n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],attrs:{data:t.sentenceList,size:"mini","show-header":!1}},[n("el-table-column",{attrs:{prop:"content"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("section",{staticClass:"sentence"},[e.row.mp3||e.row.mp3Time&&t.article.mp3?n("a",{on:{click:function(){return t.play(e.row.mp3||t.article.mp3,e.row.mp3Time)}}},[t._v(t._s(e.row.content))]):t._e(),e.row.mp3||e.row.mp3Time&&t.article.mp3?t._e():n("div",[t._v(" "+t._s(e.row.content)+" ")]),n("span",[t._v(" "+t._s(e.row.acceptation)+" "),e.row.mp3Time&&t.article.mp3?n("span",[n("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(n){return t.handleUpdate(e.row)}}}),n("el-dropdown",{attrs:{trigger:"click"},on:{command:function(n){return t.handleCommand(e.row,n)}}},[n("span",{staticClass:"el-dropdown-link"},[n("i",{staticClass:"el-icon-more"})]),n("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[n("el-dropdown-item",{attrs:{command:"a"}},[t._v("S+1")]),n("el-dropdown-item",{attrs:{command:"b"}},[t._v("S-1")]),n("el-dropdown-item",{attrs:{command:"c"}},[t._v("D+0.5")]),n("el-dropdown-item",{attrs:{command:"d"}},[t._v("D-0.5")]),n("el-dropdown-item",{attrs:{command:"e"}},[t._v("D-1")]),n("el-dropdown-item",{attrs:{command:"f"}},[t._v("X1")]),n("el-dropdown-item",{attrs:{command:"g"}},[t._v("X0.7")]),n("el-dropdown-item",{attrs:{command:"h"}},[t._v("X0.5")])],1)],1)],1):t._e()])])]}}],null,!1,2733362347)})],1)],1):t._e(),"word"==t.type?n("div",[n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],attrs:{data:t.wordList,size:"mini","show-header":!1}},[n("el-table-column",{attrs:{label:"单词",prop:"wordName"},scopedSlots:t._u([{key:"default",fn:function(e){return e.row.phMp3?[n("div",{staticClass:"word-container"},[n("section",{staticClass:"word"},[n("el-button",{attrs:{type:"text"},on:{click:function(){return t.play(e.row.phMp3)}}},[t._v(" "+t._s(e.row.wordName)+" ")]),n("span",[t._v(t._s(e.row.ph))])],1),n("span",[t._v(t._s(e.row.exchange?e.row.exchange:t.acceptationFormatter(e.row)))])])]:void 0}}],null,!0)})],1)],1):t._e(),n("el-dialog",{attrs:{title:"调整时间",visible:t.open,width:"454px","append-to-body":""},on:{"update:visible":function(e){t.open=e}}},[n("div",[n("el-input-number",{attrs:{placeholder:"请输入开始时间",min:0,size:"mini"},model:{value:t.time1,callback:function(e){t.time1=e},expression:"time1"}}),t._v(" , "),n("el-input-number",{attrs:{placeholder:"请输入结束时间",min:0,step:.3,precision:1,size:"mini"},model:{value:t.time2,callback:function(e){t.time2=e},expression:"time2"}}),t._v(" , "),n("el-input-number",{attrs:{placeholder:"请输入倍速",min:.3,step:.1,precision:1,size:"mini"},model:{value:t.time3,callback:function(e){t.time3=e},expression:"time3"}}),n("div",{staticStyle:{"padding-top":"10px",display:"flex",gap:"2px","flex-wrap":"wrap"}},[n("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(){return t.addStart(-1)}}},[t._v("S-1")]),n("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(){return t.addStart(1)}}},[t._v("S+1")]),n("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(){return t.addDuration(-.5)}}},[t._v("D-0.5")]),n("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(){return t.addDuration(.5)}}},[t._v("D+0.5")]),n("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(){return t.time3=.7}}},[t._v("X0.7")]),n("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(){return t.time3=.5}}},[t._v("X0.5")])],1),n("el-button",{attrs:{type:"text"},on:{click:function(){return t.play(t.article.mp3,t.time1+","+t.time2)}}},[t._v(" 试听 ")])],1),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("确 定")]),n("el-button",{on:{click:function(){return t.open=!1}}},[t._v("取 消")])],1)])],1)},r=[],a=(n("4de4"),n("d3b7"),n("dcec")),o=n("ac3c"),c=n("baee"),s=n("c074"),u={data:function(){return{articleId:0,article:{},baseUrl:"/prod-api",loading:!0,type:"sentence",sentenceList:[],wordList:[],open:!1,sentence:{},time1:0,time2:0,time3:1}},created:function(){this.articleId=this.$route.params&&this.$route.params.articleId,this.articleId&&(this.getArticle(),this.getSentenceList(),this.getWordList())},methods:{getArticle:function(){var t=this;this.loading=!0,Object(a["d"])(this.articleId).then((function(e){var n=e.data;t.article=n,t.loading=!1}))},play:function(t,e){Object(s["a"])(t,e)},getSentenceList:function(){var t=this;this.loading=!0,Object(o["f"])({pageNum:1,pageSize:1e3,articleId:this.articleId}).then((function(e){t.sentenceList=e.rows.filter((function(t){return t.mp3Time||t.mp3})),t.loading=!1}))},getWordList:function(){var t=this;this.loading=!0,Object(c["g"])({pageNum:1,pageSize:1e3,articleId:this.articleId}).then((function(e){t.wordList=e.rows,t.loading=!1}))},acceptationFormatter:function(t){var e=t.acceptation,n=e.split("|");return n[0]+(n.length>1?"...":"")},handleUpdate:function(t){this.sentence=t;var e=(this.sentence.mp3Time||",").split(",");this.time1=e[0],this.time2=e[1],this.time3=e[2]||1,this.open=!0},onSubmit:function(){var t=this;this.sentence.mp3Time=this.time1+","+this.time2+","+this.time3,Object(o["g"])(this.sentence).then((function(){t.$modal.msgSuccess("修改成功"),t.open=!1,t.getSentenceList()}))},addStart:function(t){this.time1+=t},addDuration:function(t){this.time2+=t},updateStart:function(t){this.time1=this.time1+t,this.onSubmit()},updateDuration:function(t){this.time2=this.time2+t,this.onSubmit()},updateRate:function(t){this.time3=t,this.onSubmit()},handleCommand:function(t,e){this.sentence=t;var n=(this.sentence.mp3Time||",").split(",");switch(this.time1=parseInt(n[0]),this.time2=parseFloat(n[1]),this.time3=n[2]||1,e){case"a":this.updateStart(1);break;case"b":this.updateStart(-1);break;case"c":this.updateDuration(.5);break;case"d":this.updateDuration(-.5);break;case"e":this.updateDuration(-1);break;case"f":this.updateRate(1);break;case"g":this.updateRate(.7);break;case"h":this.updateRate(.5);break}}}},d=u,l=(n("241e"),n("2877")),p=Object(l["a"])(d,i,r,!1,null,"31a08d9f",null);e["default"]=p.exports},"241e":function(t,e,n){"use strict";n("7228")},7228:function(t,e,n){},ac3c:function(t,e,n){"use strict";n.d(e,"f",(function(){return r})),n.d(e,"e",(function(){return a})),n.d(e,"d",(function(){return o})),n.d(e,"a",(function(){return c})),n.d(e,"b",(function(){return s})),n.d(e,"g",(function(){return u})),n.d(e,"c",(function(){return d}));var i=n("b775");function r(t){return Object(i["a"])({url:"/eng/sentence/list",method:"get",params:t})}function a(t){return Object(i["a"])({url:"/eng/sentence/list/play",method:"get",params:t})}function o(t){return Object(i["a"])({url:"/eng/sentence/"+t,method:"get"})}function c(t){return Object(i["a"])({url:"/eng/sentence",method:"post",data:t})}function s(t){return Object(i["a"])({url:"/eng/sentence/batch",method:"post",data:t})}function u(t){return Object(i["a"])({url:"/eng/sentence",method:"put",data:t})}function d(t){return Object(i["a"])({url:"/eng/sentence/"+t,method:"delete"})}},baee:function(t,e,n){"use strict";n.d(e,"f",(function(){return r})),n.d(e,"g",(function(){return a})),n.d(e,"d",(function(){return o})),n.d(e,"a",(function(){return c})),n.d(e,"h",(function(){return s})),n.d(e,"b",(function(){return u})),n.d(e,"e",(function(){return d})),n.d(e,"i",(function(){return l})),n.d(e,"c",(function(){return p}));var i=n("b775");function r(t){return Object(i["a"])({url:"/eng/word/list",method:"get",params:t})}function a(t){return Object(i["a"])({url:"/eng/word/list/"+t.articleId,method:"get",params:t})}function o(t){var e=t.wordName;return Object(i["a"])({url:"/eng/word?wordName="+e,method:"get"})}function c(t){return Object(i["a"])({url:"/eng/word",method:"post",data:t})}function s(t,e){return Object(i["a"])({url:"/eng/word/"+t,method:"post",contentType:"application/x-www-form-urlencoded",data:{words:e}})}function u(t,e){return Object(i["a"])({url:"/eng/word/"+t+"/"+e,method:"post"})}function d(t){return Object(i["a"])({url:"/eng/word/api/"+t,method:"get"})}function l(t){return Object(i["a"])({url:"/eng/word",method:"put",data:t})}function p(t){return Object(i["a"])({url:"/eng/word/"+t,method:"delete"})}},c074:function(t,e,n){"use strict";n.d(e,"a",(function(){return a}));n("ac1f"),n("00b4");var i,r=new Audio,a=function(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:function(){};r.src="/prod-api"+t,r.load(),clearTimeout(i),r.pause();var a,o,c=1,s=(e||"").split(",");if(2==s.length||3==s.length){if(/^\d+$/.test(s[0]))o=parseInt(s[0]);else if(/^\d+:\d+$/.test(s[0])){var u=s[0].split(":");o=60*parseInt(u[0])+parseInt(u[1])}/^\d+(.\d+)*$/.test(s[1])&&(a=parseFloat(s[1])),s[2]&&(c=parseFloat(s[2]))}if(r.currentTime=0|o,r.playbackRate=c,r.play().catch((function(t){n&&n(t)})),a){var d=a/c;console.log("duration",d),i=setTimeout((function(){r.pause()}),1e3*d)}return r}},dcec:function(t,e,n){"use strict";n.d(e,"e",(function(){return r})),n.d(e,"d",(function(){return a})),n.d(e,"a",(function(){return o})),n.d(e,"g",(function(){return c})),n.d(e,"b",(function(){return s})),n.d(e,"f",(function(){return u})),n.d(e,"c",(function(){return d}));var i=n("b775");function r(t){return Object(i["a"])({url:"/eng/article/list",method:"get",params:t})}function a(t){return Object(i["a"])({url:"/eng/article/"+t,method:"get"})}function o(t){return Object(i["a"])({url:"/eng/article",method:"post",data:t})}function c(t){return Object(i["a"])({url:"/eng/article",method:"put",data:t})}function s(t){return Object(i["a"])({url:"/eng/article/"+t,method:"delete"})}function u(t){return Object(i["a"])({url:"/eng/article/list/play",method:"get",params:t})}function d(t){return Object(i["a"])({url:"/eng/article/export/"+t,method:"get"})}}}]);