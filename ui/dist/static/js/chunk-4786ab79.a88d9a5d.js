(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4786ab79"],{"1d58":function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("el-form",{ref:"form",attrs:{model:e.article,"label-width":"80px"}},[n("el-row",[n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:"分组："}},[e._v(e._s(e.article.groupName))])],1),n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:"标题："}},[e._v(e._s(e.article.title))])],1),n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:"图片："}},[e.article.picture?n("img",{staticStyle:{width:"200px"},attrs:{src:e.baseUrl+e.article.picture}}):e._e()])],1),n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:"音频："}},[e.article.mp3?n("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(e.article.mp3)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1):e._e()],1)],1),n("el-col",{attrs:{span:24}},[n("el-form-item",{attrs:{label:"描述："}},[e._v(e._s(e.article.comment))])],1)],1),n("el-divider",{attrs:{"content-position":"center"}},[e._v("文章句子信息")]),n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{type:"primary",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAddSentence}},[e._v("添加")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{type:"danger",icon:"el-icon-delete",size:"mini"},on:{click:e.handleDeleteSentence}},[e._v("删除")])],1)],1),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.sentenceList},on:{"selection-change":e.handleSentenceSelectionChange}},[n("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),n("el-table-column",{attrs:{label:"句子内容",align:"center",prop:"content"}}),n("el-table-column",{attrs:{label:"解释",align:"center",prop:"acceptation"}}),n("el-table-column",{attrs:{label:"序号",align:"center",prop:"idx"}}),n("el-table-column",{attrs:{label:"图片",align:"center",prop:"picture",width:"100"},scopedSlots:e._u([{key:"default",fn:function(e){return e.row.picture?[n("image-preview",{attrs:{src:e.row.picture,width:50,height:50}})]:void 0}}],null,!0)}),n("el-table-column",{attrs:{label:"音频",align:"center",prop:"mp3"},scopedSlots:e._u([{key:"default",fn:function(t){return t.row.mp3?[n("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(t.row.mp3,t.row.mp3Time)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1)]:void 0}}],null,!0)}),n("el-table-column",{attrs:{label:"MP3开始结束时间",align:"center",prop:"mp3Time"}}),n("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:sentence:edit"],expression:"['eng:sentence:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(n){return e.handleUpdateSentence(t.row)}}},[e._v("修改")]),n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:word:edit"],expression:"['eng:word:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(n){return e.handleUpdateWord(t.row)}}},[e._v("生词")]),n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:sentence:remove"],expression:"['eng:sentence:remove']"}],attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(n){return e.handleDeleteSentence(t.row)}}},[e._v("删除")])]}}])})],1),n("pagination",{directives:[{name:"show",rawName:"v-show",value:e.sentenceTotal>0,expression:"sentenceTotal > 0"}],attrs:{total:e.sentenceTotal,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getSentenceList}}),n("el-divider",{attrs:{"content-position":"center"}},[e._v("单词信息")]),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.wordList}},[n("el-table-column",{attrs:{label:"单词",align:"center",prop:"wordName"}}),n("el-table-column",{attrs:{label:"音标",align:"center",prop:"phAm"}}),n("el-table-column",{attrs:{label:"解释",align:"center",prop:"acceptation",formatter:e.acceptationFormatter}}),n("el-table-column",{attrs:{label:"注释",align:"center",prop:"exchange"}}),n("el-table-column",{attrs:{label:"音频",align:"center",prop:"phAnMp3"},scopedSlots:e._u([{key:"default",fn:function(t){return t.row.phAnMp3?[n("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(t.row.phAnMp3)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1)]:void 0}}],null,!0)}),n("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(n){return e.handleDeleteRel(t.row)}}},[e._v("删除")])]}}])})],1),n("el-dialog",{attrs:{title:e.title,visible:e.openSentence,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.openSentence=t}}},[n("el-form",{ref:"form",attrs:{model:e.form,rules:e.sentenceRules,"label-width":"80px"}},[n("el-form-item",{attrs:{label:"句子内容",prop:"content"}},[n("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:e.form.content,callback:function(t){e.$set(e.form,"content",t)},expression:"form.content"}})],1),n("el-form-item",{attrs:{label:"解释",prop:"acceptation"}},[n("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:e.form.acceptation,callback:function(t){e.$set(e.form,"acceptation",t)},expression:"form.acceptation"}})],1),n("el-form-item",{attrs:{label:"序号",prop:"idx"}},[n("el-input",{attrs:{placeholder:"请输入序号"},model:{value:e.form.idx,callback:function(t){e.$set(e.form,"idx",t)},expression:"form.idx"}})],1),n("el-form-item",{attrs:{label:"图片",prop:"picture",uploadType:"article"}},[n("image-upload",{model:{value:e.form.picture,callback:function(t){e.$set(e.form,"picture",t)},expression:"form.picture"}})],1),n("el-form-item",{attrs:{label:"音频",prop:"mp3"}},[n("file-upload",{attrs:{fileType:["mp3"],uploadType:"article"},model:{value:e.form.mp3,callback:function(t){e.$set(e.form,"mp3",t)},expression:"form.mp3"}})],1),n("el-form-item",{attrs:{label:"MP3时间",prop:"mp3Time"}},[n("el-input",{attrs:{placeholder:"请输入MP3时间"},model:{value:e.form.mp3Time,callback:function(t){e.$set(e.form,"mp3Time",t)},expression:"form.mp3Time"}}),e._v(" 格式: 开始时间,持续时间 例: 5,8 "),e.form.mp3?n("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(e.form.mp3,e.form.mp3Time)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1):e._e()],1)],1),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),n("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1),n("el-dialog",{attrs:{title:e.title,visible:e.openWord,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.openWord=t}}},[e._l(e.splitWordList,(function(t,r){var i=t.text,a=t.isWord,o=t.style;return n("div",{key:r,staticStyle:{display:"inline-block"}},[a?n("el-button",{style:o,attrs:{size:"mini",type:"text"},on:{click:function(t){return e.handleClickWord(i)}}},[e._v(e._s(i))]):e._e(),a?e._e():n("div",{style:o},[e._v(e._s(i))])],1)})),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:e.submitWord}},[e._v("确 定")]),n("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],2)],1)],1)},i=[],a=n("2909"),o=n("5530"),l=(n("7db0"),n("d81d"),n("14d9"),n("a434"),n("d3b7"),n("ac1f"),n("1276"),n("159b"),n("dcec")),c=n("ac3c"),s=n("baee"),u=n("e532"),d=(n("13d5"),n("fb6a"),n("c35a"),n("25eb"),n("466d"),n("498a"),/[\n]+/),p=function(e,t){var n=[],r=/([a-z|'|-]+)/gi;t&&(r=/([\u4e00-\u9fa5])/gi);var i=null;return e.reduce((function(e,t){if(t){var n={allWords:[]},a=0;while(i=r.exec(t)){var o=i[0];i.index>a&&n.allWords.push({text:t.slice(a,i.index).trim(),isWord:!1}),n.allWords.push({text:o,isWord:!0}),a=i.index+o.length}a<t.length&&(console.log("===================================="),console.log(1),console.log("===================================="),n.allWords.push({text:t.slice(a).trim(),isWord:!1})),e.push(n)}return e}),n)},m=n("c074"),f={name:"Article",data:function(){return{articleId:0,article:{},baseUrl:"/prod-api",loading:!0,sentenceIds:[],checkedEngSentence:[],single:!0,multiple:!0,sentenceTotal:0,sentenceList:[],title:"",openSentence:!1,openWord:!1,queryParams:{pageNum:1,pageSize:10,articleId:null},form:{},sentenceRules:{},wordList:[],sentenceWordList:[],splitWordList:[]}},created:function(){this.articleId=this.$route.params&&this.$route.params.articleId,this.articleId&&(this.getArticle(),this.getSentenceList(),this.getWordList())},methods:{getArticle:function(){var e=this;this.loading=!0,Object(l["c"])(this.articleId).then((function(t){var n=t.data;e.article=n,e.loading=!1}))},handleDeleteRel:function(e){var t=this;this.$modal.confirm("是否确认删除选中的单词？").then((function(){return Object(u["b"])(e.relId)})).then((function(){t.getWordList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))},play:function(e,t){Object(m["a"])(e,t)},getSentenceList:function(){var e=this;this.loading=!0;var t=Object(o["a"])(Object(o["a"])({},this.queryParams),{},{articleId:this.articleId});this.queryParams=t,Object(c["d"])(t).then((function(t){e.sentenceList=t.rows,e.sentenceTotal=t.total,e.loading=!1}))},getWordList:function(){var e=this;this.loading=!0,Object(s["e"])({pageNum:1,pageSize:1e3,articleId:this.articleId}).then((function(t){e.wordList=t.rows,e.sentenceWordList=t.rows.map((function(e){return e.wordName})),e.loading=!1}))},cancel:function(){this.openSentence=!1,this.openWord=!1,this.resetSentence()},resetSentence:function(){this.form={id:null,articleId:this.articleId,content:null,acceptation:null,idx:null,picture:null,mp3:null,mp3Time:null,status:null},this.resetForm("form")},handleQuery:function(){this.queryParams.pageNum=1,this.getSentenceList()},resetSentenceQuery:function(){this.resetSentence("queryForm"),this.handleQuery()},handleSentenceSelectionChange:function(e){this.sentenceIds=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},handleAdd:function(){this.resetSentence(),this.openSentence=!0,this.title="添加英语文章"},handleUpdateSentence:function(e){var t=this;this.resetSentence();var n=e.id||this.sentenceIds;Object(c["c"])(n).then((function(e){t.form=e.data,t.openSentence=!0,t.title="修改句子"}))},handleUpdateWord:function(e){this.form=e,this.splitWordList=this.getSplitWordList(),this.openWord=!0,this.title="修改生词"},getSplitWordList:function(){var e=this,t=this.form.content,n=p(t.split(d))[0].allWords;return n.forEach((function(t){e.sentenceWordList.find((function(e){return e==t.text}))?t.style={padding:"5px",margin:"0 5px",backgroundColor:"rgba(241, 196, 15, 0.3)",borderColor:"rgba(211, 84, 0, 0.5)"}:t.isWord&&(t.style={display:"inline-block",padding:"5px",margin:"0 5px"})})),n},handleClickWord:function(e){var t=this.sentenceWordList.indexOf(e.toLowerCase()),n=Object(a["a"])(this.sentenceWordList);t>-1?n.splice(t,1):n.push(e.toLowerCase()),this.sentenceWordList=n,this.splitWordList=this.getSplitWordList()},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){t&&(null!=e.form.id?Object(c["e"])(e.form).then((function(t){e.$modal.msgSuccess("修改成功"),e.openSentence=!1,e.getSentenceList()})):Object(c["a"])(e.form).then((function(t){e.$modal.msgSuccess("新增成功"),e.openSentence=!1,e.getSentenceList()})))}))},submitWord:function(){var e=this;Object(s["a"])(this.articleId,this.sentenceWordList).then((function(){e.$modal.msgSuccess("修改成功"),e.openWord=!1,e.getWordList()}))},handleDeleteSentence:function(e){var t=this,n=e.id||this.sentenceIds;this.$modal.confirm("是否确认删除选中的句子？").then((function(){return Object(c["b"])(n)})).then((function(){t.getSentenceList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))},rowEngSentenceIndex:function(e){var t=e.row,n=e.rowIndex;t.index=n+1},handleAddSentence:function(){this.resetSentence(),this.openSentence=!0,this.title="添加文章句子"},handleExport:function(){this.download("eng/article/export",Object(o["a"])({},this.queryParams),"article_".concat((new Date).getTime(),".xlsx"))},acceptationFormatter:function(e,t){var n=e.acceptation,r=n.split("|");return r[0]+(r.length>1?"...":"")}}},h=f,b=n("2877"),g=Object(b["a"])(h,r,i,!1,null,null,null);t["default"]=g.exports},"25eb":function(e,t,n){var r=n("23e7"),i=n("c20d");r({target:"Number",stat:!0,forced:Number.parseInt!=i},{parseInt:i})},"466d":function(e,t,n){"use strict";var r=n("c65b"),i=n("d784"),a=n("825a"),o=n("7234"),l=n("50c4"),c=n("577e"),s=n("1d80"),u=n("dc4a"),d=n("8aa5"),p=n("14c3");i("match",(function(e,t,n){return[function(t){var n=s(this),i=o(t)?void 0:u(t,e);return i?r(i,t,n):new RegExp(t)[e](c(n))},function(e){var r=a(this),i=c(e),o=n(t,r,i);if(o.done)return o.value;if(!r.global)return p(r,i);var s=r.unicode;r.lastIndex=0;var u,m=[],f=0;while(null!==(u=p(r,i))){var h=c(u[0]);m[f]=h,""===h&&(r.lastIndex=d(i,l(r.lastIndex),s)),f++}return 0===f?null:m}]}))},"7e12":function(e,t,n){var r=n("da84"),i=n("d039"),a=n("e330"),o=n("577e"),l=n("58a8").trim,c=n("5899"),s=a("".charAt),u=r.parseFloat,d=r.Symbol,p=d&&d.iterator,m=1/u(c+"-0")!==-1/0||p&&!i((function(){u(Object(p))}));e.exports=m?function(e){var t=l(o(e)),n=u(t);return 0===n&&"-"==s(t,0)?-0:n}:u},ac3c:function(e,t,n){"use strict";n.d(t,"d",(function(){return i})),n.d(t,"c",(function(){return a})),n.d(t,"a",(function(){return o})),n.d(t,"e",(function(){return l})),n.d(t,"b",(function(){return c}));var r=n("b775");function i(e){return Object(r["a"])({url:"/eng/sentence/list",method:"get",params:e})}function a(e){return Object(r["a"])({url:"/eng/sentence/"+e,method:"get"})}function o(e){return Object(r["a"])({url:"/eng/sentence",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/eng/sentence",method:"put",data:e})}function c(e){return Object(r["a"])({url:"/eng/sentence/"+e,method:"delete"})}},baee:function(e,t,n){"use strict";n.d(t,"d",(function(){return i})),n.d(t,"e",(function(){return a})),n.d(t,"c",(function(){return o})),n.d(t,"a",(function(){return l})),n.d(t,"f",(function(){return c})),n.d(t,"b",(function(){return s}));var r=n("b775");function i(e){return Object(r["a"])({url:"/eng/word/new",method:"get",params:e})}function a(e){return Object(r["a"])({url:"/eng/word/list/"+e.articleId,method:"get",params:e})}function o(e){var t=e.wordName;return Object(r["a"])({url:"/eng/word?wordName="+t,method:"get"})}function l(e,t){return Object(r["a"])({url:"/eng/word/"+e,method:"post",contentType:"application/x-www-form-urlencoded",data:{words:t}})}function c(e){return Object(r["a"])({url:"/eng/word",method:"put",data:e})}function s(e){return Object(r["a"])({url:"/eng/word/"+e,method:"delete"})}},c074:function(e,t,n){"use strict";n.d(t,"a",(function(){return o}));var r,i=n("3835"),a=(n("ac1f"),n("00b4"),new Audio),o=function(e,t){var n,o,l=arguments.length>2&&void 0!==arguments[2]?arguments[2]:1;if(a.src="/prod-api"+e,a.load(),clearTimeout(r),a.pause(),/^\d+,\d+$/.test(t)){var c=t.split(","),s=Object(i["a"])(c,2),u=s[0],d=void 0===u?0:u,p=s[1],m=void 0===p?0:p;o=parseInt(d),n=parseFloat(m)}if(a.currentTime=0|o,a.playbackRate=l,a.play(),n){var f=n/l;console.log("duration",f),r=setTimeout((function(){a.pause()}),1e3*f)}}},c20d:function(e,t,n){var r=n("da84"),i=n("d039"),a=n("e330"),o=n("577e"),l=n("58a8").trim,c=n("5899"),s=r.parseInt,u=r.Symbol,d=u&&u.iterator,p=/^[+-]?0x/i,m=a(p.exec),f=8!==s(c+"08")||22!==s(c+"0x16")||d&&!i((function(){s(Object(d))}));e.exports=f?function(e,t){var n=l(o(e));return s(n,t>>>0||(m(p,n)?16:10))}:s},c35a:function(e,t,n){var r=n("23e7"),i=n("7e12");r({target:"Number",stat:!0,forced:Number.parseFloat!=i},{parseFloat:i})},dcec:function(e,t,n){"use strict";n.d(t,"d",(function(){return i})),n.d(t,"c",(function(){return a})),n.d(t,"a",(function(){return o})),n.d(t,"e",(function(){return l})),n.d(t,"b",(function(){return c}));var r=n("b775");function i(e){return Object(r["a"])({url:"/eng/article/list",method:"get",params:e})}function a(e){return Object(r["a"])({url:"/eng/article/"+e,method:"get"})}function o(e){return Object(r["a"])({url:"/eng/article",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/eng/article",method:"put",data:e})}function c(e){return Object(r["a"])({url:"/eng/article/"+e,method:"delete"})}},e532:function(e,t,n){"use strict";n.d(t,"a",(function(){return i})),n.d(t,"b",(function(){return a}));var r=n("b775");function i(e){return Object(r["a"])({url:"/eng/articleWordRel",method:"post",data:e})}function a(e){return Object(r["a"])({url:"/eng/articleWordRel/"+e,method:"delete"})}}}]);