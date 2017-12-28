package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import java.io.IOException;

import android.os.AsyncTask;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;




import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

/**
 * Custom AsyncTask class to call the server to get the joke as response.
 * Currently local server ip is configured.
 */

public class EndpointAsyncTask extends AsyncTask<Object, Void, String> {

    private static JokeApi jokeApiService = null;
    private EndpointTaskCompletionHandler mHandler;

    @Override
    protected String doInBackground(Object... params) {

        mHandler = (EndpointTaskCompletionHandler) params[0];

        if (jokeApiService == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            jokeApiService=builder.build();
        }
        try {
            return jokeApiService.findJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mHandler.onJokeReceived(s);
    }


    public interface EndpointTaskCompletionHandler {
        void onJokeReceived(String s);
    }
}
