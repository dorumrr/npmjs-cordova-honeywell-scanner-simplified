var execute = require("cordova/exec");

var honeywell = {
    listen: function (res, err) {
        return execute(res, err, 'HoneywellScannerPlugin', 'listen', []);
    },
    release: function () {
        return execute(null, null, 'HoneywellScannerPlugin', 'release', []);
    },
    claim: function () {
        return execute(null, null, 'HoneywellScannerPlugin', 'claim', []);
    },
    /*
    enable: function (res, err) {
        return exe(res, err, 'HoneywellScannerPlugin', 'enableTrigger', []);
    },
    disable: function (res, err) {
        return exe(res, err, 'HoneywellScannerPlugin', 'disableTrigger', []);
    }
    */
};

module.exports = honeywell;