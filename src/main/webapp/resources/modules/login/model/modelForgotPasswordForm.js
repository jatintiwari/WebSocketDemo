
define(function (require) {
	"use strict";
	Backbone            = require('backbone');
	
var ModelForgotPasswordForm= Backbone.Model.extend({

url:"forgotPassword",

defaults:{
	email:"",
},
validation:{
	email: [{
      required: true,
      msg: 'Please enter an email address'
    },{
      pattern: 'email',
      msg: 'Please enter a valid email'
    }],
}

});
return {
	ModelForgotPasswordForm : ModelForgotPasswordForm
}
});