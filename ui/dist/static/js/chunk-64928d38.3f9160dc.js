(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-64928d38"],{"2ca2":function(t,a,s){"use strict";s("7808")},6308:function(t,a,s){"use strict";s.r(a);var i=function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"playlist"},[s("div",{staticClass:"phone"},[s("div",{staticClass:"inner"},[t._m(0),s("div",{staticClass:"cover"},[s("img",{attrs:{src:t.baseUrl+"/profile/sys/img/18159534045164927.jpg",alt:"Sober Up"}})]),t._m(1),t._m(2),s("div",{staticClass:"control"},[t._m(3),s("button",{staticClass:"playButton",on:{click:t.playList}},[t.isPlaying?t._e():s("i",{staticClass:"fa fa-play",staticStyle:{"font-size":"28px"}}),t.isPlaying?s("i",{staticClass:"fa fa-pause",staticStyle:{"font-size":"22px"}}):t._e()]),t._m(4)])])])])},e=[function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"header"},[s("button",{staticClass:"button back"},[s("i",{staticClass:"fa fa-arrow-left"})]),s("h2",{staticClass:"title"},[t._v("My Playlist")]),s("button",{staticClass:"button more"},[s("i",{staticClass:"fa fa-ellipsis-h"})])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"info"},[s("h3",{staticClass:"song"},[t._v("Sober Up")]),s("h4",{staticClass:"artist"},[t._v("AJR/Rviers Cuomo")])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"time"},[s("div",{staticClass:"bar"})])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("button",{staticClass:"controlButton"},[s("i",{staticClass:"fa fa-step-backward"})])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("button",{staticClass:"controlButton"},[s("i",{staticClass:"fa fa-step-forward"})])}],l=(s("c074"),{data:function(){return{baseUrl:"/prod-api",playIndex:0,isPlaying:!1,listData:[{name:"a123213",url:"/profile/article/2024/06/25/5_20240625110127A001.mp3"},{name:"b123123",url:"/profile/article/2024/06/05/1_20240605083055A009"}]}},methods:{playList:function(){console.log("===================================="),console.log(123),console.log("===================================="),this.isPlaying=!this.isPlaying}}}),n=l,c=(s("2ca2"),s("2877")),o=Object(c["a"])(n,i,e,!1,null,"734a9a29",null);a["default"]=o.exports},7808:function(t,a,s){},c074:function(t,a,s){"use strict";s.d(a,"a",(function(){return n}));var i,e=s("3835"),l=(s("ac1f"),s("00b4"),new Audio),n=function(t,a){var s,n,c=arguments.length>2&&void 0!==arguments[2]?arguments[2]:1;if(l.src="/prod-api"+t,l.load(),clearTimeout(i),l.pause(),/^\d+,\d+$/.test(a)){var o=a.split(","),r=Object(e["a"])(o,2),u=r[0],f=void 0===u?0:u,p=r[1],d=void 0===p?0:p;n=parseInt(f),s=parseFloat(d)}if(l.currentTime=0|n,l.playbackRate=c,l.play(),s){var v=s/c;console.log("duration",v),i=setTimeout((function(){l.pause()}),1e3*v)}return l}}}]);