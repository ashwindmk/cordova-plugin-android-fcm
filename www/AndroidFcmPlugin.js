var exec = require('cordova/exec');

exports.updateToken = function() {
    exec(null, null, 'AndroidFcmPlugin', 'updateToken', []);
}

exports.getToken = function(success, error) {
    exec(success, error, 'AndroidFcmPlugin', 'getToken', []);
};
