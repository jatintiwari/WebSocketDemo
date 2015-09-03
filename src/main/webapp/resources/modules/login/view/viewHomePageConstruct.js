define(function(require){
	"use strict";

	$ 			= require('jquery'),
	Backbone 	= require ('backbone');
	var tpl 	= require('text!login/tpl/tplHomePageConstruct.html');

	var ViewHomePageConstruct = Backbone.View.extend({
			initialize:function(){

			},
			events:{

			},
			render:function(){
				this.$el.html(_.template(tpl));
				return this;
			}
	});


});