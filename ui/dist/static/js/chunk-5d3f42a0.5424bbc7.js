(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5d3f42a0"],{2510:function(t,e,n){},6308:function(t,e,n){"use strict";n.r(e);var a,i,r,s=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"playlist"},[n("div",{staticClass:"phone"},[n("div",{staticClass:"inner"},[t._m(0),n("div",{staticClass:"cover"},[n("img",{attrs:{src:t.baseUrl+"/profile/sys/img/18159534045164927.jpg",alt:"Sober Up"}})]),n("div",{staticClass:"info"},[n("h3",{staticClass:"song"},[t._v(t._s(t.mp3Name))]),n("h4",{staticClass:"artist"},[t._v(t._s(t.displayStr))])]),n("div",{staticClass:"time"},[n("div",{staticClass:"bar",style:{width:t.barPercent+"%"}})]),n("div",{staticClass:"control"},[n("button",{staticClass:"controlButton",on:{click:function(){return t.step(-1)}}},[n("i",{staticClass:"fa fa-step-backward"})]),n("button",{staticClass:"playButton",on:{click:t.playList}},[n("div",[t.isPlaying?t._e():n("i",{staticClass:"fa fa-play"}),t.isPlaying?n("i",{staticClass:"fa fa-pause",staticStyle:{"font-size":"22px",left:"0"}}):t._e()])]),n("button",{staticClass:"controlButton",on:{click:function(){return t.step(1)}}},[n("i",{staticClass:"fa fa-step-forward"})])])])])])},c=[function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"header"},[n("button",{staticClass:"button back"},[n("i",{staticClass:"fa fa-arrow-left"})]),n("h2",{staticClass:"title"},[t._v("播放列表")]),n("button",{staticClass:"button more"},[n("i",{staticClass:"fa fa-ellipsis-h"})])])}],u=n("c7eb"),o=n("2909"),l=n("1da1"),d=n("5530"),f=(n("14d9"),n("b0c0"),n("d3b7"),n("159b"),n("c074")),p=n("2f62"),h=n("dcec"),m=n("ac3c"),g=n("baee"),b={data:function(){return{baseUrl:"/prod-api",playIndex:-1,isPlaying:!1,listData:[],restTime:5400,isTimed:!1}},computed:Object(d["a"])(Object(d["a"])({},Object(p["b"])(["name"])),{},{mp3Name:function(){return this.listData.length>0&&-1!=this.playIndex?this.listData[this.playIndex].title:"无标题"},displayStr:function(){var t="",e=Math.floor(this.restTime/60);t+=e<10?"0":"",t+=e;var n=this.restTime%60;return t+=n<10?" : 0":" : ",t+=n,t},barPercent:function(){return 0==this.listData.length||-1==this.playIndex?0:100*(this.playIndex+1)/this.listData.length}}),created:function(){var t=this,e=this.$route.query&&this.$route.query.article;if(e)this.getArticleDetail(e);else{var n=this.name;if(!n&&(n=this.$route.query&&this.$route.query.u,!n))return void this.$modal.msgError("请输入用户名或文章ID");Object(h["e"])({pageNum:1,pageSize:1e3,inPlayList:!0,username:n}).then((function(e){var n=e.rows;n.forEach((function(e){return t.getSentence(e)}))}))}},methods:{getSentence:function(t){var e=this;return Object(l["a"])(Object(u["a"])().mark((function n(){var a,i,r;return Object(u["a"])().wrap((function(n){while(1)switch(n.prev=n.next){case 0:return a=Object(o["a"])(e.listData),n.next=3,Object(m["e"])({pageNum:1,pageSize:1e3,articleId:t.id});case 3:return i=n.sent,i.rows&&i.rows.length>0&&i.rows.forEach((function(e){return a.push({mp3:e.mp3Time?t.mp3:e.mp3,mp3Time:e.mp3Time,title:t.title})})),n.next=7,Object(g["h"])({pageNum:1,pageSize:1e3,articleId:t.id});case 7:r=n.sent,r.rows&&r.rows.length>0&&r.rows.forEach((function(e){return a.push({mp3:e.phAnMp3,articleName:t.title})})),e.listData=a;case 10:case"end":return n.stop()}}),n)})))()},getArticleDetail:function(t){var e=this;return Object(l["a"])(Object(u["a"])().mark((function n(){var a,i;return Object(u["a"])().wrap((function(n){while(1)switch(n.prev=n.next){case 0:return n.next=2,Object(h["c"])(t);case 2:a=n.sent,i=a.data,e.getSentence(i);case 5:case"end":return n.stop()}}),n)})))()},playList:function(){this.listData.length<=0?this.$modal.msgError("播放列表为空"):(this.isPlaying=!this.isPlaying,this.isPlaying?a?a.play():(r=(new Date).getTime(),this.playIndex=0,a=this.playMp3(),a.addEventListener("pause",this.onEnded)):a.pause(),this.isPlaying&&this.isTimed&&this.timed())},onEnded:function(){var t=this;if(this.isPlaying){var e=(new Date).getTime();if(this.playIndex<this.listData.length-1)this.playIndex=this.playIndex+1;else if(this.isTimed)this.playIndex=0;else{if(!((e-r)/1e3/60<=90))return;this.playIndex=0}setTimeout((function(){return t.playMp3()}),1500)}},timed:function(){var t=this;this.restTime<=0?(clearInterval(i),i=null,a.pause()):this.isPlaying&&(this.restTime=this.restTime-1,i=setTimeout((function(){return t.timed()}),1e3))},playMp3:function(){var t=this.listData.length;if(t>0&&this.playIndex<t&&this.playIndex>=0){var e=this.listData[this.playIndex],n=e.mp3,i=e.mp3Time;return a=Object(f["a"])(n,i),a}},step:function(t){if(a){var e=this.listData.length,n=this.playIndex+t;n<e-1&&n>=0&&(this.playIndex=n,a.pause(),this.playMp3())}}}},y=b,v=(n("8a6c"),n("2877")),w=Object(v["a"])(y,s,c,!1,null,"142d7004",null);e["default"]=w.exports},"8a6c":function(t,e,n){"use strict";n("2510")},ac3c:function(t,e,n){"use strict";n.d(e,"e",(function(){return i})),n.d(e,"d",(function(){return r})),n.d(e,"c",(function(){return s})),n.d(e,"a",(function(){return c})),n.d(e,"f",(function(){return u})),n.d(e,"b",(function(){return o}));var a=n("b775");function i(t){return Object(a["a"])({url:"/eng/sentence/list",method:"get",params:t})}function r(t){return Object(a["a"])({url:"/eng/sentence/list/play",method:"get",params:t})}function s(t){return Object(a["a"])({url:"/eng/sentence/"+t,method:"get"})}function c(t){return Object(a["a"])({url:"/eng/sentence",method:"post",data:t})}function u(t){return Object(a["a"])({url:"/eng/sentence",method:"put",data:t})}function o(t){return Object(a["a"])({url:"/eng/sentence/"+t,method:"delete"})}},baee:function(t,e,n){"use strict";n.d(e,"g",(function(){return i})),n.d(e,"f",(function(){return r})),n.d(e,"h",(function(){return s})),n.d(e,"d",(function(){return c})),n.d(e,"e",(function(){return u})),n.d(e,"a",(function(){return o})),n.d(e,"b",(function(){return l})),n.d(e,"i",(function(){return d})),n.d(e,"c",(function(){return f}));var a=n("b775");function i(t){return Object(a["a"])({url:"/eng/word/list",method:"get",params:t})}function r(t){return Object(a["a"])({url:"/eng/word/new",method:"get",params:t})}function s(t){return Object(a["a"])({url:"/eng/word/list/"+t.articleId,method:"get",params:t})}function c(t){var e=t.wordName;return Object(a["a"])({url:"/eng/word?wordName="+e,method:"get"})}function u(t){return Object(a["a"])({url:"/eng/word/"+t,method:"get"})}function o(t){return Object(a["a"])({url:"/eng/word",method:"post",data:t})}function l(t,e){return Object(a["a"])({url:"/eng/word/"+t,method:"post",contentType:"application/x-www-form-urlencoded",data:{words:e}})}function d(t){return Object(a["a"])({url:"/eng/word",method:"put",data:t})}function f(t){return Object(a["a"])({url:"/eng/word/"+t,method:"delete"})}},c074:function(t,e,n){"use strict";n.d(e,"a",(function(){return r}));n("ac1f"),n("00b4");var a,i=new Audio,r=function(t,e){var n,r,s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:1;i.src="/prod-api"+t,i.load(),clearTimeout(a),i.pause();var c=(e||"").split(",");if(2==c.length){if(/^\d+$/.test(c[0]))r=parseInt(c[0]);else if(/^\d+:\d+$/.test(c[0])){var u=c[0].split(":");r=60*parseInt(u[0])+parseInt(u[1])}/^\d+(.\d+)*$/.test(c[1])&&(n=parseFloat(c[1]))}if(i.currentTime=0|r,i.playbackRate=s,i.play(),n){var o=n/s;console.log("duration",o),a=setTimeout((function(){i.pause()}),1e3*o)}return i}},dcec:function(t,e,n){"use strict";n.d(e,"d",(function(){return i})),n.d(e,"c",(function(){return r})),n.d(e,"a",(function(){return s})),n.d(e,"f",(function(){return c})),n.d(e,"b",(function(){return u})),n.d(e,"e",(function(){return o}));var a=n("b775");function i(t){return Object(a["a"])({url:"/eng/article/list",method:"get",params:t})}function r(t){return Object(a["a"])({url:"/eng/article/"+t,method:"get"})}function s(t){return Object(a["a"])({url:"/eng/article",method:"post",data:t})}function c(t){return Object(a["a"])({url:"/eng/article",method:"put",data:t})}function u(t){return Object(a["a"])({url:"/eng/article/"+t,method:"delete"})}function o(t){return Object(a["a"])({url:"/eng/article/list/play",method:"get",params:t})}}}]);