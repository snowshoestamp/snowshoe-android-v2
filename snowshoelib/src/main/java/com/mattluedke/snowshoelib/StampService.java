package com.mattluedke.snowshoelib;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class StampService {

    private static RequestQueue requestQueue = null;
    private static final String API_URL = "https://ss-dev-api-stamp.azurewebsites.net/v3/stamp";
    private final Context context;
    private final String mApiKey;
    private final OnStampListener mOnStampListener;

    public StampService(Context context, String apiKey, OnStampListener onStampListener) {
        this.context = context;
        mApiKey = apiKey;
        mOnStampListener = onStampListener;
    }

    private static RequestQueue getRequestQueue(Context context) {

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    private <T> void executeGeneralRequest(GsonRequest<T> gsonRequest) {
        getRequestQueue(context).add(gsonRequest);
    }

    // ### GET STAMP DATA BY TOUCH POINTS SERVICE ###
    public void getStampByTouchPoints(String touchPoints) {

        String params = "data="+touchPoints;

        GsonRequest<StampResult> gsonRequest = new GsonRequest<>(API_URL, StampResult.class, mApiKey, params, didGetStampResult(), failedToGetStampResult());

        executeGeneralRequest(gsonRequest);
        mOnStampListener.onStampRequestMade();
    }

    private Response.Listener<StampResult> didGetStampResult() {
        return new Response.Listener<StampResult>() {
            @Override
            public void onResponse(StampResult response) {
                mOnStampListener.onStampResult(response);
            }
        };
    }

    private Response.ErrorListener failedToGetStampResult() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mOnStampListener.onStampResult(null);

                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        //try to log debug info about the error.
                        JSONObject obj = new JSONObject(res);
                        Log.e("RESPONSE ERROR", obj.toString());
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        };
    }
    // ### END GET STAMP DATA BY TOUCH POINTS SERVICE ###
}
