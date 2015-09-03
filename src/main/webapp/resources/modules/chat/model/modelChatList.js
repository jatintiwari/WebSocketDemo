define(function(require){
	"use strict";
	
	$			=require('jquery'),
	Backbone	=require('backbone');
	
	
	var ModelChatMessage = Backbone.Model.extend({
		
		url:"message",
		
		defaults:{
			id:null,
			toUser:"",
			fromUser:"",
			displayTime:"",
			date:"",
			time:"",
			read:"",
		}
	});
	
	var ModelChatList = Backbone.Collection.extend({
		url:function(){
			return "messages?otherUser="+this.fromUser;
		},
		model:ModelChatMessage
	});
	
	return{
		ModelChatMessage: ModelChatMessage,
		ModelChatList:ModelChatList
	};
	
});