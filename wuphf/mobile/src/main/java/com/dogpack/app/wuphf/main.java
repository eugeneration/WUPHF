package com.dogpack.app.wuphf;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // send the message
    public void sendMessage(View view) {
        Toast toast = Toast.makeText(this, "Message Sent!", Toast.LENGTH_SHORT);
        toast.show();

        PostDataTask poster = new PostDataTask();

        //=============================
        // YO
        PostData yoData = new PostData("https://api.justyo.co/yo/");
        yoData.params.add(new BasicNameValuePair("api_token", "a1001602-9b08-4783-89c6-c86f700590ec"));
        yoData.params.add(new BasicNameValuePair("username", "HIPSTERVY"));
        poster.execute(yoData);
    }

    /**
     * Instructions on how to send an HTTP push:
     * Create a PostData object with the APIs URL
     * then add parameters as seen in the examples above: ("[parameter name]", "[data]")
     * Then run "poster.execute(yourData);" using yourData.
     */
    private class PostDataTask extends AsyncTask<PostData, Void, HttpResponse> {
        protected HttpResponse doInBackground(PostData... dataList) {
            PostData data = dataList[0];

            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(data.url);
            try {
                httppost.setEntity(new UrlEncodedFormEntity(data.params));
                HttpResponse response = httpclient.execute(httppost);
                return response;
            } catch (ClientProtocolException e) {
            } catch (IOException e) {
            }
            return null;
        }
        protected void onPostExecute(HttpResponse response) {
            Log.v("HTTP", response.toString());
        }

    }
    private class PostData {
        public String url;
        public List<NameValuePair> params;
        public PostData (String url) {
            this.url = url;
            params = new ArrayList<NameValuePair>();
        }
    }
}
