define(function(require){
	
	"use strict";
	
	$				=require('jquery'),
	Backbone		=require('backbone');
	
	
	var ModelCurrentUser = Backbone.Model.extend({
		
		url:"settings",
		
		defaults:{
			id:"",
			username:"",
			firstName:"",
			lastName:"",
			userType:""
		}
	
	});
	
	return {
		ModelCurrentUser:ModelCurrentUser
	};
});