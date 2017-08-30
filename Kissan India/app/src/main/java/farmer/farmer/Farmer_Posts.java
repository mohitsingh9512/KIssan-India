package farmer.farmer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Ankit Kumar on 22-07-2016.
 */
@SuppressLint("SetJavaScriptEnabled")
public class Farmer_Posts extends AppCompatActivity {
    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Adds Progress Bar Support
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        // Makes Progress Bar Visible
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        // Use farmer_post.xml as webview layout
        setContentView(R.layout.farmer_posts);
        this.setTitle("Forum");
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        // Adds Zoom Control (You may not need this)
        webView.getSettings().setSupportZoom(true);

        // Enables Multi-Touch. if supported by ROM
        webView.getSettings().setBuiltInZoomControls(true);

        //forum url
        webView.loadUrl("http://www.kisaanindia.createaforum.com");

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Loads only your forum domain and no others!
                if(url.contains("kisaanindia.createaforum.com") == true) {
                    view.loadUrl(url);
                    // Adds Progress Bar Support
                    super.onPageStarted(view, url, null);
                    findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
                    // If they are not your domain, use browser instead
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                // Removes Progress Bar
                findViewById(R.id.progressbar).setVisibility(View.GONE);
                // Adds Cookies.
                CookieSyncManager.getInstance().sync();
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Enables going back history
        if (webView.copyBackForwardList().getCurrentIndex() > 0) {
            webView.goBack();
        }
        else {
            // Your exit alert code, or alternatively line below to finish
            // Finishes forum activity
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return true;
            default:
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.

                int id = item.getItemId();

                if (id==R.id.action_settings) {
                    Intent intent = new Intent(Farmer_Posts.this, SettingsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                    return true;
                }

                else if (id == R.id.action_share_feedback) {
                    Intent intent = new Intent(Farmer_Posts.this, Action_Feedback.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                    return true;
                }

                else if (id == R.id.action_share) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Now, you can download Kisaan India app on google play store";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kisaan India");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    return true;
                }

                return super.onOptionsItemSelected(item);
        }
    }

}



















