
define(function (require) {
  "use strict";
  Backbone            = require('backbone');
  
var ModelLoginForm= Backbone.Model.extend({

url:"j_spring_security_check",

defaults:{
	username:"",
	password:""
},
validation:{
	email: [{
      required: true,
      msg: 'Please enter an email address'
    }],
    password:{
		required:true,
	}
}

});

return{
  ModelLoginForm : ModelLoginForm
};
});
