package com.prideven.android.hungryeats;

import android.content.Context;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.location.Address;
import android.os.Handler;



public class GetLocationDetails {

        private static final String TAG = "GeocodingLocation";



    public static void getAddressFromLocation(final String locationAddress,
                                                  final Context context, final Handler handler) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    String result = null;
                    try {
                        List addressList = geocoder.getFromLocationName(locationAddress, 1);
                        if (addressList != null && addressList.size() > 0) {
                            Address address = (Address) addressList.get(0);
                            StringBuilder sb = new StringBuilder();
                            double lat = address.getLatitude();
                            double lng = address.getLatitude();
                            sb.append(address.getLatitude()).append("\n");
                            sb.append(address.getLongitude()).append("\n");
                            result = sb.toString();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Unable to connect to Geocoder", e);
                    } finally {
                        Message message = Message.obtain();
                        message.setTarget(handler);
                        if (result != null) {
                            message.what = 1;
                            Bundle bundle = new Bundle();
                            result = "Address: " + locationAddress +
                                    "\n\nLatitude and Longitude :\n" + result;
                            bundle.putString("address", result);
                            message.setData(bundle);


                        } else {
                            message.what = 1;
                            Bundle bundle = new Bundle();
                            result = "Address: " + locationAddress +
                                    "\n Unable to get Latitude and Longitude for this address location.";
                            bundle.putString("address", result);
                            message.setData(bundle);
                        }
                        message.sendToTarget();
                    }
                }
            };
            thread.start();
        }
    }



