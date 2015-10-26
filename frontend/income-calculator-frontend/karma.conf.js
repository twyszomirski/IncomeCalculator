module.exports = function(config){
  config.set({

    basePath : './',

    files : [
      'income-calculator/bower_components/angular/angular.js',
      'income-calculator/bower_components/angular-resource/angular-resource.js',
      'income-calculator/bower_components/angular-mocks/angular-mocks.js',
      'income-calculator/*.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers : ['Chrome'],

    plugins : [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine',
            'karma-junit-reporter'
            ],

    junitReporter : {
      outputFile: 'test_out/unit.xml',
      suite: 'unit'
    }

  });
};
