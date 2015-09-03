define(function(require){
	"use strict";
	var 	$ = require('jquery'),
	Backbone = require('backbone');


	$(document).ready(function(){
		$('.heading').click(function(e){
			$('.heading').removeClass('active');
			$('#'+e.target.id).addClass('active');
		});
	});

	var util={

			laddaLoadingId : function(id){
				if(id!=='') {
					$(id).attr('data-style','expand-left');
					$(id).addClass('ladda-button');
					var l = Ladda.create(document.querySelector(id));
					l.start();	
					setTimeout(function(){
						l.stop();
					},500);
				}

			},

			bindValidation: function(view){
				require(['../lib/backbone/backbone-validation-min'],function(){
					Backbone.Validation.bind(view,{
						valid: function(view, attr, selector) {
							var $el = view.$('[name=' + attr + ']'), 
							$group = $el.closest('.form-group');

							$group.removeClass('has-error');
							$group.find('.help-block').html('').addClass('hidden');
						},
						invalid: function(view, attr, error, selector) {
							var $el = view.$('[name=' + attr + ']'), 
							$group = $el.closest('.form-group');
							$group.addClass('has-error');
							$group.find('.help-block').html(error).removeClass('hidden');
						}
					});
				});
			},
			getCurrnentDateAndTime:function(){
				var now = new Date();
				var thisHours = now.getHours() % 12 || 12;
				var hours = thisHours<10 ? 0+""+thisHours : thisHours;
				var minutes = now.getMinutes();
				var ampm = now.getHours() >= 12 ? 'PM' : 'AM';
				var currentTime = hours + ':' + minutes + ' ' + ampm;
				
				var date = now.getDate();
				var thisMonth = now.getMonth()+1;
				var month = thisMonth<10 ? 0+""+thisMonth : thisMonth;
				var year = now.getUTCFullYear();
				
				var today= date+"/"+month+"/"+year;
				console.log(today+" "+currentTime)
				return today+"  "+currentTime;
			}

	};
	return util;

});
