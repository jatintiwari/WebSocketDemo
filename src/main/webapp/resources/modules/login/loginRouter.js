define(function(require){
	
	"use strict";
	
	$				=require('jquery'),
	Backbone		=require('backbone');
	
	return Backbone.Router.extend({
		
		initialize:function(){
			console.log("web socket demo!");
			this.showIdAppSpace();
		},
		showIdAppSpace:function(){
			$('#idAppSpace').html(_.template(require('text!login/tpl/tplHomePageConstruct.html')));
		},
		showFormBody:function(view){
			$("#formBody").html(view.render().el);
		},
		
		routes:{
			""				:"login",
			"login"			:"login",
		},
		
		login:function(){
			var _this=loginRouter;
			require(['login/model/modelLoginForm','login/view/viewLoginForm'],function(modelLoginForm,viewLoginForm){
				_this.modelLoginForm = new modelLoginForm.ModelLoginForm();
				_this.viewLoginForm = new viewLoginForm.ViewLoginForm({model:_this.modelLoginForm});
				_this.showFormBody(_this.viewLoginForm);
				$('#username').focus();
			});
		}
		
	});
	
});