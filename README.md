# cordova-honeywell-scanner-simplified
A Ionic/Cordova Honeywell Scanner Simplified Plugin for the buil-in barcodes scanner.

# Install
Ionic: `ionic cordova plugin add cordova-honeywell-scanner-simplified`

Cordova: `cordova plugin add cordova-honeywell-scanner-simplified`

# Tested with
Honeywell ScanPal EDA50

# Usage
```javascript
window.plugins.honeywell.listen(data => {
    console.log('Scanned: ' + data);
  }, err => {
   console.log('Error occured: ' + error);
  });
```

# Tips

With Ionic 3, in order to access the window property, you must declare it inside your class: `window: any = window`
