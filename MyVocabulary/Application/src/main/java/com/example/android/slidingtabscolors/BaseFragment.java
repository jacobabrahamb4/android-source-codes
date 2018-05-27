/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.slidingtabscolors;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class BaseFragment extends Fragment {

    private static final String APP_ID = "c17de1ce";
    private static final String APP_KEY = "3285eb7329889cab49ba819ebb431aca";
    private static final String LANG = "en";

    protected static final String KEY_TITLE = "title";
    protected static final String KEY_INDICATOR_COLOR = "indicator_color";
    protected static final String KEY_DIVIDER_COLOR = "divider_color";

    protected String dictionaryEntries(String query) {
        final String word_id = query.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + LANG + "/" + word_id;
    }

    //in android calling network requests on the main thread forbidden by default
    //create class to do async job
    protected static class CallbackTask extends AsyncTask<String, Integer, String> {

        public interface QuerryCallback {
            void publishDefinitions(ArrayList<Definition> definitions);
        }

        private QuerryCallback mCallback;

        public CallbackTask(QuerryCallback callback) {
            mCallback = callback;
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("app_id", APP_ID);
                urlConnection.setRequestProperty("app_key", APP_KEY);

                // read the output from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                return stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            SearchResult searchResult = gson.fromJson(result, SearchResult.class);
            ArrayList<Definition> definitions = searchResult.getDefinitions();
            if(mCallback != null) {
                mCallback.publishDefinitions(definitions);
            }
        }
    }
}
