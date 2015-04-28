package com.demo.demoservercommunication;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeeshan on 4/28/2015.
 */

// Implementation References:
// https://developer.android.com/training/volley/requestqueue.html
// http://arnab.ch/blog/2013/08/asynchronous-http-requests-in-android-using-volley/
public class HttpRequestHandler {


    private static final String TAG = "HttpRequestHandler";
    private static HttpRequestHandler httpRequestHandler;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private HttpRequestHandler(){
    }

    public static void setApplicationContext(Context context){
        mContext = context;
    }

    public static HttpRequestHandler getInstance()throws RuntimeException{
        if(mContext==null){
            throw new RuntimeException("Context is not set, call HttpRequestHandler.setApplicationContext(context) first");
        }
        if(httpRequestHandler==null){
            httpRequestHandler = new HttpRequestHandler();
        }
        return httpRequestHandler;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    public void get(String url){
        JSONObject j = new JSONObject();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        //pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                //pDialog.hide();
            }
        });

        // Adding request to request queue
        this.addToRequestQueue(jsonObjReq, TAG);
    }

    public void post(String url){
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "Androidhive");
        params.put("age", "5");


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        Student st = gson.fromJson(response.toString(),Student.class);
                        Log.d(TAG, st.getName());
                        Log.d(TAG, ""+st.getAge());
                        //pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                //pDialog.hide();
            }
        });

        // Adding request to request queue
        this.addToRequestQueue(jsonObjReq, TAG);

    }


    public void postWithObject(String url)throws JSONException{
        Student st = new Student("Aijaz",44);
        Gson gson = new Gson();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(gson.toJson(st)),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        Student st = gson.fromJson(response.toString(),Student.class);
                        Log.d(TAG, st.getName());
                        Log.d(TAG, ""+st.getAge());
                        //pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                //pDialog.hide();
            }
        });

        // Adding request to request queue
        this.addToRequestQueue(jsonObjReq, TAG);

    }


}
