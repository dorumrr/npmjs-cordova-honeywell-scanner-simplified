# cordova-honeywell-scanner-simplified
Ionic/Cordova Honeywell Scanner Simplified Plugin for the built-in barcodes scanner.

Install
-------

Ionic: `ionic cordova plugin add cordova-honeywell-scanner-simplified`

Cordova: `cordova plugin add cordova-honeywell-scanner-simplified`

Tested successfully with
------------------------

- Honeywell ScanPal EDA50 - Android 4.4 and 7 (confirmed by [dorumrr](https://github.com/dorumrr))

- Honeywell CT60 - Android 8.1.0 (confirmed by [andreicocari](https://github.com/andreicocari))

- Honeywell CT50 Android 6.0.1 (confirmed by [pklaes](https://github.com/pklaes))

- Honeywell EDA51 Android 8.1.0 (confirmed by [jalte](https://github.com/jalte))

**Please [notify](https://github.com/dorumrr/npmjs-cordova-honeywell-scanner-simplified/issues/2) if you have tested with other Honeywell devices!**

Usage
-----

Call `.listen` to capture scans using the device's physical buttons. Call `.scan` within your application to enable a "software" triggerd scan. You can disable the capturing by calling `.release` and enable it back by calling `.claim` followed by `.listen` method. You can also simulate a softare button to enable the reader behaving in the same way as the hardware scan button(s).

TIP: In Ionic, in order to access the `window` property, you may need to add `window: any = window` just above your constructor.

### Enable listener
```javascript
window.plugins.honeywell.listen(function(data) {
  console.log('Scanned: ' + data);
}, function (error) => {
  console.log('Error occured: ' + error);
});
```

### Disable listener
```javascript
function disable() {
  window.plugins.honeywell.release();
}
```

### Re-enable listener after being disabled (.release)
```javascript
function enable() {
  window.plugins.honeywell.claim(function(){
    window.plugins.honeywell.listen(function(data) {
      console.log('Scanned: ' + data);
    }, function (error) => {
      console.log('Error occured: ' + error);
    });
  });
}
```

### Scan button simulation to enable readings from within your application

Ionic JS/TS methods:
```javascript
scanPressed () {
  this.window.plugins.honeywell.softwareTriggerStart(function(data) {
    console.log('Software scan: ' + data);
  }, function(error) {
    console.log('Error occured: ' + error);
  });
}
scanReleased () {
  this.window.plugins.honeywell.softwareTriggerStop();
}
```

...and the HTML:
```html
<button (touchstart)="scanPressed()" (touchend)="scanReleased()">SCAN</button>
```

For a quick implementation, you can check out the Ionic Test Application [here](https://github.com/dorumrr/npmjs-cordova-honeywell-scanner-simplified-test-app)



---


HELP WANTED FOR BETTER DOCUMENTATION
====================================

I aim to have better documentation for this project (current readme.MD), so any sugestions are more than welcome!
-----------------------------------------------------------------------------------------------------------------
