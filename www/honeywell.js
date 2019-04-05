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
    }
};

module.exports = honeywell;
