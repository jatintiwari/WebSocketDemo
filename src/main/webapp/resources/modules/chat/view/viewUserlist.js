define(function(require){
	"use strict";
	
	$ 				=require('jquery'),
	Backbone		=require('backbone');
	
	var ViewUsersList 	= Backbone.View.extend({
		tagName:"ul",
		className:"chatUserList",
		initialize:function(){
			this.listenTo(this.collection,"reset",this.render);
			this.listenTo(this.collection,"add",this.addOne);
		},
		
		evetns:{
			
		},
		
		render:function(){
			var _this = this;
			console.log(this.collection);
			_.each(this.collection.models,function(model){
				_this.$el.append(new ViewUserListItem({model:model}).render().el);
			});
			return this;
		}
	});
	
	var ViewUserListItem = Backbone.View.extend({
		tagName:"li",
		className:"chatUserListItem",
		initialize:function(){
			this.listenTo(this.model,"change",this.render);
			this.template = _.template(require("text!chat/tpl/tplUserListItem.html"));
		},
		events:{
			"click":"showChat"
		},
		render:function(){
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		},
		showChat:function(){
			if(this.model.get("unReadMessages")>0){
				indexRouter.stompClient.send("/messages/read", {}, JSON.stringify({ "fromUser": this.model.get("username"),
															"toUser": indexRouter.modelCurrentUser.attributes.username}));	
			}
			this.model.set("unReadMessages",0);
			indexRouter.showChat(this.model.get("username"));
		}
		
	});
	
	return {
		ViewUsersList:ViewUsersList
	}
	
});