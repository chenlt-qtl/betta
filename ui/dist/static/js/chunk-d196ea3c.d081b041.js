(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d196ea3c"],{"3b7c":function(t,s,e){"use strict";e.r(s);var i=function(){var t=this,s=this,e=s.$createElement,i=s._self._c||e;return i("div",{staticClass:"test-step2"},[i("span",{staticClass:"tip"},[s._v(s._s(s.tip))]),i("section",{staticClass:"question"},[i("section",{staticClass:"wordName"},[s._v(s._s(this.question.question))]),s.question.type?s._e():i("span",{staticClass:"phAm"},[s._v(" / "+s._s(this.question.word.phAm)+" / "),i("svg-icon",{attrs:{"icon-class":"sound"},on:{click:function(){return s.play(t.question.word.phAnMp3)}}})],1)]),i("section",{staticClass:"answer"},[i("ul",s._l(this.question.answers,(function(t,e){return i("li",{key:t,class:"box-block "+s.getAnswerClass(e),on:{click:function(){return s.choseAnswer(e)}}},[s._v(" "+s._s(t)+" ")])})),0)])])},n=[],r=(e("7db0"),e("a15b"),e("14d9"),e("d3b7"),e("159b"),e("c074")),o={props:["wordList","result"],data:function(){return{index:0,userAnswer:-1,question:{},isStart:!1,questionList:[],type:0,tip:""}},created:function(){var t=this,s=[];this.wordList.forEach((function(e){s.push(t.getQuestion(e,0)),s.push(t.getQuestion(e,1))}));for(var e=0;e<s.length;e++){var i=parseInt(Math.random()*s.length),n=s[e];s[e]=s[i],s[i]=n}this.questionList=s,this.question=this.questionList[this.index]},watch:{question:function(){0==this.question.type&&this.question.word.phAnMp3&&Object(r["a"])(this.question.word.phAnMp3),this.tip=this.index+this.wordList.length+1+"/"+3*this.wordList.length}},methods:{getAnswerClass:function(t){if(-1!=this.userAnswer){if(this.userAnswer==t)return this.question.answerKey==t?"right":"wrong";if(this.question.answerKey==t)return"right"}return""},getRandomWords:function(t,s){var e=this,i=[],n=function(){var n=parseInt(Math.random()*e.wordList.length),r=e.wordList[n],o=i.find((function(t){return t.wordName&&t.wordName==r.wordName}));if(o||r.wordName==s.wordName||i.push(r),i.length>=t)return 1};while(1)if(n())break;return i},getExchange:function(t){return t.exchange||t.acceptation.split("|").join(";")},getWordName:function(t){return t.wordName},getQuestion:function(t,s){var e=0==s?this.getExchange:this.getWordName,i=e(t),n=[],r=parseInt(4*Math.random());n[r]=i;for(var o=this.getRandomWords(3,t),a=0;a<4;a++)if(a!=r){var u=o.pop();n[a]=e(u)}return{type:s,answerKey:r,answers:n,question:0==s?this.getWordName(t):this.getExchange(t),word:t}},changeIndex:function(t){this.index=this.index+t,this.word=this.wordList[this.index]},play:function(t){Object(r["a"])(t)},choseAnswer:function(t){var s=this;-1==this.userAnswer&&(this.userAnswer=t,Object(r["a"])(this.question.word.phAnMp3),t==this.question.answerKey?(this.question.word.familiarity=(this.question.word.familiarity||0)+1,this.result.right=this.result.right+1):(this.question.word.familiarity=(this.question.word.familiarity||0)-1,this.result.wrong=this.result.wrong+1),setTimeout((function(){s.index<s.questionList.length-1?(s.index++,s.userAnswer=-1,s.question=s.questionList[s.index]):s.$emit("next")}),1e3))}}},a=o,u=(e("c3d3"),e("2877")),c=Object(u["a"])(a,i,n,!1,null,"eea17352",null);s["default"]=c.exports},c074:function(t,s,e){"use strict";e.d(s,"a",(function(){return r}));e("ac1f"),e("00b4");var i,n=new Audio,r=function(t,s){var e=arguments.length>2&&void 0!==arguments[2]?arguments[2]:function(){};n.src="/prod-api"+t,n.load(),clearTimeout(i),n.pause();var r,o,a=1,u=(s||"").split(",");if(2==u.length||3==u.length){if(/^\d+$/.test(u[0]))o=parseInt(u[0]);else if(/^\d+:\d+$/.test(u[0])){var c=u[0].split(":");o=60*parseInt(c[0])+parseInt(c[1])}/^\d+(.\d+)*$/.test(u[1])&&(r=parseFloat(u[1])),u[2]&&(a=parseFloat(u[2]))}if(n.currentTime=0|o,n.playbackRate=a,n.play().catch((function(t){e&&e(t)})),r){var h=r/a;console.log("duration",h),i=setTimeout((function(){n.pause()}),1e3*h)}return n}},c3d3:function(t,s,e){"use strict";e("c728")},c728:function(t,s,e){}}]);