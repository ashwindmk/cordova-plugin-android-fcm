var exec = require('cordova/exec');

function AndroidFCMPlugin() {
    console.log("AndroidFCMPlugin.js is created")
}


AndroidFCMPlugin.prototype.updateToken = function() {
    exec(null, null, 'AndroidFcmPlugin', 'updateToken', []);
}

AndroidFCMPlugin.prototype.getToken = function(success, error) {
    exec(success, error, 'AndroidFcmPlugin', 'getToken', []);
};

exec(function(result){ console.log("AndroidFCMPlugin Ready OK") }, function(result){ console.log("AndroidFCMPlugin Ready ERROR") }, "AndroidFCMPlugin",'ready',[]);

var androidFCMPlugin = new AndroidFCMPlugin();
module.exports = androidFCMPlugin;