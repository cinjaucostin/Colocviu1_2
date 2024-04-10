package ro.pub.cs.systems.eim.Colocviu1_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Colocviu1_2SecondaryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String allTerms = intent.getStringExtra(Constants.ALL_TERMS_DATA);

        Log.d("Compute tag", allTerms);

        if(!allTerms.isEmpty()) {

            int sum = 0;
            StringTokenizer tokenizer = new StringTokenizer(allTerms, "+");
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                System.out.println(token);
                Log.d("Compute tag", token);
                sum += Integer.parseInt(token);
            }

            Intent intentToParent = new Intent();
            intentToParent.putExtra(Constants.TERMS_SUM, sum);
            setResult(RESULT_OK, intentToParent);
            finish();
        } else {
            Intent intentToParent = new Intent();
            setResult(RESULT_CANCELED, intentToParent);
            finish();
        }

    }
}