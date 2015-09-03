define(function (require) {

	"use strict";

	var $               = require('jquery'),
	_                   = require('underscore'),
	Backbone            = require('backbone'),
	util 				= require('util');

var ViewForgotPasswordForm = Backbone.View.extend({
	initialize:function(){
		this.template = _.template(require('text!login/tpl/tplForgotPassword.html')); 
	},

	events:{
		'submit':'submit',
	},

	render:function(){
		util.bindValidation(this);
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	},

	submit:function(e){
		e.preventDefault();
		var email= $('#email').val();
		this.model.save({'email':email}, {
			success:function(){
				$("[data-dismiss='modal']").click();
			},
			error:function(){
				
			}
		});
	}
});

return{
	ViewForgotPasswordForm : ViewForgotPasswordForm
};

});
