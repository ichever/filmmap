webpackJsonp([1],{111:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n(118),s=n.n(a),r=n(194),i=(n.n(r),n(64)),o=n.n(i),l=n(38),u=n(187),c=n.n(u);l.default.use(r,{load:{key:"AIzaSyBNCLmXai1-W7259Uj2QWHWp1mXPbl-GpI"}}),t.default={extends:c.a,name:"app",data:function(){return{titleToSearch:"",src:"http://localhost:8080/films/like?name=Hours",data:"",limit:5,minChars:1,selectFirst:!0,queryParamName:"search",center:{lat:37.7749295,lng:-122.4194155},markers:[{position:{lat:37.792427,lng:-122.41061}},{position:{lat:37.81722,lng:-122.370705}}]}},components:{Map:s.a},methods:{search:function(){var e=this;o.a.get("http://localhost:8080/location?id=1").then(function(t){e.location=t.data.name}).catch(function(t){e.errors.push(t)})},onHit:function(e){console.log("On hit"),console.log(e)},prepareResponseData:function(e){return console.log("receive response"),console.log(e),e}}}},112:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n(70);t.default={extends:a.default,data:function(){return{src:"https://typeahead-js-twitter-api-proxy.herokuapp.com/demo/search",limit:5,minChars:3}},methods:{onHit:function(e){window.location.href="http://twitter.com/"+e.screen_name}}}},164:function(e,t){},165:function(e,t){},166:function(e,t){},167:function(e,t){},174:function(e,t,n){var a=n(15)(n(113),n(185),null,null);e.exports=a.exports},175:function(e,t,n){var a=n(15)(n(114),n(184),null,null);e.exports=a.exports},176:function(e,t,n){n(167);var a=n(15)(n(115),n(183),null,null);e.exports=a.exports},177:function(e,t,n){var a=n(15)(n(116),n(181),null,null);e.exports=a.exports},178:function(e,t,n){n(164);var a=n(15)(n(117),n(179),null,null);e.exports=a.exports},179:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"vue-street-view-pano-container"},[n("div",{ref:"vue-street-view-pano",staticClass:"vue-street-view-pano"}),e._v(" "),e._t("default")],2)},staticRenderFns:[]}},180:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("div",{attrs:{id:"film-info"}},[n("b-form-input",{attrs:{type:"text",placeholder:"Enter film name"},model:{value:e.titleToSearch,callback:function(t){e.titleToSearch=t},expression:"titleToSearch"}}),e._v(" "),e.loading?n("i",{staticClass:"fa fa-spinner fa-spin"}):[n("i",{directives:[{name:"show",rawName:"v-show",value:e.isEmpty,expression:"isEmpty"}],staticClass:"fa fa-search"}),e._v(" "),n("i",{directives:[{name:"show",rawName:"v-show",value:e.isDirty,expression:"isDirty"}],staticClass:"fa fa-times",on:{click:e.reset}})],e._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.query,expression:"query"}],attrs:{type:"text",placeholder:"Enter filter name",autocomplete:"off"},domProps:{value:e.query},on:{keydown:[function(t){if(!("button"in t)&&e._k(t.keyCode,"down",40))return null;e.down(t)},function(t){if(!("button"in t)&&e._k(t.keyCode,"up",38))return null;e.up(t)},function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13))return null;e.hit(t)},function(t){if(!("button"in t)&&e._k(t.keyCode,"esc",27))return null;e.reset(t)}],blur:e.reset,input:[function(t){t.target.composing||(e.query=t.target.value)},e.update]}}),e._v(" "),n("ul",{directives:[{name:"show",rawName:"v-show",value:e.hasItems,expression:"hasItems"}]},e._l(e.items,function(t,a){return n("li",{class:e.activeClass(a),on:{mousedown:e.hit,mousemove:function(t){e.setActive(a)}}},[n("span",{domProps:{textContent:e._s(t.name)}})])}))],2),e._v(" "),n("div",{attrs:{id:"map-detail"}},[n("gmap-map",{staticStyle:{width:"100%",height:"600px"},attrs:{center:e.center,zoom:10}},e._l(e.markers,function(t){return n("gmap-marker",{attrs:{position:t.position,clickable:!0,draggable:!0},on:{click:function(n){e.center=t.position}}})}))],1)])},staticRenderFns:[]}},181:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("label",[n("span",{domProps:{textContent:e._s(e.label)}}),e._v(" "),n("input",{ref:"input",class:e.className,attrs:{type:"text",placeholder:e.placeholder}})])},staticRenderFns:[]}},182:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"Typeahead"},[e.loading?n("i",{staticClass:"fa fa-spinner fa-spin"}):[n("i",{directives:[{name:"show",rawName:"v-show",value:e.isEmpty,expression:"isEmpty"}],staticClass:"fa fa-search"}),e._v(" "),n("i",{directives:[{name:"show",rawName:"v-show",value:e.isDirty,expression:"isDirty"}],staticClass:"fa fa-times",on:{click:e.reset}})],e._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.query,expression:"query"}],staticClass:"Typeahead__input",attrs:{type:"text",placeholder:"Search twitter user",autocomplete:"off"},domProps:{value:e.query},on:{keydown:[function(t){if(!("button"in t)&&e._k(t.keyCode,"down",40))return null;e.down(t)},function(t){if(!("button"in t)&&e._k(t.keyCode,"up",38))return null;e.up(t)},function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13))return null;e.hit(t)},function(t){if(!("button"in t)&&e._k(t.keyCode,"esc",27))return null;e.reset(t)}],blur:e.reset,input:[function(t){t.target.composing||(e.query=t.target.value)},e.update]}}),e._v(" "),n("ul",{directives:[{name:"show",rawName:"v-show",value:e.hasItems,expression:"hasItems"}]},e._l(e.items,function(t,a){return n("li",{class:e.activeClass(a),on:{mousedown:e.hit,mousemove:function(t){e.setActive(a)}}},[n("span",{staticClass:"name",domProps:{textContent:e._s(t.name)}}),e._v(" "),n("span",{staticClass:"screen-name",domProps:{textContent:e._s(t.screen_name)}})])}))],2)},staticRenderFns:[]}},183:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"vue-map-container"},[n("div",{ref:"vue-map",staticClass:"vue-map"}),e._v(" "),n("div",{staticClass:"vue-map-hidden"},[e._t("default")],2),e._v(" "),e._t("visible")],2)},staticRenderFns:[]}},184:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("div",{ref:"flyaway"},[e._t("default",[n("div",{domProps:{innerHTML:e._s(e.content)}})])],2)])},staticRenderFns:[]}},185:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement;return(e._self._c||t)("input",{ref:"input",attrs:{type:"text",placeholder:e.placeholder},domProps:{value:e.value}})},staticRenderFns:[]}},70:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n(38),s=n(92),r=n.n(s),i=n(64),o=n.n(i),l=n(93),u=n.n(l);a.default.prototype.$http=o.a,a.default.config.productionTip=!1,new a.default({el:"#app",template:"<App/>",components:{App:r.a,Typeahead:u.a}})},92:function(e,t,n){n(165);var a=n(15)(n(111),n(180),null,null);e.exports=a.exports},93:function(e,t,n){n(166);var a=n(15)(n(112),n(182),"data-v-61888ae6",null);e.exports=a.exports}},[70]);
//# sourceMappingURL=app.17fc9690ffbafa7a4e79.js.map