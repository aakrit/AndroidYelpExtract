//package edu.uchicago.teamyelp;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.Button;
//import android.widget.TextView;
////
////import org.jsoup.Jsoup;
////import org.jsoup.nodes.Document;
////import org.jsoup.select.Elements;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//
//import edu.uchicago.teamyelp.R;
//import edu.uchicago.teamyelp.StartSearchActivity;
//
//public class JsoupResultActivity extends Activity {
//
//    private static final String TAG = "JSOUPResultActivity";
//
//    private TextView mRestaurantNameTextView, mRestaurantAddressTextView, mRestaurantPhoneTextView;
//    private Button mbackButton;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate() called");
//        setContentView(R.layout.activity_yelp_result);
//
//        mRestaurantNameTextView = (TextView) findViewById(R.id.yelp_list_item_restaurantNameTextView);
//        mRestaurantAddressTextView = (TextView) findViewById(R.id.yelp_list_item_restaurantAddressTextView);
//        mRestaurantPhoneTextView = (TextView) findViewById(R.id.yelp_list_item_restaurantPhoneTextView);
//        mbackButton = (Button) findViewById(R.id.backButton);
//
//        String URL = (
//                ("http://www.google.com/search?hl=en&btnI=1&q=") +
//                        ((String) getIntent().getExtras().get("NAME")).replaceAll(" ", "+") + "+" +
//                        ((String) getIntent().getExtras().get("LOCATION")).replaceAll(" ", "+") +
//                        ("+site:yelp.com"));
//
//        new JsoupSearchTask().execute(URL);
//
//        mbackButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                back();
//            }
//        });
//
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//
//    }
//
//    //well done
//    private class JsoupSearchTask extends AsyncTask<String, Void, ArrayList<String>> {
//
//        @Override
//        protected ArrayList<String> doInBackground(String... strings) {
//            String URL = strings[0];
//            ArrayList<String> result = new ArrayList<String>();
//            try {
//                Document doc = Jsoup.connect(URL).userAgent("Mozilla").get();
//                Element resName = doc.select("h1[itemprop=name]");
//                Element resAddress = doc.select("address[itemprop=address]");
//                Element resPhone = doc.select("span[itemprop=telephone]");
//                result.add(resName.text());
//                result.add(resAddress.text());
//                result.add(resPhone.text());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<String> strings) {
//            mRestaurantNameTextView.setText(strings.get(0));
//            mRestaurantAddressTextView.setText(strings.get(1));
//            mRestaurantPhoneTextView.setText(strings.get(2));
//        }
//    }
//
//    private void back() {
////        Intent intent = new Intent(this, StartSearchActivity.class);
////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////        startActivity(intent);
//        finish();
//    }
//
//}
