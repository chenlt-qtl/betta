(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f32871c6"],{baee:function(e,t,n){"use strict";n.d(t,"g",(function(){return r})),n.d(t,"h",(function(){return o})),n.d(t,"d",(function(){return i})),n.d(t,"e",(function(){return l})),n.d(t,"a",(function(){return s})),n.d(t,"i",(function(){return c})),n.d(t,"b",(function(){return u})),n.d(t,"f",(function(){return d})),n.d(t,"j",(function(){return p})),n.d(t,"c",(function(){return m}));var a=n("b775");function r(e){return Object(a["a"])({url:"/eng/word/list",method:"get",params:e})}function o(e){return Object(a["a"])({url:"/eng/word/list/"+e.articleId,method:"get",params:e})}function i(e){var t=e.wordName;return Object(a["a"])({url:"/eng/word?wordName="+t,method:"get"})}function l(e){return Object(a["a"])({url:"/eng/word/"+e,method:"get"})}function s(e){return Object(a["a"])({url:"/eng/word",method:"post",data:e})}function c(e,t){return Object(a["a"])({url:"/eng/word/"+e,method:"post",contentType:"application/x-www-form-urlencoded",data:{words:t}})}function u(e,t){return Object(a["a"])({url:"/eng/word/"+e+"/"+t,method:"post"})}function d(e){return Object(a["a"])({url:"/eng/word/api/"+e,method:"get"})}function p(e){return Object(a["a"])({url:"/eng/word",method:"put",data:e})}function m(e){return Object(a["a"])({url:"/eng/word/"+e,method:"delete"})}},c074:function(e,t,n){"use strict";n.d(t,"a",(function(){return o}));n("ac1f"),n("00b4");var a,r=new Audio,o=function(e,t){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:function(){};r.src="/prod-api"+e,r.load(),clearTimeout(a),r.pause();var o,i,l=1,s=(t||"").split(",");if(2==s.length||3==s.length){if(/^\d+$/.test(s[0]))i=parseInt(s[0]);else if(/^\d+:\d+$/.test(s[0])){var c=s[0].split(":");i=60*parseInt(c[0])+parseInt(c[1])}/^\d+(.\d+)*$/.test(s[1])&&(o=parseFloat(s[1])),s[2]&&(l=parseFloat(s[2]))}if(r.currentTime=0|i,r.playbackRate=l,r.play().catch((function(e){n&&n(e)})),o){var u=o/l;console.log("duration",u),a=setTimeout((function(){r.pause()}),1e3*u)}return r}},cb52:function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,size:"small",inline:!0,"label-width":"68px"}},[n("el-form-item",{attrs:{label:"单词内容",prop:"wordName"}},[n("el-input",{attrs:{placeholder:"请输入单词内容",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.wordName,callback:function(t){e.$set(e.queryParams,"wordName",t)},expression:"queryParams.wordName"}})],1),n("el-form-item",{attrs:{label:"手动注释",prop:"exchange"}},[n("el-input",{attrs:{placeholder:"请输入手动注释",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.exchange,callback:function(t){e.$set(e.queryParams,"exchange",t)},expression:"queryParams.exchange"}})],1),n("el-form-item",[n("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),n("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("el-col",{attrs:{span:1.5}},[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:word:add"],expression:"['eng:word:add']"}],attrs:{type:"primary",plain:"",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAdd}},[e._v("新增")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:word:edit"],expression:"['eng:word:edit']"}],attrs:{type:"success",plain:"",icon:"el-icon-edit",size:"mini",disabled:e.single},on:{click:e.handleUpdate}},[e._v("修改")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:word:remove"],expression:"['eng:word:remove']"}],attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"mini",disabled:e.multiple},on:{click:e.handleDelete}},[e._v("删除")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:word:export"],expression:"['eng:word:export']"}],attrs:{type:"warning",plain:"",icon:"el-icon-download",size:"mini"},on:{click:e.handleExport}},[e._v("导出")])],1),n("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.wordList},on:{"selection-change":e.handleSelectionChange}},[n("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),n("el-table-column",{attrs:{label:"ID",align:"center",prop:"id"}}),n("el-table-column",{attrs:{label:"单词内容",align:"center",prop:"wordName"}}),n("el-table-column",{attrs:{label:"音标",align:"center",prop:"phonetics"}}),n("el-table-column",{attrs:{label:"解释",align:"center",prop:"acceptation"}}),n("el-table-column",{attrs:{label:"手动注释",align:"center",prop:"exchange"}}),n("el-table-column",{attrs:{label:"音频",align:"center",prop:"phMp3"},scopedSlots:e._u([{key:"default",fn:function(t){return t.row.phMp3?[n("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(t.row.phMp3)}}},[n("svg-icon",{attrs:{"icon-class":"sound"}})],1)]:void 0}}],null,!0)}),n("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:word:edit"],expression:"['eng:word:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(n){return e.handleUpdate(t.row)}}},[e._v("修改")]),n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:word:remove"],expression:"['eng:word:remove']"}],attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(n){return e.handleDelete(t.row)}}},[e._v("删除")])]}}])})],1),n("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total > 0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),n("el-dialog",{attrs:{title:e.title,visible:e.open,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[n("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"80px"}},[n("el-form-item",{attrs:{label:"单词内容",prop:"wordName"}},[n("el-input",{attrs:{placeholder:"请输入单词内容",disabled:""},model:{value:e.form.wordName,callback:function(t){e.$set(e.form,"wordName",t)},expression:"form.wordName"}})],1),n("el-form-item",{attrs:{label:"音标",prop:"phonetics"}},[n("el-input",{attrs:{placeholder:"请输入音标"},model:{value:e.form.phonetics,callback:function(t){e.$set(e.form,"phonetics",t)},expression:"form.phonetics"}})],1),n("el-form-item",{attrs:{label:"解释",prop:"acceptation"}},[n("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:e.form.acceptation,callback:function(t){e.$set(e.form,"acceptation",t)},expression:"form.acceptation"}})],1),n("el-form-item",{attrs:{label:"手动注释",prop:"exchange"}},[n("el-input",{attrs:{placeholder:"请输入手动注释"},model:{value:e.form.exchange,callback:function(t){e.$set(e.form,"exchange",t)},expression:"form.exchange"}})],1),n("el-form-item",{attrs:{label:"音频位置",prop:"phMp3"}},[n("file-upload",{attrs:{fileType:["mp3"],uploadType:"word"},model:{value:e.form.phMp3,callback:function(t){e.$set(e.form,"phMp3",t)},expression:"form.phMp3"}})],1)],1),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:e.updateWord}},[e._v("获取API数据")]),n("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),n("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1)],1)},r=[],o=n("5530"),i=(n("d81d"),n("baee")),l=n("c074"),s={name:"Word",data:function(){return{loading:!0,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,wordList:[],title:"",open:!1,queryParams:{pageNum:1,pageSize:10,wordName:null,acceptation:null,exchange:null},form:{},rules:{wordName:[{required:!0,message:"单词内容不能为空",trigger:"blur"}]}}},created:function(){this.getList()},methods:{play:function(e,t){Object(l["a"])(e,t)},getList:function(){var e=this;this.loading=!0,Object(i["g"])(this.queryParams).then((function(t){e.wordList=t.rows,e.total=t.total,e.loading=!1}))},cancel:function(){this.open=!1,this.reset()},reset:function(){this.form={id:null,wordName:null,ph:null,acceptation:null,exchange:null,parts:null,phMp3:null,status:null,createTime:null,createBy:null,updateTime:null,updateBy:null},this.resetForm("form")},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange:function(e){this.ids=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},handleAdd:function(){this.reset(),this.open=!0,this.title="添加单词"},handleUpdate:function(e){var t=this;this.reset();var n=e.id||this.ids;Object(i["e"])(n).then((function(e){t.form=e.data,t.open=!0,t.title="修改单词"}))},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){t&&(null!=e.form.id?Object(i["j"])(e.form).then((function(t){e.$modal.msgSuccess("修改成功"),e.open=!1,e.getList()})):Object(i["a"])(e.form).then((function(t){e.$modal.msgSuccess("新增成功"),e.open=!1,e.getList()})))}))},updateWord:function(){var e=this;Object(i["f"])(this.form.wordName).then((function(t){var n=t.data,a=n.phonetics,r=n.acceptation,i=n.phMp3;e.form=Object(o["a"])(Object(o["a"])({},e.form),{},{phonetics:a,acceptation:r,phMp3:i})}))},handleDelete:function(e){var t=this,n=e.id||this.ids;this.$modal.confirm('是否确认删除单词编号为"'+n+'"的数据项？').then((function(){return Object(i["c"])(n)})).then((function(){t.getList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))},handleExport:function(){this.download("eng/word/export",Object(o["a"])({},this.queryParams),"word_".concat((new Date).getTime(),".xlsx"))}}},c=s,u=n("2877"),d=Object(u["a"])(c,a,r,!1,null,null,null);t["default"]=d.exports}}]);