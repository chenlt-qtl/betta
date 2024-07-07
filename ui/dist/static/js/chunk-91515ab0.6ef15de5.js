(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-91515ab0"],{"1d58":function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("el-form",{ref:"form",attrs:{model:e.article,"label-width":"80px"}},[n("el-row",[n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:"分组："}},[e._v(e._s(e.article.groupName))])],1),n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:"标题："}},[e._v(e._s(e.article.title))])],1),n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:"图片："}},[e.article.picture?n("img",{staticStyle:{width:"200px"},attrs:{src:e.baseUrl+e.article.picture}}):e._e()])],1),n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:"音频："}},[e.article.mp3?n("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(e.article.mp3)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1):e._e()],1)],1),n("el-col",{attrs:{span:24}},[n("el-form-item",{attrs:{label:"描述："}},[e._v(e._s(e.article.comment))])],1)],1),n("el-divider",{attrs:{"content-position":"center"}},[e._v("文章句子信息")]),n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{type:"primary",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAddSentence}},[e._v("添加")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{type:"danger",icon:"el-icon-delete",size:"mini"},on:{click:e.handleDeleteSentence}},[e._v("删除")])],1)],1),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.sentenceList},on:{"selection-change":e.handleSentenceSelectionChange}},[n("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),n("el-table-column",{attrs:{label:"句子内容",align:"center",prop:"content"}}),n("el-table-column",{attrs:{label:"解释",align:"center",prop:"acceptation"}}),n("el-table-column",{attrs:{label:"序号",align:"center",prop:"idx"}}),n("el-table-column",{attrs:{label:"图片",align:"center",prop:"picture",width:"100"},scopedSlots:e._u([{key:"default",fn:function(e){return e.row.picture?[n("image-preview",{attrs:{src:e.row.picture,width:50,height:50}})]:void 0}}],null,!0)}),n("el-table-column",{attrs:{label:"音频",align:"center",prop:"mp3"},scopedSlots:e._u([{key:"default",fn:function(t){return t.row.mp3||t.row.mp3Time&&e.article.mp3?[n("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(t.row.mp3||e.article.mp3,t.row.mp3Time)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1)]:void 0}}],null,!0)}),n("el-table-column",{attrs:{label:"MP3开始结束时间",align:"center",prop:"mp3Time"}}),n("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:sentence:edit"],expression:"['eng:sentence:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(n){return e.handleUpdateSentence(t.row)}}},[e._v("修改")]),n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:word:edit"],expression:"['eng:word:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(n){return e.handleUpdateWord(t.row)}}},[e._v("生词")]),n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:sentence:remove"],expression:"['eng:sentence:remove']"}],attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(n){return e.handleDeleteSentence(t.row)}}},[e._v("删除")])]}}])})],1),n("pagination",{directives:[{name:"show",rawName:"v-show",value:e.sentenceTotal>0,expression:"sentenceTotal > 0"}],attrs:{total:e.sentenceTotal,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getSentenceList}}),n("el-divider",{attrs:{"content-position":"center"}},[e._v("单词信息 (共 "+e._s(e.wordTotal)+" 条)")]),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.wordList}},[n("el-table-column",{attrs:{label:"单词",align:"center",prop:"wordName"}}),n("el-table-column",{attrs:{label:"音标",align:"center",prop:"phAm"}}),n("el-table-column",{attrs:{label:"解释",align:"center",prop:"acceptation",formatter:e.acceptationFormatter}}),n("el-table-column",{attrs:{label:"注释",align:"center",prop:"exchange"}}),n("el-table-column",{attrs:{label:"音频",align:"center",prop:"phAnMp3"},scopedSlots:e._u([{key:"default",fn:function(t){return t.row.phAnMp3?[n("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(t.row.phAnMp3)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1)]:void 0}}],null,!0)}),n("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(n){return e.handleDeleteRel(t.row)}}},[e._v("删除")])]}}])})],1),n("el-dialog",{attrs:{title:e.title,visible:e.openSentence,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.openSentence=t}}},[n("el-form",{ref:"form",attrs:{model:e.form,rules:e.sentenceRules,"label-width":"80px"}},[n("el-form-item",{attrs:{label:"句子内容",prop:"content"}},[n("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:e.form.content,callback:function(t){e.$set(e.form,"content",t)},expression:"form.content"}})],1),n("el-form-item",{attrs:{label:"解释",prop:"acceptation"}},[n("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:e.form.acceptation,callback:function(t){e.$set(e.form,"acceptation",t)},expression:"form.acceptation"}})],1),n("el-form-item",{attrs:{label:"序号",prop:"idx"}},[n("el-input",{attrs:{placeholder:"请输入序号"},model:{value:e.form.idx,callback:function(t){e.$set(e.form,"idx",t)},expression:"form.idx"}})],1),n("el-form-item",{attrs:{label:"图片",prop:"picture",uploadType:"article"}},[n("image-upload",{model:{value:e.form.picture,callback:function(t){e.$set(e.form,"picture",t)},expression:"form.picture"}})],1),e.article.mp3?n("el-form-item",{attrs:{label:"独立音频",prop:"useTopMp3"}},[n("el-radio-group",{model:{value:e.useTopMp3,callback:function(t){e.useTopMp3=t},expression:"useTopMp3"}},[n("el-radio-button",{attrs:{label:0}},[e._v("是")]),n("el-radio-button",{attrs:{label:1}},[e._v("否")])],1)],1):e._e(),e.useTopMp3?e._e():n("div",[n("el-form-item",{attrs:{label:"上传方式",prop:"uploadType"}},[n("el-radio-group",{model:{value:e.uploadType,callback:function(t){e.uploadType=t},expression:"uploadType"}},[n("el-radio",{attrs:{label:"file"}},[e._v("文件")]),n("el-radio",{attrs:{label:"url"}},[e._v("URL")])],1)],1),n("el-form-item",{attrs:{label:"音频",prop:"mp3"}},["file"==e.uploadType?n("file-upload",{attrs:{fileType:["mp3","m4a"],uploadType:"article"},model:{value:e.form.mp3,callback:function(t){e.$set(e.form,"mp3",t)},expression:"form.mp3"}}):e._e(),"url"==e.uploadType?n("uploadByUrl",{attrs:{uploadType:"article"},model:{value:e.form.mp3,callback:function(t){e.$set(e.form,"mp3",t)},expression:"form.mp3"}}):e._e()],1)],1),e.useTopMp3?n("el-form-item",{attrs:{label:"MP3时间",prop:"mp3Time"}},[n("el-input",{attrs:{placeholder:"请输入MP3时间"},model:{value:e.form.mp3Time,callback:function(t){e.$set(e.form,"mp3Time",t)},expression:"form.mp3Time"}}),e._v(" 格式1: 开始时间(int),持续时间(float) 例: 5,8.5"),n("br"),e._v(" 格式2: 开始时间(分:秒),持续时间(float) 例: 02:55,8.5"),n("br"),n("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(e.article.mp3,e.form.mp3Time)}}},[e._v("试听 ")])],1):e._e()],1),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),n("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1),n("el-dialog",{attrs:{title:e.title,visible:e.openWord,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.openWord=t}}},[e._l(e.splitWordList,(function(t,r){var i=t.text,o=t.isWord,a=t.style;return n("div",{key:r,staticStyle:{display:"inline-block"}},[o?n("el-button",{style:a,attrs:{size:"mini",type:"text"},on:{click:function(t){return e.handleClickWord(i)}}},[e._v(e._s(i))]):e._e(),o?e._e():n("div",{style:a},[e._v(e._s(i))])],1)})),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:e.submitWord}},[e._v("确 定")]),n("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],2)],1)],1)},i=[],o=n("2909"),a=n("5530"),l=(n("7db0"),n("d81d"),n("14d9"),n("a434"),n("d3b7"),n("ac1f"),n("1276"),n("159b"),n("dcec")),c=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("el-button",{attrs:{size:"mini",type:"primary"},on:{click:e.handleShowDialog}},[e._v("输入网址")]),e._v(" "+e._s(e.fileName)+" "),n("el-dialog",{attrs:{title:"",visible:e.dialogVisible,"append-to-body":"",width:"30%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[n("el-input",{attrs:{placeholder:"输入网址"},model:{value:e.fileUrl,callback:function(t){e.fileUrl=t},expression:"fileUrl"}}),n("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),n("el-button",{attrs:{type:"primary"},on:{click:e.handleUploadUrl}},[e._v("确 定")])],1)],1)],1)},s=[],u=n("2934"),d={props:["uploadType"],data:function(){return{dialogVisible:!1,fileUrl:"",fileName:""}},methods:{handleUploadUrl:function(){var e=this;Object(u["a"])(this.fileUrl,this.uploadType).then((function(t){(t.code=200)&&(e.dialogVisible=!1,e.$emit("input",t.url),console.log("===================================="),console.log(t.url),console.log("===================================="),e.fileName=t.fileName)}))},handleShowDialog:function(){this.dialogVisible=!0,this.fileUrl=""}}},p=d,m=n("2877"),f=Object(m["a"])(p,c,s,!1,null,null,null),h=f.exports,b=n("ac3c"),g=n("baee"),v=n("e532"),y=(n("13d5"),n("fb6a"),n("c35a"),n("25eb"),n("466d"),n("498a"),/[\n]+/),w=function(e,t){var n=[],r=/([a-z|'|-]+)/gi;t&&(r=/([\u4e00-\u9fa5])/gi);var i=null;return e.reduce((function(e,t){if(t){var n={allWords:[]},o=0;while(i=r.exec(t)){var a=i[0];i.index>o&&n.allWords.push({text:t.slice(o,i.index).trim(),isWord:!1}),n.allWords.push({text:a,isWord:!0}),o=i.index+a.length}o<t.length&&n.allWords.push({text:t.slice(o).trim(),isWord:!1}),e.push(n)}return e}),n)},x=n("c074"),S={name:"Article",components:{uploadByUrl:h},data:function(){return{uploadType:"file",useTopMp3:1,articleId:0,article:{},baseUrl:"/prod-api",loading:!0,sentenceIds:[],checkedEngSentence:[],single:!0,multiple:!0,sentenceTotal:0,wordTotal:0,sentenceList:[],title:"",openSentence:!1,openWord:!1,queryParams:{pageNum:1,pageSize:10,articleId:null},form:{},sentenceRules:{},wordList:[],sentenceWordList:[],splitWordList:[]}},created:function(){this.articleId=this.$route.params&&this.$route.params.articleId,this.articleId&&(this.getArticle(),this.getSentenceList(),this.getWordList())},methods:{getArticle:function(){var e=this;this.loading=!0,Object(l["c"])(this.articleId).then((function(t){var n=t.data;e.article=n,e.loading=!1}))},handleDeleteRel:function(e){var t=this;this.$modal.confirm("是否确认删除选中的单词？").then((function(){return Object(v["b"])(e.relId)})).then((function(){t.getWordList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))},play:function(e,t){Object(x["a"])(e,t)},getSentenceList:function(){var e=this;this.loading=!0;var t=Object(a["a"])(Object(a["a"])({},this.queryParams),{},{articleId:this.articleId});this.queryParams=t,Object(b["e"])(t).then((function(t){e.sentenceList=t.rows,e.sentenceTotal=t.total,e.loading=!1}))},getWordList:function(){var e=this;this.loading=!0,Object(g["h"])({pageNum:1,pageSize:1e3,articleId:this.articleId}).then((function(t){e.wordList=t.rows,e.wordTotal=t.total,e.sentenceWordList=t.rows.map((function(e){return e.wordName})),e.loading=!1}))},cancel:function(){this.openSentence=!1,this.openWord=!1,this.resetSentence()},resetSentence:function(){this.form={id:null,articleId:this.articleId,content:null,acceptation:null,idx:null,picture:null,mp3:null,mp3Time:null,status:null},this.uploadType="file",this.useTopMp3=this.article.mp3?1:0,this.resetForm("form")},handleQuery:function(){this.queryParams.pageNum=1,this.getSentenceList()},resetSentenceQuery:function(){this.resetSentence("queryForm"),this.handleQuery()},handleSentenceSelectionChange:function(e){this.sentenceIds=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},handleUpdateSentence:function(e){var t=this;this.resetSentence();var n=e.id||this.sentenceIds;Object(b["c"])(n).then((function(e){t.form=e.data,t.useTopMp3=t.article.mp3?t.form.mp3?0:1:0,t.uploadType="file",t.openSentence=!0,t.title="修改句子"}))},handleUpdateWord:function(e){this.form=e,this.splitWordList=this.getSplitWordList(),this.openWord=!0,this.title="修改生词"},getSplitWordList:function(){var e=this,t=this.form.content,n=w(t.split(y))[0].allWords;return n.forEach((function(t){e.sentenceWordList.find((function(e){return e==t.text.toLowerCase()}))?t.style={padding:"5px",margin:"0 5px",backgroundColor:"rgba(241, 196, 15, 0.3)",borderColor:"rgba(211, 84, 0, 0.5)"}:t.isWord&&(t.style={display:"inline-block",padding:"5px",margin:"0 5px"})})),n},handleClickWord:function(e){var t=this.sentenceWordList.indexOf(e.toLowerCase()),n=Object(o["a"])(this.sentenceWordList);t>-1?n.splice(t,1):n.push(e.toLowerCase()),this.sentenceWordList=n,this.splitWordList=this.getSplitWordList()},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){e.form[e.useTopMp3?"mp3":"mp3Time"]="",t&&(null!=e.form.id?Object(b["f"])(e.form).then((function(t){e.$modal.msgSuccess("修改成功"),e.openSentence=!1,e.getSentenceList()})):Object(b["a"])(e.form).then((function(t){e.$modal.msgSuccess("新增成功"),e.openSentence=!1,e.getSentenceList()})))}))},submitWord:function(){var e=this;Object(g["b"])(this.articleId,this.sentenceWordList).then((function(){e.$modal.msgSuccess("修改成功"),e.openWord=!1,e.getWordList()}))},handleDeleteSentence:function(e){var t=this,n=e.id||this.sentenceIds;this.$modal.confirm("是否确认删除选中的句子？").then((function(){return Object(b["b"])(n)})).then((function(){t.getSentenceList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))},rowEngSentenceIndex:function(e){var t=e.row,n=e.rowIndex;t.index=n+1},handleAddSentence:function(){this.resetSentence(),this.form.idx=this.sentenceTotal+1,this.openSentence=!0,this.title="添加文章句子"},acceptationFormatter:function(e,t){var n=e.acceptation,r=n.split("|");return r[0]+(r.length>1?"...":"")}}},_=S,T=Object(m["a"])(_,r,i,!1,null,null,null);t["default"]=T.exports},"25eb":function(e,t,n){var r=n("23e7"),i=n("c20d");r({target:"Number",stat:!0,forced:Number.parseInt!=i},{parseInt:i})},2934:function(e,t,n){"use strict";n.d(t,"b",(function(){return i})),n.d(t,"a",(function(){return o}));var r=n("b775");function i(e){return Object(r["a"])({url:"/common/uploadImg/note",method:"post",data:{file:e}})}function o(e,t){return Object(r["a"])({url:"/common/upload/url/"+t,method:"post",params:{url:e}})}},"466d":function(e,t,n){"use strict";var r=n("c65b"),i=n("d784"),o=n("825a"),a=n("7234"),l=n("50c4"),c=n("577e"),s=n("1d80"),u=n("dc4a"),d=n("8aa5"),p=n("14c3");i("match",(function(e,t,n){return[function(t){var n=s(this),i=a(t)?void 0:u(t,e);return i?r(i,t,n):new RegExp(t)[e](c(n))},function(e){var r=o(this),i=c(e),a=n(t,r,i);if(a.done)return a.value;if(!r.global)return p(r,i);var s=r.unicode;r.lastIndex=0;var u,m=[],f=0;while(null!==(u=p(r,i))){var h=c(u[0]);m[f]=h,""===h&&(r.lastIndex=d(i,l(r.lastIndex),s)),f++}return 0===f?null:m}]}))},"7e12":function(e,t,n){var r=n("da84"),i=n("d039"),o=n("e330"),a=n("577e"),l=n("58a8").trim,c=n("5899"),s=o("".charAt),u=r.parseFloat,d=r.Symbol,p=d&&d.iterator,m=1/u(c+"-0")!==-1/0||p&&!i((function(){u(Object(p))}));e.exports=m?function(e){var t=l(a(e)),n=u(t);return 0===n&&"-"==s(t,0)?-0:n}:u},ac3c:function(e,t,n){"use strict";n.d(t,"e",(function(){return i})),n.d(t,"d",(function(){return o})),n.d(t,"c",(function(){return a})),n.d(t,"a",(function(){return l})),n.d(t,"f",(function(){return c})),n.d(t,"b",(function(){return s}));var r=n("b775");function i(e){return Object(r["a"])({url:"/eng/sentence/list",method:"get",params:e})}function o(e){return Object(r["a"])({url:"/eng/sentence/list/play",method:"get",params:e})}function a(e){return Object(r["a"])({url:"/eng/sentence/"+e,method:"get"})}function l(e){return Object(r["a"])({url:"/eng/sentence",method:"post",data:e})}function c(e){return Object(r["a"])({url:"/eng/sentence",method:"put",data:e})}function s(e){return Object(r["a"])({url:"/eng/sentence/"+e,method:"delete"})}},baee:function(e,t,n){"use strict";n.d(t,"g",(function(){return i})),n.d(t,"f",(function(){return o})),n.d(t,"h",(function(){return a})),n.d(t,"d",(function(){return l})),n.d(t,"e",(function(){return c})),n.d(t,"a",(function(){return s})),n.d(t,"b",(function(){return u})),n.d(t,"i",(function(){return d})),n.d(t,"c",(function(){return p}));var r=n("b775");function i(e){return Object(r["a"])({url:"/eng/word/list",method:"get",params:e})}function o(e){return Object(r["a"])({url:"/eng/word/new",method:"get",params:e})}function a(e){return Object(r["a"])({url:"/eng/word/list/"+e.articleId,method:"get",params:e})}function l(e){var t=e.wordName;return Object(r["a"])({url:"/eng/word?wordName="+t,method:"get"})}function c(e){return Object(r["a"])({url:"/eng/word/"+e,method:"get"})}function s(e){return Object(r["a"])({url:"/eng/word",method:"post",data:e})}function u(e,t){return Object(r["a"])({url:"/eng/word/"+e,method:"post",contentType:"application/x-www-form-urlencoded",data:{words:t}})}function d(e){return Object(r["a"])({url:"/eng/word",method:"put",data:e})}function p(e){return Object(r["a"])({url:"/eng/word/"+e,method:"delete"})}},c074:function(e,t,n){"use strict";n.d(t,"a",(function(){return o}));n("ac1f"),n("00b4");var r,i=new Audio,o=function(e,t){var n,o,a=arguments.length>2&&void 0!==arguments[2]?arguments[2]:1;i.src="/prod-api"+e,i.load(),clearTimeout(r),i.pause();var l=(t||"").split(",");if(2==l.length){if(/^\d+$/.test(l[0]))o=parseInt(l[0]);else if(/^\d+:\d+$/.test(l[0])){var c=l[0].split(":");o=60*parseInt(c[0])+parseInt(c[1])}/^\d+(.\d+)*$/.test(l[1])&&(n=parseFloat(l[1]))}if(i.currentTime=0|o,i.playbackRate=a,i.play(),n){var s=n/a;console.log("duration",s),r=setTimeout((function(){i.pause()}),1e3*s)}return i}},c20d:function(e,t,n){var r=n("da84"),i=n("d039"),o=n("e330"),a=n("577e"),l=n("58a8").trim,c=n("5899"),s=r.parseInt,u=r.Symbol,d=u&&u.iterator,p=/^[+-]?0x/i,m=o(p.exec),f=8!==s(c+"08")||22!==s(c+"0x16")||d&&!i((function(){s(Object(d))}));e.exports=f?function(e,t){var n=l(a(e));return s(n,t>>>0||(m(p,n)?16:10))}:s},c35a:function(e,t,n){var r=n("23e7"),i=n("7e12");r({target:"Number",stat:!0,forced:Number.parseFloat!=i},{parseFloat:i})},dcec:function(e,t,n){"use strict";n.d(t,"d",(function(){return i})),n.d(t,"c",(function(){return o})),n.d(t,"a",(function(){return a})),n.d(t,"f",(function(){return l})),n.d(t,"b",(function(){return c})),n.d(t,"e",(function(){return s}));var r=n("b775");function i(e){return Object(r["a"])({url:"/eng/article/list",method:"get",params:e})}function o(e){return Object(r["a"])({url:"/eng/article/"+e,method:"get"})}function a(e){return Object(r["a"])({url:"/eng/article",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/eng/article",method:"put",data:e})}function c(e){return Object(r["a"])({url:"/eng/article/"+e,method:"delete"})}function s(e){return Object(r["a"])({url:"/eng/article/list/play",method:"get",params:e})}},e532:function(e,t,n){"use strict";n.d(t,"a",(function(){return i})),n.d(t,"b",(function(){return o}));var r=n("b775");function i(e){return Object(r["a"])({url:"/eng/articleWordRel",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/eng/articleWordRel/"+e,method:"delete"})}}}]);