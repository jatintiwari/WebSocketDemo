define(function(require){

	"use strict";

	$				=require('jquery'),
	Backbone		=require('backbone');


	var ModelUser = Backbone.Model.extend({

		defaults:{
			id:"",
			username:"",
			password:"",
			unReadMessages:0,
		},

		parse: function(data){
			return data;
		}
	});

	var ModelUsersList = Backbone.Collection.extend({
		url:"userList",
		model:ModelUser,
	});


	return{
		ModelUser:ModelUser,
		ModelUsersList:ModelUsersList,
	};
});