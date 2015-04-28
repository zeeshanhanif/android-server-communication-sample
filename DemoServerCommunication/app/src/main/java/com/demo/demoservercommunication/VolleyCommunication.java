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

import java.io.IOException;


public class VolleyCommunication extends ActionBarActivity {

    private TextView responseView;
    private static final String TAG = "VolleyCommunication";
    private Button button1;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_communication);
        responseView = (TextView)findViewById(R.id.responseView);
        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPostBackgroundTask myPostTask = new MyPostBackgroundTask();
                myPostTask.execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_volley_communication, menu);
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


    private class MyPostBackgroundTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                return getData();
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
            responseView.setText(item);
        }
    }

    public String getData() throws IOException {

        HttpRequestHandler h = HttpRequestHandler.getInstance();

        h.get("http://10.0.2.2:3000/users/data");


        return null;

    }

    public String postData() throws IOException {

        HttpRequestHandler h = HttpRequestHandler.getInstance();

        h.get("http://10.0.2.2:3000/users/data");


        return null;

    }
}
