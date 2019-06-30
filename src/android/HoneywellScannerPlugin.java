package com.icsfl.rfsmart.honeywell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.AidcManager.CreatedCallback;
import com.honeywell.aidc.BarcodeFailureEvent;
import com.honeywell.aidc.BarcodeReadEvent;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.ScannerUnavailableException;
import com.honeywell.aidc.ScannerNotClaimedException;

public class HoneywellScannerPlugin extends CordovaPlugin implements BarcodeReader.BarcodeListener {
    private static final String TAG = "HoneywellScanner";
    private static BarcodeReader barcodeReader;
    private AidcManager manager;
    private CallbackContext callbackContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {

        super.initialize(cordova, webView);

        Context context = cordova.getActivity().getApplicationContext();
        AidcManager.create(context, new CreatedCallback() {
            @Override
            public void onCreated(AidcManager aidcManager) {
                manager = aidcManager;
                barcodeReader = manager.createBarcodeReader();
                if (barcodeReader != null) {
                    barcodeReader.addBarcodeListener(HoneywellScannerPlugin.this);
                    try {
                        Map<String, Object> properties = new HashMap<String, Object>();
                        properties.put("PROPERTY_UPC_A_ENABLE", "DEC_UPCA_ENABLE");
                        properties.put("PROPERTY_UPC_A_TRANSLATE_EAN13", "DEC_UPCA_TRANSLATE_TO_EAN13");
                        properties.put("PROPERTY_UPC_A_COUPON_CODE_MODE_ENABLED", "DEC_COUPON_CODE_MODE");
                        properties.put("PROPERTY_UPC_A_COMBINE_COUPON_CODE_MODE_ENABLED", "DEC_COMBINE_COUPON_CODES");
                        properties.put("PROPERTY_UPC_A_CHECK_DIGIT_TRANSMIT_ENABLED", "DEC_UPCA_CHECK_DIGIT_TRANSMIT");
                        properties.put("PROPERTY_UPC_A_NUMBER_SYSTEM_TRANSMIT_ENABLED", "DEC_UPCA_NUMBER_SYSTEM_TRANSMIT");
//                        properties.put("PROPERTY_UPC_A_TWO_CHAR_ADDENDA_ENABLED", "DEC_UPCA_2CHAR_ADDENDA_ENABLED");
//                        properties.put("PROPERTY_UPC_A_FIVE_CHAR_ADDENDA_ENABLED", "DEC_UPCA_5CHAR_ADDENDA_ENABLED");
//                        properties.put("PROPERTY_UPC_A_ADDENDA_REQUIRED_ENABLED", "DEC_UPCA_ADDENDA_REQUIRED");
//                        properties.put("PROPERTY_UPC_A_ADDENDA_SEPARATOR_ENABLED", "DEC_UPCA_ADDENDA_SEPARATOR");
                        properties.put("PROPERTY_UPC_E_ENABLED", "DEC_UPCE0_ENABLED");
                        properties.put("PROPERTY_UPC_E_E1_ENABLED", "DEC_UPCE1_ENABLED");
                        properties.put("PROPERTY_UPC_E_EXPAND_TO_UPC_A", "DEC_UPCE_EXPAND");
                        properties.put("PROPERTY_UPC_E_CHECK_DIGIT_TRANSMIT_ENABLED", "DEC_UPCE_CHECK_DIGIT_TRANSMIT");
                        properties.put("PROPERTY_UPC_E_NUMBER_SYSTEM_TRANSMIT_ENABLED", "DEC_UPCE_NUMBER_SYSTEM_TRANSMIT");
//                        properties.put("PROPERTY_UPC_E_TWO_CHAR_ADDENDA_ENABLED", "DEC_UPCE_2CHAR_ADDENDA_ENABLED");
//                        properties.put("PROPERTY_UPC_E_FIVE_CHAR_ADDENDA_ENABLED", "DEC_UPCE_5CHAR_ADDENDA_ENABLED");
//                        properties.put("PROPERTY_UPC_E_ADDENDA_REQUIRED_ENABLED", "DEC_UPCE_ADDENDA_REQUIRED");
//                        properties.put("PROPERTY_UPC_E_ADDENDA_SEPARATOR_ENABLED", "DEC_UPCE_ADDENDA_SEPARATOR");
                        properties.put("PROPERTY_EAN_8_ENABLED", "DEC_EAN8_ENABLED");
                        properties.put("PROPERTY_EAN_8_CHECK_DIGIT_TRANSMIT_ENABLED", "DEC_EAN8_CHECK_DIGIT_TRANSMIT");
//                        properties.put("PROPERTY_EAN_8_TWO_CHAR_ADDENDA_ENABLED", "DEC_EAN8_2CHAR_ADDENDA_ENABLED");
//                        properties.put("PROPERTY_EAN_8_FIVE_CHAR_ADDENDA_ENABLED", "DEC_EAN8_5CHAR_ADDENDA_ENABLED");
//                        properties.put("PROPERTY_EAN_8_ADDENDA_REQUIRED_ENABLED", "DEC_EAN8_ADDENDA_REQUIRED");
//                        properties.put("PROPERTY_EAN_8_ADDENDA_SEPARATOR_ENABLED", "DEC_EAN8_ADDENDA_SEPARATOR");
                        properties.put("PROPERTY_EAN_13_ENABLED", "DEC_EAN13_ENABLED");
                        properties.put("PROPERTY_EAN_13_CHECK_DIGIT_TRANSMIT_ENABLED", "DEC_EAN13_CHECK_DIGIT_TRANSMIT");
//                        properties.put("PROPERTY_EAN_13_TWO_CHAR_ADDENDA_ENABLED", "DEC_EAN13_2CHAR_ADDENDA_ENABLED");
//                        properties.put("PROPERTY_EAN_13_FIVE_CHAR_ADDENDA_ENABLED", "DEC_EAN13_5CHAR_ADDENDA_ENABLED");
//                        properties.put("PROPERTY_EAN_13_ADDENDA_REQUIRED_ENABLED", "DEC_EAN13_ADDENDA_REQUIRED");
//                        properties.put("PROPERTY_EAN_13_ADDENDA_SEPARATOR_ENABLED", "DEC_EAN13_ADDENDA_SEPARATOR");
                        properties.put("PROPERTY_CODABAR_CHECK_DIGIT_MODE", "DEC_CODABAR_CHECK_DIGIT_MODE");
                        properties.put("CODABAR_CHECK_DIGIT_MODE_NO_CHECK", "noCheck");
                        properties.put("CODABAR_CHECK_DIGIT_MODE_CHECK", "check");
                        properties.put("CODABAR_CHECK_DIGIT_MODE_CHECK_AND_STRIP", "checkAndStrip");
                        properties.put("PROPERTY_CODE_11_CHECK_DIGIT_MODE", "DEC_CODE11_CHECK_DIGIT_MODE");
                        properties.put("CODE_11_CHECK_DIGIT_MODE_DOUBLE_DIGIT_CHECK", "doubleDigitCheck");
                        properties.put("CODE_11_CHECK_DIGIT_MODE_SINGLE_DIGIT_CHECK", "singleDigitCheck");
                        properties.put("CODE_11_CHECK_DIGIT_MODE_DOUBLE_DIGIT_CHECK_AND_STRIP", "doubleDigitCheckAndStrip");
                        properties.put("CODE_11_CHECK_DIGIT_MODE_SINGLE_DIGIT_CHECK_AND_STRIP", "singleDigitCheckAndStrip");
//                        properties.put("PROPERTY_INTERLEAVED_25_CHECK_DIGIT_MODE", "DEC_I25_CHECK_DIGIT_MODE");
//                        properties.put("INTERLEAVED_25_CHECK_DIGIT_MODE_NO_CHECK", "noCheck");
//                        properties.put("INTERLEAVED_25_CHECK_DIGIT_MODE_CHECK", "check");
//                        properties.put("INTERLEAVED_25_CHECK_DIGIT_MODE_CHECK_AND_STRIP", "checkAndStrip");
//                        properties.put("PROPERTY_POSTAL_2D_MODE", "DEC_POSTAL_ENABLED");
//                        properties.put("PROPERTY_POSTAL_2D_POSTNET_CHECK_DIGIT_TRANSMIT_ENABLED", "DEC_POSTNET_CHECK_DIGIT_TRANSMIT");
//                        properties.put("PROPERTY_POSTAL_2D_PLANET_CHECK_DIGIT_TRANSMIT_ENABLED", "DEC_PLANETCODE_CHECK_DIGIT_TRANSMIT");

                        barcodeReader.setProperties(properties);
                        barcodeReader.claim();
                    } catch (ScannerUnavailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext)
    throws JSONException {
        if (action.equals("softwareTriggerStart")) {
            if (barcodeReader != null) {
                try {
                    barcodeReader.softwareTrigger(true);
                } catch (ScannerNotClaimedException e) {
                    e.printStackTrace();
                    NotifyError("ScannerNotClaimedException");
                } catch (ScannerUnavailableException e) {
                    e.printStackTrace();
                 NotifyError("ScannerUnavailableException");
                }
            }
        } else if (action.equals("softwareTriggerStop")) {
            if (barcodeReader != null) {
                try {
                    barcodeReader.softwareTrigger(false);
                } catch (ScannerNotClaimedException e) {
                    e.printStackTrace();
                    NotifyError("ScannerNotClaimedException");
                } catch (ScannerUnavailableException e) {
                    e.printStackTrace();
                 NotifyError("ScannerUnavailableException");
                }
            }
        } else if (action.equals("listen") ) {
            this.callbackContext = callbackContext;
            PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
            result.setKeepCallback(true);
            this.callbackContext.sendPluginResult(result);
            if (barcodeReader != null) {
                try {
                   barcodeReader.softwareTrigger(false);
                } catch (ScannerNotClaimedException e) {
                    e.printStackTrace();
                    NotifyError("ScannerNotClaimedException2");
                } catch (ScannerUnavailableException e) {
                    e.printStackTrace();
                     NotifyError("ScannerUnavailableException2");
                }
            }
        } else if (action.equals("claim")) {
            if (barcodeReader != null) {
                try {
                    barcodeReader.claim();
                } catch (ScannerUnavailableException e) {
                    e.printStackTrace();
                    NotifyError("Scanner unavailable");
                }
            }
            if (barcodeReader != null) {
                try {
                   barcodeReader.softwareTrigger(false);
                } catch (ScannerNotClaimedException e) {
                    e.printStackTrace();
                    NotifyError("ScannerNotClaimedException2");
                } catch (ScannerUnavailableException e) {
                    e.printStackTrace();
                     NotifyError("ScannerUnavailableException2");
                }
            }
        } else if (action.equals("release")) {
            if (barcodeReader != null) {
                barcodeReader.release();
            }
            if (barcodeReader != null) {
                try {
                   barcodeReader.softwareTrigger(false);
                } catch (ScannerNotClaimedException e) {
                    e.printStackTrace();
                    NotifyError("ScannerNotClaimedException2");
                } catch (ScannerUnavailableException e) {
                    e.printStackTrace();
                     NotifyError("ScannerUnavailableException2");
                }
            }
         }
        return true;
    }

    @Override
    public void onBarcodeEvent(BarcodeReadEvent barcodeReadEvent) {
        if (this.callbackContext != null) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, barcodeReadEvent.getBarcodeData());
            result.setKeepCallback(true);
            this.callbackContext.sendPluginResult(result);
        }
        if (barcodeReader != null) {
            try {
                barcodeReader.softwareTrigger(false);
            } catch (ScannerNotClaimedException e) {
                e.printStackTrace();
                NotifyError("ScannerNotClaimedException2");
            } catch (ScannerUnavailableException e) {
                e.printStackTrace();
                    NotifyError("ScannerUnavailableException2");
            }
        }
    }

    @Override
    public void onFailureEvent(BarcodeFailureEvent barcodeFailureEvent) {
        NotifyError("Scan has failed");
        if (barcodeReader != null) {
            try {
                barcodeReader.softwareTrigger(false);
            } catch (ScannerNotClaimedException e) {
                e.printStackTrace();
                NotifyError("ScannerNotClaimedException2");
            } catch (ScannerUnavailableException e) {
                e.printStackTrace();
                    NotifyError("ScannerUnavailableException2");
            }
        }
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        if (barcodeReader != null) {
            try {
                barcodeReader.claim();
            } catch (ScannerUnavailableException e) {
                e.printStackTrace();
                NotifyError("The scanner is unavailable");
            }
        }
        if (barcodeReader != null) {
            try {
                barcodeReader.softwareTrigger(false);
            } catch (ScannerNotClaimedException e) {
                e.printStackTrace();
                NotifyError("ScannerNotClaimedException2");
            } catch (ScannerUnavailableException e) {
                e.printStackTrace();
                    NotifyError("ScannerUnavailableException2");
            }
        }
    }

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        if (barcodeReader != null) {
            barcodeReader.release();
        }
        if (barcodeReader != null) {
            try {
                barcodeReader.softwareTrigger(false);
            } catch (ScannerNotClaimedException e) {
                e.printStackTrace();
                NotifyError("ScannerNotClaimedException2");
            } catch (ScannerUnavailableException e) {
                e.printStackTrace();
                    NotifyError("ScannerUnavailableException2");
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (barcodeReader != null) {
            barcodeReader.close();
            barcodeReader = null;
        }

        if (manager != null) {
            manager.close();
        }
    }

    private void NotifyError(String error) {
        if (this.callbackContext != null) {
            PluginResult result = new PluginResult(PluginResult.Status.ERROR, error);
            result.setKeepCallback(true);
            this.callbackContext.sendPluginResult(result);
        }
    }
}