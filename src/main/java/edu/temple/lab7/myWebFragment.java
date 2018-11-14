package edu.temple.lab7;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class myWebFragment extends Fragment {

    String newestURL = "";

    WebView display;


    public myWebFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_web, container, false);
    }

    public void setUrlText(String urlSent) {
        newestURL = urlSent;
    }

    public String getUrlText() {
        return newestURL;
    }
    
    public void goToUrl() {
        WebView webView = getView().findViewById(R.id.Display);
        webView.setWebViewClient(new WebViewClient());
        EditText mainActivityUrlText = getActivity().findViewById(R.id.urlEditText);
        mainActivityUrlText.setText(newestURL, TextView.BufferType.EDITABLE);
        //webView.loadUrl(newestURL);

        webView.getSettings().setJavaScriptEnabled(true);
        if(newestURL.contains("https://")) {
            webView.loadUrl((newestURL));
        }
        else {
            newestURL = ("https://" + newestURL);
            webView.loadUrl(newestURL);
        }
}
}


