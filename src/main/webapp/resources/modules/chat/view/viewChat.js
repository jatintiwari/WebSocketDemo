define(function(require){
	
	"use strict";
	
	$ 				=require('jquery'),
	Backbone		=require('backbone');
	
	
	var ViewUserChat = Backbone.View.extend({
		initialize:function(){
			this.template= _.template(require("text!chat/tpl/tplChatLayout.html"));
		},
		
		events:{
			"click #send":"sendMessage"
		},
		
		render:function(){
			this.$el.html(this.template());
			return this;
		},
		
		sendMessage:function(){
			var message = $.trim($("#message").val());
			if(message=="" || message==null){
				return;
			};
			var _this =this;
			require(["util","chat/model/modelChatList"],function(util,modelChatList){
				_this.model = new modelChatList.ModelChatMessage();
				_this.model.set("message",message);
				_this.model.set("fromUser",indexRouter.modelCurrentUser.attributes.username);
				_this.model.set("toUser",indexRouter.toUser);
				_this.model.set("displayTime",util.getCurrnentDateAndTime());
				if(indexRouter.modelUserChatList.at(0).attributes.success==undefined){
					indexRouter.modelUserChatList.add(_this.model);
				}else if(indexRouter.modelUserChatList.at(0).attributes.success=="false"){
					$("#idChatList").html(new ViewUserChatList({collection:indexRouter.modelUserChatList.reset(_this.model)}).render().el);
				}
				indexRouter.stompClient.send("/message",{},JSON.stringify({"message":message,
																			"toUser":indexRouter.toUser}));
			});
			$("#message").val("");														
			return;
		}
	});
	
	var ViewUserChatList= Backbone.View.extend({
		
		initialize:function(){
			this.listenTo(this.collection,"reset",this.render);
			this.listenTo(this.collection,"add",this.addOne);
		},
		
		events:{
			
		},
		
		render:function(){
			this.$el.empty();
			var _this= this;
			_.each(this.collection.models,function(model){
				_this.addOne(model);
			});
			return this;
		},
		addOne:function(model){
			this.$el.append(new ViewUserChatListItem({model:model}).render().el);
			$("#idChatList")[0].scrollTop = $("#idChatList")[0].scrollHeight;
		}
	});
	
	var ViewUserChatListItem = Backbone.View.extend({
		
		initialize:function(){
			this.listenTo(this.model,"change",this.render);
			this.template = _.template(require("text!chat/tpl/tplChatListItem.html"));
		},
		events:{
			
		},
		render:function(){
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		}
		
	});
	
	return{
		ViewUserChat:ViewUserChat,
		ViewUserChatList:ViewUserChatList,
		ViewUserChatListItem:ViewUserChatListItem
	};
	
	
});