require .config({

    baseUrl: 'resources/modules',

    paths: {
        backbone:   ['//cdnjs.cloudflare.com/ajax/libs/backbone.js/1.0.0/backbone-min',
                     '../lib/backbone/backbone-min'],
                   
        underscore: ['//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.4.4/underscore-min',
                     '../lib/backbone/underscore'],
                     
        jquery:   ['//cdnjs.cloudflare.com/ajax/libs/jquery/1.10.2/jquery.min',
                   '../lib/js/jquery'],
                   
        text:   ['//cdnjs.cloudflare.com/ajax/libs/require-text/2.0.12/text.min',
                 '../lib/require/text'],
                 
        bootstrap:  ['//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.0/js/bootstrap.min',
                     '../lib/js/bootstrap.min'],
        chart:	['//cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min','../lib/chart/Chart.min']
    },
    
    shim: {
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'underscore': {
            exports: '_'
        },
        'bootstrap': {
          deps: ['jquery']
        },
        
    },
    
    waitSeconds: 0
});

require(['jquery', 'backbone','login/loginRouter','bootstrap'], function ($, Backbone,LoginRouter,Bootstrap) {
	loginRouter = new LoginRouter();
  Backbone.history.start();
});