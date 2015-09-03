define(function (require) {

	"use strict";

	var $               = require('jquery'),
	_                   = require('underscore'),
	Backbone            = require('backbone'),
	util            	= require('util');

var ViewLoginForm = Backbone.View.extend({
	initialize:function(){
		this.template = _.template(require('text!login/tpl/tplLoginForm.html')); 
	},

	events:{
		'change input':'setModel',
		//'submit':'submit',
	},

	render:function(){
		$('#formBody').empty();
		util.bindValidation(this);
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	},

	submit:function(e){
		e.preventDefault();
		util.laddaLoadingId('#signInButton');
		this.model.save(null,{
			success:function(model,response,options){
				indexRouter.navigate('dashboard',{trigger: true});
			},
			error:function(model,response,options){
				console.log(model.toJSON());
				console.log(response);
				console.log(options);
			}
		});
	},
	setModel:function(e){
		var id=e.target.id;
		this.model.set(id,e.currentTarget.value);
		this.model.validate;
	}
});

return {
	ViewLoginForm : ViewLoginForm
};

});
