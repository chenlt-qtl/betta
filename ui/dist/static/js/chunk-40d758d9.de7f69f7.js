(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-40d758d9"],{2838:function(e,t,i){"use strict";i.r(t);var r=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"article-list"},[i("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,size:"small",inline:!0,"label-width":"68px"}},[i("el-form-item",{attrs:{label:"标题",prop:"title"}},[i("el-input",{attrs:{placeholder:"请输入标题",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.title,callback:function(t){e.$set(e.queryParams,"title",t)},expression:"queryParams.title"}})],1),i("el-form-item",{attrs:{label:"分组",prop:"groupId"}},[i("el-select",{on:{change:e.handleQuery},model:{value:e.queryParams.groupId,callback:function(t){e.$set(e.queryParams,"groupId",t)},expression:"queryParams.groupId"}},e._l(e.groupList,(function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1),i("el-form-item",[i("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),i("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),i("el-row",{staticClass:"mb8",attrs:{gutter:10}},[i("el-col",{attrs:{span:1.5}},[i("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:article:add"],expression:"['eng:article:add']"}],attrs:{type:"primary",plain:"",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAdd}},[e._v("新增")])],1),i("el-col",{attrs:{span:1.5}},[i("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:article:edit"],expression:"['eng:article:edit']"}],attrs:{type:"success",plain:"",icon:"el-icon-edit",size:"mini",disabled:e.single},on:{click:e.handleUpdate}},[e._v("修改")])],1),i("el-col",{attrs:{span:1.5}},[i("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:article:remove"],expression:"['eng:article:remove']"}],attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"mini",disabled:e.single},on:{click:e.handleDelete}},[e._v("删除")])],1),i("el-col",{attrs:{span:1.5}},[i("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:article:export"],expression:"['eng:article:export']"}],attrs:{type:"warning",plain:"",icon:"el-icon-download",size:"mini"},on:{click:e.handleExport}},[e._v("导出")])],1),i("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),i("el-row",{staticClass:"mb8 group-tags",attrs:{gutter:10}},[i("groupTags",{attrs:{groupList:e.groupList},on:{input:e.handleQuery},model:{value:e.queryParams.groupId,callback:function(t){e.$set(e.queryParams,"groupId",t)},expression:"queryParams.groupId"}})],1),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.articleList},on:{"selection-change":e.handleSelectionChange}},[i("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),i("el-table-column",{attrs:{label:"文章",prop:"title"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("div",{staticClass:"cell"},[i("section",{staticClass:"content"},[i("image-preview",{attrs:{src:t.row.picture,width:50,height:50}}),i("router-link",{staticClass:"desc",attrs:{to:"/eng/article-detail/"+t.row.id}},[i("span",{staticClass:"title"},[e._v(e._s(t.row.title)+" ")]),i("span",{staticClass:"group"},[e._v(e._s(t.row.groupName))])])],1),i("section",{staticClass:"toolbar"},[i("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:article:edit"],expression:"['eng:article:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-notebook-2"},on:{click:function(i){return e.handleViewArticle(t.row)}}},[e._v("详情")]),i("el-divider",{attrs:{direction:"vertical"}}),i("read-article-btn",{attrs:{articleId:t.row.id}}),i("el-divider",{attrs:{direction:"vertical"}}),i("play-article-btn",{attrs:{articleId:t.row.id}}),i("el-divider",{attrs:{direction:"vertical"}}),i("test-article-btn",{attrs:{articleId:t.row.id}}),i("el-divider",{attrs:{direction:"vertical"}}),i("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:article:edit"],expression:"['eng:article:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(i){return e.handleUpdate(t.row)}}}),i("el-divider",{attrs:{direction:"vertical"}}),i("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:article:remove"],expression:"['eng:article:remove']"}],attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(i){return e.handleDelete(t.row)}}}),t.row.mp3?i("span",[i("el-divider",{attrs:{direction:"vertical"}}),i("el-button",{attrs:{type:"text"},on:{click:function(){return e.play(t.row.mp3)}}},[i("svg-icon",{attrs:{"icon-class":"sound"}})],1)],1):e._e()],1)])]}}])})],1),i("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total > 0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),i("el-dialog",{attrs:{title:e.title,visible:e.open,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[i("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"80px"}},[i("el-form-item",{attrs:{label:"标题",prop:"title"}},[i("el-input",{attrs:{placeholder:"请输入标题"},model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1),i("el-form-item",{attrs:{label:"图片",prop:"picture"}},[i("image-upload",{attrs:{uploadType:"article"},model:{value:e.form.picture,callback:function(t){e.$set(e.form,"picture",t)},expression:"form.picture"}})],1),i("el-form-item",{attrs:{label:"音频",prop:"mp3"}},[i("file-upload",{attrs:{fileType:["mp3"],fileSize:100,uploadType:"article"},model:{value:e.form.mp3,callback:function(t){e.$set(e.form,"mp3",t)},expression:"form.mp3"}})],1),i("el-form-item",{attrs:{label:"分组",prop:"groupId"}},[i("el-select",{model:{value:e.form.groupId,callback:function(t){e.$set(e.form,"groupId",t)},expression:"form.groupId"}},e._l(e.groupList,(function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1),i("el-form-item",{attrs:{label:"手工注释",prop:"comment"}},[i("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:e.form.comment,callback:function(t){e.$set(e.form,"comment",t)},expression:"form.comment"}})],1)],1),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),i("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1)],1)},n=[],a=i("5530"),l=(i("d81d"),i("14d9"),i("dcec")),o=i("987b"),s=i("c074"),c=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"group-tags"},e._l(e.groupList,(function(t){return i("el-tag",{key:t.id,attrs:{size:"small",type:t.id==e.value?"":"info"},on:{click:function(){return e.onTagClick(t)}}},[e._v(" "+e._s(t.name)+" ")])})),1)},u=[],d={props:["groupList","value"],data:function(){return{}},methods:{onTagClick:function(e){this.$emit("input",e.id)}}},p=d,m=(i("294e"),i("2877")),h=Object(m["a"])(p,c,u,!1,null,"85bb0ce6",null),f=h.exports,g=i("d463"),v=i("9be0"),b=i("37d0"),y={name:"Article",components:{groupTags:f,TestArticleBtn:g["a"],ReadArticleBtn:v["a"],PlayArticleBtn:b["a"]},data:function(){return{loading:!0,ids:[],checkedEngSentence:[],single:!0,multiple:!0,showSearch:!0,total:0,articleList:[],engSentenceList:[],title:"",open:!1,queryParams:{pageNum:1,pageSize:10,groupId:null,title:null},form:{},rules:{title:[{required:!0,message:"不能为空",trigger:"change"}],groupId:[{required:!0,message:"不能为空",trigger:"blur"}]},groupList:[]}},created:function(){this.getList(),this.getGroupList()},methods:{getList:function(){var e=this;this.loading=!0,Object(l["e"])(this.queryParams).then((function(t){e.articleList=t.rows,e.total=t.total,e.loading=!1}))},getGroupList:function(){var e=this;this.loading=!0,Object(o["d"])({pageNum:1,pageSize:1e3}).then((function(t){e.groupList=t.rows}))},play:function(e,t){Object(s["a"])(e,t)},cancel:function(){this.open=!1,this.reset()},reset:function(){this.form={id:null,groupId:null,picture:null,mp3:null,title:null,comment:null,status:null,createTime:null,createBy:null,updateTime:null,updateBy:null},this.engSentenceList=[],this.resetForm("form")},handleViewArticle:function(e){var t=e.id;this.$router.push("/eng/article-detail/"+t)},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange:function(e){this.ids=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},handleAdd:function(){this.reset(),this.open=!0,this.title="添加英语文章"},handleUpdate:function(e){var t=this;this.reset();var i=e.id||this.ids;Object(l["d"])(i).then((function(e){t.form=e.data,t.engSentenceList=e.data.engSentenceList,t.open=!0,t.title="修改英语文章"}))},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){t&&(e.form.engSentenceList=e.engSentenceList,null!=e.form.id?Object(l["g"])(e.form).then((function(t){e.$modal.msgSuccess("修改成功"),e.open=!1,e.getList()})):Object(l["a"])(e.form).then((function(t){e.$modal.msgSuccess("新增成功"),e.open=!1,e.getList()})))}))},handleDelete:function(e){var t=this,i=e.id||this.ids;this.$modal.confirm('是否确认删除英语文章编号为"'+i+'"的数据项？').then((function(){return Object(l["b"])(i)})).then((function(){t.getList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))},handleExport:function(){this.download("eng/article/export",Object(a["a"])({},this.queryParams),"article_".concat((new Date).getTime(),".xlsx"))}}},w=y,x=(i("f229"),Object(m["a"])(w,r,n,!1,null,"09b1cdd7",null));t["default"]=x.exports},"294e":function(e,t,i){"use strict";i("4606")},"37d0":function(e,t,i){"use strict";var r=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:article:edit"],expression:"['eng:article:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-service"},on:{click:e.handlePlayArticle}},[e._v("播放")])},n=[],a=(i("14d9"),{props:["articleId"],methods:{handlePlayArticle:function(){this.$router.push("/eng/playlist?article="+this.articleId)}}}),l=a,o=i("2877"),s=Object(o["a"])(l,r,n,!1,null,null,null);t["a"]=s.exports},4606:function(e,t,i){},"987b":function(e,t,i){"use strict";i.d(t,"d",(function(){return n})),i.d(t,"c",(function(){return a})),i.d(t,"a",(function(){return l})),i.d(t,"e",(function(){return o})),i.d(t,"b",(function(){return s}));var r=i("b775");function n(e){return Object(r["a"])({url:"/eng/group/list",method:"get",params:e})}function a(e){return Object(r["a"])({url:"/eng/group/"+e,method:"get"})}function l(e){return Object(r["a"])({url:"/eng/group",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/eng/group",method:"put",data:e})}function s(e){return Object(r["a"])({url:"/eng/group/"+e,method:"delete"})}},"9be0":function(e,t,i){"use strict";var r=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["eng:article:edit"],expression:"['eng:article:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-chat-line-square"},on:{click:e.handleReadArticle}},[e._v(" 跟读 ")])},n=[],a=(i("14d9"),{props:["articleId"],methods:{handleReadArticle:function(){this.$router.push("/eng/article/read/"+this.articleId)}}}),l=a,o=i("2877"),s=Object(o["a"])(l,r,n,!1,null,null,null);t["a"]=s.exports},c074:function(e,t,i){"use strict";i.d(t,"a",(function(){return a}));i("ac1f"),i("00b4");var r,n=new Audio,a=function(e,t){var i=arguments.length>2&&void 0!==arguments[2]?arguments[2]:function(){};n.src="/prod-api"+e,n.load(),clearTimeout(r),n.pause();var a,l,o=1,s=(t||"").split(",");if(2==s.length||3==s.length){if(/^\d+$/.test(s[0]))l=parseInt(s[0]);else if(/^\d+:\d+$/.test(s[0])){var c=s[0].split(":");l=60*parseInt(c[0])+parseInt(c[1])}/^\d+(.\d+)*$/.test(s[1])&&(a=parseFloat(s[1])),s[2]&&(o=parseFloat(s[2]))}if(n.currentTime=0|l,n.playbackRate=o,n.play().catch((function(e){i&&i(e)})),a){var u=a/o;console.log("duration",u),r=setTimeout((function(){n.pause()}),1e3*u)}return n}},d463:function(e,t,i){"use strict";var r=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-coordinate"},on:{click:e.handleTest}},[e._v("测试")])},n=[],a=(i("14d9"),{props:["articleId"],methods:{handleTest:function(){this.$router.push("/eng/article/test/"+this.articleId)}}}),l=a,o=i("2877"),s=Object(o["a"])(l,r,n,!1,null,null,null);t["a"]=s.exports},dcec:function(e,t,i){"use strict";i.d(t,"e",(function(){return n})),i.d(t,"d",(function(){return a})),i.d(t,"a",(function(){return l})),i.d(t,"g",(function(){return o})),i.d(t,"b",(function(){return s})),i.d(t,"f",(function(){return c})),i.d(t,"c",(function(){return u}));var r=i("b775");function n(e){return Object(r["a"])({url:"/eng/article/list",method:"get",params:e})}function a(e){return Object(r["a"])({url:"/eng/article/"+e,method:"get"})}function l(e){return Object(r["a"])({url:"/eng/article",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/eng/article",method:"put",data:e})}function s(e){return Object(r["a"])({url:"/eng/article/"+e,method:"delete"})}function c(e){return Object(r["a"])({url:"/eng/article/list/play",method:"get",params:e})}function u(e){return Object(r["a"])({url:"/eng/article/export/"+e,method:"get"})}},f229:function(e,t,i){"use strict";i("fc3a")},fc3a:function(e,t,i){}}]);