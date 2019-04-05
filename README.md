# cordova-honeywell-scanner-simplified
A Ionic/Cordova Honeywell Scanner Simplified Plugin for the buil-in barcodes scanner.

## Install
Ionic: `ionic cordova plugin add cordova-honeywell-scanner-simplified`

Cordova: `cordova plugin add cordova-honeywell-scanner-simplified`

## Tested with
Honeywell ScanPal EDA50 (Please notify if works with other versions too)

## Usage

Call `.listen` to capture data from scanner. You can disable the capturing by calling `.release` and enable it back by calling `.claim` followed by `.listen`

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
