package in.vbgramg.forms;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends Activity {
    // *** फक्त हे एकच बदला: तुमच्या GitHub Pages ची लिंक (शेवटी / असावा) ***
    static final String BASE_URL = "https://YOUR_GITHUB_USERNAME.github.io/YOUR_REPO/";

    WebView web;
    ValueCallback<Uri[]> fileCb;

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        web = new WebView(this);
        setContentView(web);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(false);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);
        web.addJavascriptInterface(new Bridge(), "AndroidBridge");
        web.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) {
                if (r.getUrl().toString().startsWith(BASE_URL)) return false;
                startActivity(new Intent(Intent.ACTION_VIEW, r.getUrl()));
                return true;
            }
        });
        web.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onShowFileChooser(WebView w, ValueCallback<Uri[]> cb, FileChooserParams p) {
                if (fileCb != null) fileCb.onReceiveValue(null);
                fileCb = cb;
                try { startActivityForResult(p.createIntent(), 1); }
                catch (Exception e) { fileCb = null; return false; }
                return true;
            }
        });
        web.loadUrl(BASE_URL);
    }

    @Override protected void onActivityResult(int rq, int rs, Intent d) {
        if (rq == 1 && fileCb != null) {
            fileCb.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(rs, d));
            fileCb = null;
        } else super.onActivityResult(rq, rs, d);
    }

    @Override public void onBackPressed() {
        if (web.canGoBack()) web.goBack(); else super.onBackPressed();
    }

    class Bridge {
        @JavascriptInterface public void savePdf(final String name, final String b64) {
            runOnUiThread(() -> {
                try {
                    byte[] data = Base64.decode(b64, Base64.DEFAULT);
                    ContentValues cv = new ContentValues();
                    cv.put(MediaStore.Downloads.DISPLAY_NAME, name);
                    cv.put(MediaStore.Downloads.MIME_TYPE, name.endsWith(".json") ? "application/json" : "application/pdf");
                    cv.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);
                    Uri u = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, cv);
                    try (OutputStream os = getContentResolver().openOutputStream(u)) { os.write(data); }
                    Toast.makeText(MainActivity.this, "Downloads मध्ये जतन केले: " + name, Toast.LENGTH_LONG).show();
                    if (name.endsWith(".pdf")) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setDataAndType(u, "application/pdf");
                        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        try { startActivity(i); } catch (Exception ignored) {}
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "जतन अयशस्वी: " + e, Toast.LENGTH_LONG).show();
                }
            });
        }

        @JavascriptInterface public void printPdf(final String name, final String b64) {
            final byte[] data = Base64.decode(b64, Base64.DEFAULT);
            runOnUiThread(() -> {
                PrintManager pm = (PrintManager) getSystemService(PRINT_SERVICE);
                pm.print(name, new PdfAdapter(data, name), null);
            });
        }
    }

    static class PdfAdapter extends PrintDocumentAdapter {
        final byte[] d; final String n;
        PdfAdapter(byte[] d, String n) { this.d = d; this.n = n; }
        @Override public void onLayout(PrintAttributes o, PrintAttributes nw, CancellationSignal c, LayoutResultCallback cb, Bundle e) {
            if (c.isCanceled()) { cb.onLayoutCancelled(); return; }
            cb.onLayoutFinished(new PrintDocumentInfo.Builder(n)
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .setPageCount(PrintDocumentInfo.PAGE_COUNT_UNKNOWN).build(), true);
        }
        @Override public void onWrite(PageRange[] p, ParcelFileDescriptor dest, CancellationSignal c, WriteResultCallback cb) {
            try (OutputStream os = new FileOutputStream(dest.getFileDescriptor())) {
                os.write(d);
                cb.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
            } catch (IOException ex) { cb.onWriteFailed(ex.toString()); }
        }
    }
}
