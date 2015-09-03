define(function (require) {
  "use strict";
  Backbone            = require('backbone');

 var ModelRegistrationForm = Backbone.Model.extend({

url:"registration",

defaults:{
	firstname:"",
	lastname:"",
	mobile:"",
	email:"",
	password:""
},
validation:{
	firstname:{
		required:true,
	},
	lastname:{
		required:true,
	},
	mobile:{
		required:true,
	},
	email: [{
      required: true,
      msg: 'Please enter an email address'
    },{
      pattern: 'email',
      msg: 'Please enter a valid email'
    }],
    password:{
		required:true,
	}
}

});
 return {
 			ModelRegistrationForm : ModelRegistrationForm
 };
});