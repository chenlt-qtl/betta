(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-19d5fd38"],{"1afb":function(t,s,n){"use strict";n("4190")},4190:function(t,s,n){},bcf2:function(t,s,n){"use strict";n.r(s);var e=function(){var t=this,s=t.$createElement,n=t._self._c||s;return n("div",{staticClass:"test-step3"},[n("section",{staticClass:"step3-result"},[t._v(" 恭喜您，测试完成！"),n("br"),t._v(" 最终得分 "+t._s(parseInt(100*t.result.right/t.questionList.length))+" 分,答对了 "+t._s(t.result.right)+"题，答错了 "+t._s(t.result.wrong)+" 题。 ")]),n("div",{staticClass:"toolbar"},[n("button",{staticClass:"block-button",on:{click:t.next}},[t._v("下一关")]),t._v(" "),n("button",{staticClass:"block-button",on:{click:t.back}},[t._v("返回")])])])},i=[],c=(n("14d9"),{props:["wordList","result","questionList"],data:function(){return{index:0,userAnswer:-1,question:{},isStart:!1,tip:""}},created:function(){},methods:{next:function(){this.$emit("next")},back:function(){this.$router.push("/eng/article")}}}),o=c,u=(n("1afb"),n("2877")),r=Object(u["a"])(o,e,i,!1,null,"03a17cfc",null);s["default"]=r.exports}}]);