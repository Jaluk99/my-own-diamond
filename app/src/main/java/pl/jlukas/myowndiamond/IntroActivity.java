package pl.jlukas.myowndiamond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.analytics.FirebaseAnalytics;

public class IntroActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "my-own-diamond ");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "My Own Diamond");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        progressBar = findViewById(R.id.loadingBar);
        progressBar.setProgress(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressBar.getProgress() < 100) {
                    progressBar.incrementProgressBy(1);
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                intent();
            }
        }).start();
    }

    public void intent() {
        Intent intent = new Intent(this, IntroductionActivity.class);
        startActivity(intent);
    }
}
