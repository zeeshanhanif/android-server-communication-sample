package com.demo.demoservercommunication;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "TAG";
    private TextView responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseData = (TextView)findViewById(R.id.postResponse);

        Button getReq = (Button)findViewById(R.id.getRequest);
        getReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_SHORT)
                        .show();
                MyBackgroundTask mytask = new MyBackgroundTask();
                mytask.execute();
                //MyPostBackgroundTask myPostTask = new MyPostBackgroundTask();
                //myPostTask.execute();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public byte[] getUrlBytes(String urlSpec) throws IOException {

        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];

            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();

        } finally {
            connection.disconnect();
        }
    }

    public String postData() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://10.0.2.2:3000/users");

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("key1", "value1"));
        pairs.add(new BasicNameValuePair("key2", "value2"));
        post.setEntity(new UrlEncodedFormEntity(pairs));

        HttpResponse response = client.execute(post);

        String result = EntityUtils.toString(response.getEntity());
        Log.d(TAG,"After Post request Data = "+ result );
        return result;

    }

    private class MyBackgroundTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {

            try {
                String result = getUrl("http://192.168.1.27:3000/users");
                Log.d(TAG, "Result of Get URL = "+result);
                //JSON.par
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String item) {
            Gson gson = new Gson();
            //Student user = gson.fromJson(item,Student.class);
            Student []user =  gson.fromJson(item,Student[].class);
            //responseData.setTextuser.getName());
            Log.d(TAG, "After Execution Result of Get URL = "+item);
        }
    }

    private class MyPostBackgroundTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {

            try {
                return postData();
                //Log.d(TAG, "Result of Get URL = "+result);
                //return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String item) {
            Log.d(TAG, "Post After Execution Result of Get URL = " + item);
            responseData.setText(item);
        }


    }


    private class MyBackgroundHttpTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {

            try {
                return postData();
                //Log.d(TAG, "Result of Get URL = "+result);
                //return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String item) {
            Log.d(TAG, "Post After Execution Result of Get URL = " + item);
            responseData.setText(item);
        }
    }


    private String httpPostCall() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://10.0.2.2:3000/users");

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("key1", "value1"));
        pairs.add(new BasicNameValuePair("key2", "value2"));
        post.setEntity(new UrlEncodedFormEntity(pairs));

        HttpResponse response = client.execute(post);

        String result = EntityUtils.toString(response.getEntity());
        Log.d(TAG,"After Post request Data = "+ result );
        return result;

    }

}
