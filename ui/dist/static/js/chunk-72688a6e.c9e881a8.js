(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-72688a6e"],{"2f43":function(t,e,i){"use strict";i.d(e,"g",(function(){return o})),i.d(e,"f",(function(){return r})),i.d(e,"e",(function(){return d})),i.d(e,"d",(function(){return a})),i.d(e,"b",(function(){return c})),i.d(e,"a",(function(){return l})),i.d(e,"h",(function(){return u})),i.d(e,"c",(function(){return s}));var n=i("b775");function o(t){return Object(n["a"])({url:"/video/list",method:"get",params:t})}function r(t){return Object(n["a"])({url:"/video/"+t,method:"get"})}function d(t){return Object(n["a"])({url:"/video/"+t+"/children",method:"get"})}function a(t){return Object(n["a"])({url:"/video/list/brief"+(t?"?isLeaf="+t:""),method:"get"})}function c(t){return Object(n["a"])({url:"/video",method:"post",data:t})}function l(t){return Object(n["a"])({url:"/video/children",method:"post",data:t})}function u(t){return Object(n["a"])({url:"/video",method:"put",data:t})}function s(t){return Object(n["a"])({url:"/video/"+t,method:"delete"})}},a058:function(t,e,i){"use strict";i("f8bf")},a6f8:function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"video-container"},[i("el-tree",{staticClass:"tree",attrs:{data:t.treeOptions,props:t.defaultProps,"highlight-current":"","default-expand-all":!0},on:{"node-click":t.handleNodeClick}}),i("div",{staticClass:"list"},[t.video&&t.video.id?i("div",{staticClass:"top"},[i("image-preview",{attrs:{src:t.video.img,width:250,height:150}}),i("a",{attrs:{href:t.video.url,target:"_blank"}},[t._v(" "+t._s(t.video.title)+" ")])],1):t._e(),i("ol",t._l(t.videoList,(function(e){return i("li",{key:e.id},[i("div",{staticClass:"item"},[i("a",{class:t.lastClickId==e.id?"lastClick":"",attrs:{href:"#"},on:{click:function(){return t.onLinkClick(e)}}},[t._v(" "+t._s(e.title)+" ")]),i("div",{staticClass:"duration"},[t._v(t._s(e.duration))])])])})),0)])],1)},o=[],r=(i("4de4"),i("14d9"),i("d3b7"),i("2f43")),d={name:"Video",data:function(){return{treeOptions:[],video:{},defaultProps:{label:"title"},loading:!0,videoList:[],lastClickId:-1}},created:function(){this.getTreeselect()},methods:{getTreeselect:function(){var t=this;this.loading=!0,Object(r["d"])("N").then((function(e){t.treeOptions=[];var i={id:0,title:"根节点",children:[]};i.children=t.handleTree(e.data,"id","pid"),t.treeOptions.push(i),t.loading=!1}))},handleNodeClick:function(t){var e=this;this.videoList=[],this.video={},this.lastClickId=localStorage.getItem("video_"+t.id),console.log(this.lastClickId),Object(r["e"])(t.id).then((function(t){e.videoList=t.data.filter((function(t){return t.url}))})),Object(r["f"])(t.id).then((function(t){e.video=t.data}))},onLinkClick:function(t){var e=t.url,i=t.id,n=t.pid;localStorage.setItem("video_"+n,i),this.lastClickId=i,window.open(e,"_blank")}}},a=d,c=(i("a058"),i("2877")),l=Object(c["a"])(a,n,o,!1,null,null,null);e["default"]=l.exports},f8bf:function(t,e,i){}}]);