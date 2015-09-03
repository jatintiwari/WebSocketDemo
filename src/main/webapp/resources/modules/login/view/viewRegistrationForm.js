define(function (require) {

	"use strict";

	var $               = require('jquery'),
	_                   = require('underscore'),
	Backbone            = require('backbone'),
	util 				= require('util');

var ViewRegistrationForm = Backbone.View.extend({
	
	initialize:function(){
		this.template = _.template(require('text!login/tpl/tplRegistrationForm.html')); 	
	},

	events:{
		'change input':'setModel',
		'submit':'submit'
	},

	render:function(){
		util.bindValidation(this);
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	},

	submit:function(e){
		e.preventDefault();
		util.laddaLoadingId('#registerUserButton');
		this.model.save(null,{
			success:function(model,response,options){
				console.log(response);
			},
			error:function(model,response,options){
				console.log(response);
			}
		});
	},
	setModel:function(e){
		var id=e.target.id;
		this.model.set(id,e.currentTarget.value);
	}
});

return{
	ViewRegistrationForm : ViewRegistrationForm
};
});