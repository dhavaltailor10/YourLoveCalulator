package androidlocationmaps.app.com.lovecalculator;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class SecondAct extends AppCompatActivity {

    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    private Button btncheck;
    private Button btnshare;
    private EditText edtyourname;
    private EditText edtyourlovename;
    private TextView txtdis;
    private ImageView imgheart;
    private RelativeLayout ll;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_second);
        getSupportActionBar().hide();

        mediaPlayer = MediaPlayer.create(SecondAct.this, R.raw.lovetheme);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(60, 60);


        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);
        tracker = analytics.newTracker("UA-52496887-9");
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);
        tracker.enableExceptionReporting(true);

        ll = (RelativeLayout) findViewById(R.id.llMainCordinator);

        AdView adView = (AdView) findViewById(R.id.adView); //add the cast
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        Typeface font1 = Typeface.createFromAsset(getAssets(), "911Fonts.com_LucidaHandwritingItalic__-_911fonts.com-fonts-wAGX.ttf");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "Nexa_Light.otf");
        TextView txtlucida = (TextView) findViewById(R.id.textView3);
        TextView txtyname = (TextView) findViewById(R.id.textView);
        TextView txtlname = (TextView) findViewById(R.id.textView2);
        txtlucida.setTypeface(font1);
        txtyname.setTypeface(font2);
        txtlname.setTypeface(font2);

        txtlucida.setTextSize(getResources().getDimension(R.dimen.txtsize));
        txtlucida.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        txtdis = (TextView) findViewById(R.id.txtdisplay);
        edtyourname = (EditText) findViewById(R.id.edtname);
        edtyourlovename = (EditText) findViewById(R.id.edtlovename);

        String removeedtname = edtyourlovename.getText().toString();
        removeedtname = removeedtname.replace("", " ");

        btncheck = (Button) findViewById(R.id.buttoncheck);
        btnshare = (Button) findViewById(R.id.buttonshare);

        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtyourname.getText().toString().equals("")) {
                    try {
                        Snackbar snackbar;
                        snackbar = Snackbar.make(ll, "Please Fillup Name", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        txtdis.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (edtyourlovename.getText().toString().equals("")) {

                    Snackbar snackbar;
                    snackbar = Snackbar.make(ll, "Please Fillup Your Lovename", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    txtdis.setText("");

                } else {

                    try {
                        String b1 = edtyourname.getText().toString();
                        String b2 = edtyourlovename.getText().toString();
                        int i, sum1 = 0, sum2 = 0;
                        for (i = 0; i < b1.length(); i++) {

                            char ch = b1.charAt(i);
                            int ascii = ch;
                            sum1 = sum1 + ascii;
                        }
                        for (i = 0; i < b2.length(); i++) {
                            char ch = b2.charAt(i);
                            int ascii = ch;
                            sum2 = sum2 + ascii;
                        }
                        int total = sum1 + sum2;
                        int percentage = total % 100;
                        txtdis.setText(percentage + "%");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtyourname = (EditText) findViewById(R.id.edtname);
                edtyourlovename = (EditText) findViewById(R.id.edtlovename);
                String b1 = edtyourname.getText().toString();
                String b2 = edtyourlovename.getText().toString();
                int i, sum1 = 0, sum2 = 0;
                for (i = 0; i < b1.length(); i++) {
                    char ch = b1.charAt(i);
                    int ascii = ch;
                    sum1 = sum1 + ascii;
                }
                for (i = 0; i < b2.length(); i++) {
                    char ch = b2.charAt(i);
                    int ascii = ch;
                    sum2 = sum2 + ascii;
                }
                int total = sum1 + sum2;
                int percentage = total % 100;

                try {
                    String textb1 = edtyourname.getText().toString();
                    String textb2 = edtyourlovename.getText().toString();
                    Uri uri = Uri.parse("https://goo.gl/hXD0n3");
                    String text = "Hello,hi I am using love test app in that " + textb1 + " and your love name " + textb2 + " to calculate have percentage " + percentage + "%" + " .So, Using this app " + uri + "";
                    Intent textShareIntent = new Intent(Intent.ACTION_SEND);
                    textShareIntent.putExtra(Intent.EXTRA_TEXT, text);
                    textShareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(textShareIntent, "Share with..."));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
