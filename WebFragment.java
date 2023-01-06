package com.yazapps.mepowerbills;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.yazapps.mepowerbills.WebActivity.searchChar;

public class WebFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_web, container, false);
        WebView webView = view.findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());

        switch (searchChar) {

            case 'D':
                webView.loadUrl("http://www.hepco.co.jp/home/price/system/system.html");
                break;

            case 'L':
                webView.loadUrl("http://www.hepco.co.jp/home/price/recyclable_promotion/system02.html");
                break;
        }
        return view;
    }
}
