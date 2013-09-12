package edu.uchicago.teamyelp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.lang.annotation.Annotation;

public class YelpWebViewScrapeResultActivity extends Activity {
    private static final String GIRL_AND_GOAT_URL = "http://www.yelp.com/biz/girl-and-the-goat-chicago";
    public static final String HTML_AMP = "&amp;";
    public static final String PLAIN_AMP = "&";

    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp_result);

        WebView scraper = new WebView(this);
        scraper.getSettings().setJavaScriptEnabled(true);
        scraper.addJavascriptInterface(new JavascriptInterface() {

            public void setInfo(final String name, final String phone, final String address) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        YelpWebViewScrapeResultActivity.this.setName(name.replace(HTML_AMP, PLAIN_AMP));
                        YelpWebViewScrapeResultActivity.this.setPhone(phone);
                        YelpWebViewScrapeResultActivity.this.setAddress(address);
                        YelpWebViewScrapeResultActivity.this.setBackButton();
                    }
                });
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }
        }, "BusinessInfo");


        //this is blocking the UI thread. That's why it's crashing.
        // if you could implement this like JSoup was done with an AsyncTask then it'll work.
        // No one is going to use scraping. let's just omit it as an option.
        scraper.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.BusinessInfo.setInfo(document.getElementsByTagName('h1')[0].innerHTML," +
                        "document.getElementsByClassName('phone-number')[0].innerHTML," +
                        "document.getElementsByTagName('span')[15].innerHTML+'\\n'+" +
                        "document.getElementsByTagName('span')[16].innerHTML+', '+" +
                        "document.getElementsByTagName('span')[17].innerHTML+' '+" +
                        "document.getElementsByTagName('span')[18].innerHTML);");
            }
        });
        scraper.loadUrl(GIRL_AND_GOAT_URL);

    }

    public void setName(String name) {
        TextView nameView = (TextView) YelpWebViewScrapeResultActivity.this.findViewById(R.id.yelp_list_item_restaurantNameTextView);
        nameView.setText(name);
    }

    public void setPhone(String phone) {
        TextView phoneView = (TextView) YelpWebViewScrapeResultActivity.this.findViewById(R.id.yelp_list_item_restaurantPhoneTextView);
        phoneView.setText(phone);
    }

    public void setAddress(String address) {
        TextView addressView = (TextView) YelpWebViewScrapeResultActivity.this.findViewById(R.id.yelp_list_item_restaurantAddressTextView);
        addressView.setText(address);
    }

    private void setBackButton() {
        Button mbackButton = (Button) YelpWebViewScrapeResultActivity.this.findViewById(R.id.backButton);
        mbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(YelpWebViewScrapeResultActivity.this, StartSearchActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

            }
        });

    }
}