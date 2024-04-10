package ro.pub.cs.systems.eim.Colocviu1_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Colocviu1_2SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String allTerms = intent.getStringExtra(Constants.ALL_TERMS_DATA);

        Log.d("Compute tag", allTerms);

        if(!allTerms.isEmpty()) {
            String[] elements = allTerms.split(" + ");
            int sum = 0;
            for(int i = 0; i < elements.length; i++) {
                int elementInt = Integer.parseInt(elements[i]);
                sum += elementInt;
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