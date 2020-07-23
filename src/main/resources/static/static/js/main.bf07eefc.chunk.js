(this.webpackJsonpreactjs=this.webpackJsonpreactjs||[]).push([[0],{61:function(e,t,a){e.exports=a(92)},66:function(e,t,a){},67:function(e,t,a){},92:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),l=a(20),c=a.n(l),i=(a(66),a(67),a(12)),o=a(13),m=a(15),s=a(14),u=a(101),d=a(102),p=a(19),h=function(e){Object(m.a)(a,e);var t=Object(s.a)(a);function a(){return Object(i.a)(this,a),t.apply(this,arguments)}return Object(o.a)(a,[{key:"render",value:function(){return r.a.createElement(u.a,{bg:"dark",variant:"dark"},r.a.createElement(p.b,{to:"/",className:"navbar-brand"},r.a.createElement(u.a.Brand,{href:"/"},r.a.createElement("img",{src:"schedule.png",width:"25",height:"25",alt:"brand-icon"}),"Report Scheduler")),r.a.createElement(d.a,{className:"mr-auto"},r.a.createElement(p.b,{to:"/addRecipient",className:"nav-link"},"Add Recipient"),r.a.createElement(p.b,{to:"/addReport",className:"nav-link"},"Upload Report"),r.a.createElement(p.b,{to:"/ReportList",className:"nav-link"},"View Reports")))}}]),a}(n.Component),E=a(94),b=a(99),f=a(58),v=a(6),g=function(e){Object(m.a)(a,e);var t=Object(s.a)(a);function a(){return Object(i.a)(this,a),t.apply(this,arguments)}return Object(o.a)(a,[{key:"render",value:function(){var e=(new Date).getFullYear();return r.a.createElement(u.a,{bg:"dark",variant:"dark",fixed:"bottom"},r.a.createElement(E.a,null,r.a.createElement(f.a,{lg:12,className:"text-center text-muted"},r.a.createElement("div",null,e,", All right reserved"))))}}]),a}(n.Component),k=a(59),N=a(24),j=a(103),y=a(100),O=a(95),w=a(21),x=a(22),C=a(36),R=a.n(C),S=function(e){Object(m.a)(a,e);var t=Object(s.a)(a);function a(e){var n;return Object(i.a)(this,a),(n=t.call(this,e)).initialState={firstName:"",lastName:"",email:""},n.submitRecipient=function(e){e.preventDefault();var t={firstName:n.state.firstName,lastName:n.state.lastName,email:n.state.email};R.a.post("/api/recipient",t).then((function(e){null!=e.data&&(n.setState(n.initialState),alert("recipient saved successfully"))}))},n.recipientChange=function(e){n.setState(Object(k.a)({},e.target.name,e.target.value))},n.state=n.initialState,n.recipientChange=n.recipientChange.bind(Object(N.a)(n)),n.submitRecipient=n.submitRecipient.bind(Object(N.a)(n)),n}return Object(o.a)(a,[{key:"render",value:function(){var e=this.state,t=e.firstName,a=e.lastName,n=e.email;return r.a.createElement(j.a,{className:"border border-dark bg-dark text-white"},r.a.createElement(y.a,{id:"recipientFormId",onSubmit:this.submitRecipient},r.a.createElement(j.a.Header,null,r.a.createElement(w.a,{icon:x.b})," Add Recipient"),r.a.createElement(j.a.Body,null,r.a.createElement(y.a.Group,{controlId:"firstName",as:f.a},r.a.createElement(y.a.Label,null,"First Name"),r.a.createElement(y.a.Control,{required:!0,autoComplete:"off",name:"firstName",type:"test",value:t,onChange:this.recipientChange,placeholder:"Enter first name",className:"text-white bg-dark"}),r.a.createElement(y.a.Text,null)),r.a.createElement(y.a.Group,{controlId:"lastName",as:f.a},r.a.createElement(y.a.Label,null,"Last Name"),r.a.createElement(y.a.Control,{required:!0,autoComplete:"off",name:"lastName",value:a,onChange:this.recipientChange,type:"test",placeholder:"Enter last name",className:"text-white bg-dark"}),r.a.createElement(y.a.Text,null)),r.a.createElement(y.a.Group,{controlId:"email",as:f.a},r.a.createElement(y.a.Label,null,"Email"),r.a.createElement(y.a.Control,{required:!0,autoComplete:"off",name:"email",value:n,onChange:this.recipientChange,type:"test",placeholder:"Enter email",className:"text-white bg-dark"}),r.a.createElement(y.a.Text,null))),r.a.createElement(j.a.Footer,{style:{"text-align":"right"}},r.a.createElement(O.a,{variant:"success",type:"submit",size:"sm"},r.a.createElement(w.a,{icon:x.c})," Submit"))))}}]),a}(n.Component),L=a(96),D=function(e){Object(m.a)(a,e);var t=Object(s.a)(a);function a(){var e;Object(i.a)(this,a);for(var n=arguments.length,r=new Array(n),l=0;l<n;l++)r[l]=arguments[l];return(e=t.call.apply(t,[this].concat(r))).state={},e}return Object(o.a)(a,[{key:"render",value:function(){return r.a.createElement(L.a,{className:"bg-dark text-white"},r.a.createElement("h1",null,"Welcome to the Report Scheduler!"),r.a.createElement("blockquote",{className:"blockquote mb-0"},r.a.createElement("p",null,"\u201cA plan is what, a schedule is when. It takes both a plan and a schedule to get things done.\u201d"),r.a.createElement("footer",{className:"blockquote-footer"},"Peter Turla")))}}]),a}(n.Component),T=a(40),q=a.n(T),A=a(60),I=a(97),B=a(98),F=function(e){Object(m.a)(a,e);var t=Object(s.a)(a);function a(){var e;Object(i.a)(this,a);for(var n=arguments.length,r=new Array(n),l=0;l<n;l++)r[l]=arguments[l];return(e=t.call.apply(t,[this].concat(r))).state={isLoading:!0,Reports:[]},e}return Object(o.a)(a,[{key:"componentDidMount",value:function(){var e=Object(A.a)(q.a.mark((function e(){var t,a;return q.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("/api/report");case 2:return t=e.sent,e.next=5,t.json();case 5:a=e.sent,this.setState({Reports:a,isLoading:!1});case 7:case"end":return e.stop()}}),e,this)})));return function(){return e.apply(this,arguments)}}()},{key:"render",value:function(){var e=this.state,t=e.Reports;if(e.isLoading)return r.a.createElement("div",null,"Loading...");var a=t.data.map((function(e){return r.a.createElement("tr",{key:e.id},r.a.createElement("td",null,e.reportTitle),r.a.createElement("td",null,e.reportDescription),r.a.createElement("td",null,e.reportDocument),r.a.createElement("td",null,e.status),r.a.createElement("td",null,e.scheduleExpression),r.a.createElement("td",null,e.url),r.a.createElement("td",null,r.a.createElement(I.a,null,r.a.createElement(O.a,{size:"sm",variant:"outline-danger"}," ",r.a.createElement(w.a,{icon:x.d})))))}));return r.a.createElement(j.a,{className:"border border-dark bg-dark text-white"},r.a.createElement(j.a.Header,null,r.a.createElement(w.a,{icon:x.a})," Report List"),r.a.createElement(j.a.Body,null,r.a.createElement(B.a,{bordered:!0,hover:!0,striped:!0,variant:"dark"},r.a.createElement("thead",null,r.a.createElement("tr",null,r.a.createElement("th",null,"Title"),r.a.createElement("th",null,"Description"),r.a.createElement("th",null,"Document Name"),r.a.createElement("th",null,"Status"),r.a.createElement("th",null,"Schedule"),r.a.createElement("th",null,"Document Link"),r.a.createElement("th",null,"Cancel"))),r.a.createElement("tbody",null,a))))}}]),a}(n.Component);var G=function(){return r.a.createElement(p.a,null,r.a.createElement(h,null),r.a.createElement(E.a,null,r.a.createElement(b.a,null,r.a.createElement(f.a,{lg:12,style:{marginTop:"20px"}},r.a.createElement(v.c,null,r.a.createElement(v.a,{path:"/",exact:!0,component:D}),r.a.createElement(v.a,{path:"/addRecipient",exact:!0,component:S}),r.a.createElement(v.a,{path:"/Reportlist",exact:!0,component:F}))))),r.a.createElement(g,null))};Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));c.a.render(r.a.createElement(r.a.StrictMode,null,r.a.createElement(G,null)),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))}},[[61,1,2]]]);
//# sourceMappingURL=main.bf07eefc.chunk.js.map