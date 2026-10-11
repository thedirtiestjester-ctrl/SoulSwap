package com.soulswap.game;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView webView;
    private ValueCallback<Uri[]> fileChooserCallback;
    private static final int PICK_JSON = 102;
    private static final int CREATE_SAVE = 103;
    private String pendingSaveBackup;

    @Override public void onCreate(Bundle saved) {
        super.onCreate(saved);
        getWindow().setStatusBarColor(0xff080d1b);
        getWindow().setNavigationBarColor(0xff10182b);
        webView = new WebView(this);
        webView.setBackgroundColor(0xff080d1b);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDefaultTextEncodingName("UTF-8");
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface public void exportBackup(String json) {
                if (json == null || json.length() > 80000000) return;
                runOnUiThread(() -> {
                    pendingSaveBackup = json;
                    Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                    intent.setType("application/json");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    String stamp = new SimpleDateFormat("yyyyMMdd-HHmm", Locale.US).format(new Date());
                    intent.putExtra(Intent.EXTRA_TITLE, "SoulSwap_Save_" + stamp + ".json");
                    try { startActivityForResult(intent, CREATE_SAVE); }
                    catch (Exception e) { pendingSaveBackup = null; }
                });
            }
        }, "SoulSwapBridge");
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onShowFileChooser(WebView view,
                     ValueCallback<Uri[]> callback, FileChooserParams params) {
                if (fileChooserCallback != null) fileChooserCallback.onReceiveValue(null);
                fileChooserCallback = callback;
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/json");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"application/json", "text/plain", "application/octet-stream"});
                    startActivityForResult(Intent.createChooser(intent,"Import SoulSwap card pack"), PICK_JSON);
                } catch (Exception e) {
                    fileChooserCallback.onReceiveValue(null);
                    fileChooserCallback = null;
                }
                return true;
            }
        });
        setContentView(webView);
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_SAVE) {
            if (resultCode == RESULT_OK && data != null && data.getData() != null && pendingSaveBackup != null) {
                try (OutputStream stream = getContentResolver().openOutputStream(data.getData())) {
                    if (stream != null) stream.write(pendingSaveBackup.getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) { android.util.Log.e("SoulSwap", "Save export failed", e); }
            }
            pendingSaveBackup = null;
            return;
        }
        if (requestCode == PICK_JSON && fileChooserCallback != null) {
            Uri[] uris = (resultCode == RESULT_OK && data != null && data.getData() != null)
                    ? new Uri[]{data.getData()} : null;
            fileChooserCallback.onReceiveValue(uris);
            fileChooserCallback = null;
        }
    }

    @Override public void onBackPressed() {
        if (webView != null) {
            webView.evaluateJavascript("(function(){if(document.querySelector('#details.screen.active')){document.querySelector('#backCollection').click();return true;}if(!document.querySelector('#collection.screen.active')){document.querySelector('[data-go=collection]').click();return true;}return false;})()", value -> {
                if ("false".equals(value)) MainActivity.super.onBackPressed();
            });
        } else super.onBackPressed();
    }

    @Override public void onDestroy() {
        if (fileChooserCallback != null) {fileChooserCallback.onReceiveValue(null);fileChooserCallback=null;}
        if (webView != null) {webView.destroy();webView=null;}
        super.onDestroy();
    }
}
