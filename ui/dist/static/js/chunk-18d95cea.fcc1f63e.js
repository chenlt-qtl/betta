(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-18d95cea"],{"44cf":function(t,s,i){"use strict";i.r(s);var n=function(){var t=this,s=t.$createElement,i=t._self._c||s;return i("div",{staticClass:"test-step1"},[t.isStart?t._e():i("div",{staticClass:"word-table"},[i("el-table",{staticClass:"table",attrs:{data:t.wordList,"show-header":!1}},[i("el-table-column",{attrs:{label:"单词",prop:"wordName"},scopedSlots:t._u([{key:"default",fn:function(s){return s.row.phAnMp3?[i("div",{staticClass:"word-container"},[i("span",{staticClass:"word"},[t._v(" "+t._s(s.row.wordName))]),i("span",[t._v(" / "+t._s(s.row.phAm)+" /")])])]:void 0}}],null,!0)})],1),i("div",{staticClass:"toolbar"},[i("button",{staticClass:"block-button",on:{click:t.start}},[t._v("开始")])])],1),t.isStart?i("div",{staticClass:"word-detail"},[i("section",{staticClass:"wordName"},[t._v(" "+t._s(t.word.wordName)+" "),i("el-button",{staticClass:"soundBtn",attrs:{type:"text"},on:{click:function(){return t.play(t.word.phAnMp3)}}},[i("svg-icon",{attrs:{"icon-class":"sound"}})],1)],1),t.word.phAnMp3?i("div",{staticClass:"ph"},[t._v("/ "+t._s(t.word.phAm)+" /")]):t._e(),i("ul",{staticClass:"acceptations"},t._l(t.acceptations,(function(s){return i("li",{key:s},[t._v(" "+t._s(s)+" ")])})),0),i("div",{staticClass:"toolbar"},[i("button",{staticClass:"block-button",staticStyle:{width:"52px"},on:{click:t.nextStep}},[i("i",{staticClass:"el-icon-right"})])])]):t._e()])},a=[],e=i("c074"),o={props:["wordList","questionList"],data:function(){return{index:0,word:{},isStart:!1}},computed:{acceptations:function(){return this.word?this.word.exchange?[this.word.exchange]:this.word.acceptation.split("|"):[]}},watch:{word:function(){this.word&&this.word.phAnMp3&&Object(e["a"])(this.word.phAnMp3)}},methods:{changeIndex:function(t){this.index=this.index+t,this.word=this.wordList[this.index]},play:function(t){Object(e["a"])(t)},start:function(){this.isStart=!0,this.word=this.wordList[this.index]},nextStep:function(){this.index<this.wordList.length-1?this.changeIndex(1):this.$emit("next")}}},c=o,r=(i("e7bd"),i("2877")),d=Object(r["a"])(c,n,a,!1,null,null,null);s["default"]=d.exports},"9b28":function(t,s,i){},c074:function(t,s,i){"use strict";i.d(s,"a",(function(){return e}));i("ac1f"),i("00b4");var n,a=new Audio,e=function(t,s){var i=arguments.length>2&&void 0!==arguments[2]?arguments[2]:function(){};a.src="/prod-api"+t,a.load(),clearTimeout(n),a.pause();var e,o,c=1,r=(s||"").split(",");if(2==r.length||3==r.length){if(/^\d+$/.test(r[0]))o=parseInt(r[0]);else if(/^\d+:\d+$/.test(r[0])){var d=r[0].split(":");o=60*parseInt(d[0])+parseInt(d[1])}/^\d+(.\d+)*$/.test(r[1])&&(e=parseFloat(r[1])),r[2]&&(c=parseFloat(r[2]))}if(a.currentTime=0|o,a.playbackRate=c,a.play().catch((function(t){i&&i(t)})),e){var l=e/c;console.log("duration",l),n=setTimeout((function(){a.pause()}),1e3*l)}return a}},e7bd:function(t,s,i){"use strict";i("9b28")}}]);