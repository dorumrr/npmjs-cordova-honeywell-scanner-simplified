# cordova-honeywell-scanner-simplified
A Ionic/Cordova Honeywell Scanner Simplified Plugin for the buil-in barcodes scanner.

## Install
Ionic: `ionic cordova plugin add cordova-honeywell-scanner-simplified`

Cordova: `cordova plugin add cordova-honeywell-scanner-simplified`

## Tested with
Honeywell ScanPal EDA50

## Usage
```javascript
window.plugins.honeywell.listen(data => {
  console.log(`Scanned: ${data}`);
}, error => {
  console.log(`Error occured: ${error}`);
});
```
