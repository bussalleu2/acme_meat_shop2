package com.bussalleu.acme2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by alba on 5/2/17.
 */

public class ACMEAsyncTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;
        Callback callback;
        Context context;
        public ACMEAsyncTask( Context context,Callback callback){
            this.callback=callback;
            this.context=context;
        }
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(context, context.getString(R.string.Loading), context.getString(R.string.Please_wait), true);
        }

        protected String doInBackground(String... params) {
            String stream = null;
            String urlString = params[0];

            try{
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Check the connection status
                if(urlConnection.getResponseCode() == 200)
                {
                    // if response code = 200 ok
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    // Read the BufferedInputStream
                    BufferedReader r = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        sb.append(line);
                    }
                    stream = sb.toString();

                    urlConnection.disconnect();
                }
                else
                {
                    // Do something
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }finally {

            }
            return stream;


        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            callback.onResult(result);
            dialog.dismiss();
        }
    }

